package com.github.imifou.jsonschema.module.addon;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveDefault;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveDescription;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveFormat;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveIgnore;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveMaxLength;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveMaximum;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveMaximumExclusive;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveMetadata;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveMinLength;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveMinimum;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveMinimumExclusive;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveMultipleOf;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolvePattern;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveRequired;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveTitle;
import com.github.victools.jsonschema.generator.ConfigFunction;
import com.github.victools.jsonschema.generator.FieldScope;
import com.github.victools.jsonschema.generator.InstanceAttributeOverride;
import com.github.victools.jsonschema.generator.MethodScope;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigPart;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.function.Predicate;

/**
 * Test for the {@link AddonModule} class.
 */

@RunWith(JUnitParamsRunner.class)
public class AddonModuleTest {

    private SchemaGeneratorConfigBuilder configBuilder;
    private SchemaGeneratorConfigPart<FieldScope> fieldConfigPart;
    private SchemaGeneratorConfigPart<MethodScope> methodConfigPart;

    @Before
    public void setUp() {
        this.configBuilder = Mockito.mock(SchemaGeneratorConfigBuilder.class);
        this.fieldConfigPart = Mockito.spy(new SchemaGeneratorConfigPart<>());
        this.methodConfigPart = Mockito.spy(new SchemaGeneratorConfigPart<>());
        Mockito.when(this.configBuilder.forFields()).thenReturn(this.fieldConfigPart);
        Mockito.when(this.configBuilder.forMethods()).thenReturn(this.methodConfigPart);
    }

    @Test
    public void testApplyToConfigBuilderWithDefaultOptions() {
        new AddonModule().applyToConfigBuilder(this.configBuilder);
        this.verifyCommonConfigurations();
        Mockito.verifyNoMoreInteractions(this.configBuilder, this.fieldConfigPart, this.methodConfigPart);
    }

