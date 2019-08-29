package com.github.imifou.jsonschema.module.addon;

import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveDefault;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveDescription;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveFormat;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveIgnore;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveMetadata;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveRequired;
import com.github.imifou.jsonschema.module.addon.mock.TestClassForResolveTitle;
import com.github.victools.jsonschema.generator.ConfigFunction;
import com.github.victools.jsonschema.generator.FieldScope;
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

import java.util.HashMap;
import java.util.Map;
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
        Mockito.verify(this.fieldConfigPart).withRequired(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withIgnoreCheck(Mockito.any());
        Mockito.verify(this.fieldConfigPart).withMetadata(Mockito.any());

        Mockito.verify(this.methodConfigPart).withTitleResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withDescriptionResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withStringFormatResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withDefaultResolver(Mockito.any());
        Mockito.verify(this.methodConfigPart).withRequired(Mockito.any());
        Mockito.verify(this.methodConfigPart).withIgnoreCheck(Mockito.any());
        Mockito.verify(this.methodConfigPart).withMetadata(Mockito.any());
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

        ArgumentCaptor<ConfigFunction<FieldScope, String>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withDefaultResolver(captor.capture());
        String defaultValue = captor.getValue().apply(field);
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
        Mockito.verify(this.fieldConfigPart).withRequired(captor.capture());
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
                {"unannotatedField", null},
                {"annotatedWithoutValueField", null},
                {"annotatedWithoutValueGetterField", null},
                {"annotatedField", new HashMap<String,String>() {{put("a", "b");}}},
                {"annotatedGetterField", new HashMap<String,String>() {{put("a", "b");put("c", "d");}}}
        };
    }
    @Test
    @Parameters
    public void testResolveMetadata(String fieldName, Map<String,String> expectedMetadata){
        new AddonModule().applyToConfigBuilder(this.configBuilder);

        TestType testType = new TestType(TestClassForResolveMetadata.class);
        FieldScope field = testType.getMemberField(fieldName);

        ArgumentCaptor<ConfigFunction<FieldScope, Map<String,String>>> captor = ArgumentCaptor.forClass(ConfigFunction.class);
        Mockito.verify(this.fieldConfigPart).withMetadata(captor.capture());
        Map<String,String> result = captor.getValue().apply(field);
        Assert.assertEquals(expectedMetadata, result);
    }
}