    private void verifyCommonConfigurations() {
        Mockito.verify(this.configBuilder).forFields();
        Mockito.verify(this.configBuilder).forMethods();

        Mockito.verify(this.fieldConfigPart).withTitleResolver(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withDescriptionResolver(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withStringFormatResolver(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withDefaultResolver(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withRequiredCheck(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withIgnoreCheck(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withInstanceAttributeOverride(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withNumberMultipleOfResolver(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withNumberInclusiveMinimumResolver(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withNumberInclusiveMaximumResolver(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withNumberExclusiveMinimumResolver(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withNumberExclusiveMaximumResolver(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withStringPatternResolver(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withStringMinLengthResolver(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withStringMaxLengthResolver(Mockito.any());

        Mockito.verify(this.methodConfigPart).withTitleResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withDescriptionResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withStringFormatResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withDefaultResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withRequiredCheck(Mockito.any());
        Mockito.verify(this.methodConfigPart).withIgnoreCheck(Mockito.any());
        Mockito.verify(this.methodConfigPart).withInstanceAttributeOverride(Mockito.any());
        Mockito.verify(this.methodConfigPart).withNumberMultipleOfResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withNumberInclusiveMinimumResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withNumberInclusiveMaximumResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withNumberExclusiveMinimumResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withNumberExclusiveMaximumResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withStringPatternResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withStringMinLengthResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withStringMaxLengthResolver(Mockito.any());
    }

    Object parametersForTestResolveTitle() {
        return new Object[][]{
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", "annotation with title 1"},
                {"annotatedGetterField", "annotation with title 2"}
        };
    }
    @Test
    @Parameters
    public void testResolveTitle(String fieldName, String expectedTitle){
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveTitle.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, String>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withTitleResolver(captor.capture());
        String title = captor.getValue().apply(field);
        Assert.assertEquals(expectedTitle, title);
    }

    Object parametersForTestResolveDescription() {
        return new Object[][]{
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", "annotation with description 1"},
                {"annotatedGetterField", "annotation with description 2"}
        };
    }
    @Test
    @Parameters
    public void testResolveDescription(String fieldName, String expectedDescription){
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveDescription.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, String>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withDescriptionResolver(captor.capture());
        String description = captor.getValue().apply(field);
        Assert.assertEquals(expectedDescription, description);
    }

    Object parametersForTestResolveDefault() {
        return new Object[][]{
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", "annotation with defaultValue 1"},
                {"annotatedGetterField", "annotation with defaultValue 2"}
        };
    }
    @Test
    @Parameters
    public void testResolveDefault(String fieldName, String expectedDefaultValue){
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveDefault.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, Object>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withDefaultResolver(captor.capture());
        Object defaultValue = captor.getValue().apply(field);
        Assert.assertEquals(expectedDefaultValue, defaultValue);
    }

    Object parametersForTestResolveFormat() {
        return new Object[][]{
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", "date"},
                {"annotatedGetterField", "date"}
        };
    }
    @Test
    @Parameters
    public void testResolveFormat(String fieldName, String expectedFormat){
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveFormat.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, String>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withStringFormatResolver(captor.capture());
        String formatValue = captor.getValue().apply(field);
        Assert.assertEquals(expectedFormat, formatValue);
    }

    Object parametersForTestResolveRequired() {
        return new Object[][]{
                {"unannotatedField", false},
                {"annotatedAsNotHiddenField", false},
                {"annotatedAsNotHiddenGetterField", false},
                {"annotatedField", true},
                {"annotatedGetterField", true}
        };
    }
    @Test
    @Parameters
    public void testResolveRequired(String fieldName, boolean expectedRequired){
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveRequired.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<Predicate<FieldScope>> captor = ArgumentCaptor.forClass(Predicate.class);
        Mockito.verify(this.fieldConfigPart).withRequiredCheck(captor.capture());
        boolean result = captor.getValue().test(field);
        Assert.assertEquals(expectedRequired, result);
    }

    Object parametersForTestResolveIgnore() {
        return new Object[][]{
                {"unannotatedField", false},
                {"annotatedAsNotHiddenField", false},
                {"annotatedAsNotHiddenGetterField", false},
                {"annotatedField", true},
                {"annotatedGetterField", true}
        };
    }
    @Test
    @Parameters
    public void testResolveIgnore(String fieldName, boolean expectedIgnore){
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveIgnore.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<Predicate<FieldScope>> captor = ArgumentCaptor.forClass(Predicate.class);
        Mockito.verify(this.fieldConfigPart).withIgnoreCheck(captor.capture());
        boolean result = captor.getValue().test(field);
        Assert.assertEquals(expectedIgnore, result);
    }

    Object parametersForTestResolveMetadata() {
        return new Object[][]{
                {"unannotatedField", "{}"},
                {"annotatedWithoutValueField", "{}"},
                {"annotatedWithoutValueGetterField", "{}"},
                {"annotatedField", "{\"a\":\"b\"}"},
                {"annotatedGetterField", "{\"a\":\"b\",\"c\":\"d\"}"}
        };
    }
    @Test
    @Parameters
    public void testResolveMetadata(String fieldName, String expectedMetadata) {
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveMetadata.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<InstanceAttributeOverride<FieldScope>> captor = ArgumentCaptor.forClass(InstanceAttributeOverride.class);
        Mockito.verify(this.fieldConfigPart).withInstanceAttributeOverride(captor.capture());
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        captor.getValue().overrideInstanceAttributes(node, field);

        Assert.assertEquals(expectedMetadata, node.toString());
    }

    Object parametersForTestResolveMinimum() {
        return new Object[][]{
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", new BigDecimal(1)},
                {"annotatedFieldExclusive", null},
                {"annotatedFieldInclusive", new BigDecimal(1)},
                {"annotatedGetterField", new BigDecimal(2)},
                {"annotatedGetterFieldExclusive", null},
                {"annotatedGetterFieldInclusive", new BigDecimal(2)}
        };
    }

    @Test
    @Parameters
    public void testResolveMinimum(String fieldName, BigDecimal expectedMinimum) {
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveMinimum.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, BigDecimal>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withNumberInclusiveMinimumResolver(captor.capture());
        BigDecimal formatValue = captor.getValue().apply(field);
        Assert.assertEquals(expectedMinimum, formatValue);
    }

    Object parametersForTestResolveMaximum() {
        return new Object[][]{
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", new BigDecimal(1)},
                {"annotatedFieldExclusive", null},
                {"annotatedFieldInclusive", new BigDecimal(1)},
                {"annotatedGetterField", new BigDecimal(2)},
                {"annotatedGetterFieldExclusive", null},
                {"annotatedGetterFieldInclusive", new BigDecimal(2)}
        };
    }

    @Test
    @Parameters
    public void testResolveMaximum(String fieldName, BigDecimal expectedMaximum) {
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveMaximum.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, BigDecimal>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withNumberInclusiveMaximumResolver(captor.capture());
        BigDecimal formatValue = captor.getValue().apply(field);
        Assert.assertEquals(expectedMaximum, formatValue);
    }

    Object parametersForTestResolveMinimumExclusive() {
        return new Object[][]{
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", null},
                {"annotatedFieldExclusive", new BigDecimal(1)},
                {"annotatedFieldInclusive", null},
                {"annotatedGetterField", null},
                {"annotatedGetterFieldExclusive", new BigDecimal(2)},
                {"annotatedGetterFieldInclusive", null}
        };
    }

    @Test
    @Parameters
    public void testResolveMinimumExclusive(String fieldName, BigDecimal expectedMinimumExclusive) {
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveMinimumExclusive.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, BigDecimal>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withNumberExclusiveMinimumResolver(captor.capture());
        BigDecimal formatValue = captor.getValue().apply(field);
        Assert.assertEquals(expectedMinimumExclusive, formatValue);
    }

    Object parametersForTestResolveMaximumExclusive() {
        return new Object[][]{
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", null},
                {"annotatedFieldExclusive", new BigDecimal(1)},
                {"annotatedFieldInclusive", null},
                {"annotatedGetterField", null},
                {"annotatedGetterFieldExclusive", new BigDecimal(2)},
                {"annotatedGetterFieldInclusive", null}
        };
    }

    @Test
    @Parameters
    public void testResolveMaximumExclusive(String fieldName, BigDecimal expectedMaximumExclusive) {
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveMaximumExclusive.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, BigDecimal>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withNumberExclusiveMaximumResolver(captor.capture());
        BigDecimal formatValue = captor.getValue().apply(field);
        Assert.assertEquals(expectedMaximumExclusive, formatValue);
    }

    Object parametersForTestResolveMultipleOf() {
        return new Object[][]{
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", new BigDecimal(1)},
                {"annotatedFieldBadValue", null},
                {"annotatedGetterField", new BigDecimal(2)},
                {"annotatedGetterFieldBadValue", null},
        };
    }

    @Test
    @Parameters
    public void testResolveMultipleOf(String fieldName, BigDecimal expectedMaximumExclusive) {
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveMultipleOf.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, BigDecimal>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withNumberMultipleOfResolver(captor.capture());
        BigDecimal formatValue = captor.getValue().apply(field);
        Assert.assertEquals(expectedMaximumExclusive, formatValue);
    }


    Object parametersForTestResolvePattern() {
        return new Object[][]{
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", "annotation with pattern 1"},
                {"annotatedGetterField", "annotation with pattern 2"}
        };
    }

    @Test
    @Parameters
    public void testResolvePattern(String fieldName, String expectedPattern) {
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolvePattern.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, String>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withStringPatternResolver(captor.capture());
        String pattern = captor.getValue().apply(field);
        Assert.assertEquals(expectedPattern, pattern);
    }

    Object parametersForTestResolveMinLength() {
        return new Object[][]{
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", 1},
                {"annotatedFieldNegatif", null},
                {"annotatedFieldNull", null},
                {"annotatedGetterField", 2},
                {"annotatedGetterFieldNegatif", null},
                {"annotatedGetterFieldNull", null}
        };
    }

    @Test
    @Parameters
    public void testResolveMinLength(String fieldName, Integer expectedMinLength) {
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveMinLength.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, Integer>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withStringMinLengthResolver(captor.capture());
        Integer minLength = captor.getValue().apply(field);
        Assert.assertEquals(expectedMinLength, minLength);
    }

    Object parametersForTestResolveMaxLength() {
        return new Object[][]{
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", 1},
                {"annotatedFieldMax", null},
                {"annotatedGetterField", 2},
                {"annotatedGetterFieldMax", null}
        };
    }

    @Test
    @Parameters
    public void testResolveMaxLength(String fieldName, Integer expectedMaxLength) {
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveMaxLength.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, Integer>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withStringMaxLengthResolver(captor.capture());
        Integer maxLength = captor.getValue().apply(field);
        Assert.assertEquals(expectedMaxLength, maxLength);
    }
}
