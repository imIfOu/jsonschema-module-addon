package com.github.imifou.jsonschema.module.addon;

import com.github.imifou.jsonschema.module.addon.annotation.JSData;
import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;
import com.github.victools.jsonschema.generator.FieldScope;
import com.github.victools.jsonschema.generator.MemberScope;
import com.github.victools.jsonschema.generator.MethodScope;
import com.github.victools.jsonschema.generator.Module;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigPart;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddonModule implements Module {

    @Override
    public void applyToConfigBuilder(SchemaGeneratorConfigBuilder schemaGeneratorConfigBuilder) {
        applyToConfigPart(schemaGeneratorConfigBuilder.forFields());
        applyToConfigPart(schemaGeneratorConfigBuilder.forMethods());
    }

    private void applyToConfigPart(SchemaGeneratorConfigPart<?> configPart){
        configPart.withTitleResolver(this::resolveTitle)
                .withDescriptionResolver(this::resolveDescription)
                .withStringFormatResolver(this::resolveFormat)
                .withDefaultResolver(this::resolveDefault)
                .withRequired(this::resolveRequired)
                .withIgnoreCheck(this::resolveIgnore)
                .withMetadata(this::resolveMetadata);
    }

    private String resolveTitle(MemberScope<?, ?> member){
        return this.getAnnotationFromFieldOrGetter(member, JsonSchema.class)
                .map(JsonSchema::title)
                .filter(title -> !title.isEmpty())
                .orElse(null);
    }

    private String resolveDescription(MemberScope<?, ?> member){
        return this.getAnnotationFromFieldOrGetter(member, JsonSchema.class)
                .map(JsonSchema::description)
                .filter(description -> !description.isEmpty())
                .orElse(null);
    }

    private String resolveFormat(MemberScope<?, ?> member){
        return this.getAnnotationFromFieldOrGetter(member, JsonSchema.class)
                .map(JsonSchema::format)
                .filter(typeFormat -> !TypeFormat.NONE.equals(typeFormat))
                .map(TypeFormat::toString)
                .orElse(null);
    }

    private String resolveDefault(MemberScope<?, ?> member){
        return this.getAnnotationFromFieldOrGetter(member, JsonSchema.class)
                .map(JsonSchema::defaultValue)
                .filter(defaultValue -> !defaultValue.isEmpty())
                .orElse(null);
    }

    private boolean resolveRequired(MemberScope<?, ?> member){
        return this.getAnnotationFromFieldOrGetter(member, JsonSchema.class)
                .map(JsonSchema::required)
                .orElse(Boolean.FALSE);
    }

    private boolean resolveIgnore(MemberScope<?, ?> member){
        return this.getAnnotationFromFieldOrGetter(member, JsonSchema.class)
                .map(JsonSchema::ignore)
                .orElse(Boolean.FALSE);
    }

    protected Map<String,String> resolveMetadata(MemberScope<?, ?> member){
        return this.getAnnotationFromFieldOrGetter(member, JsonSchema.class)
                .map(JsonSchema::metadata)
                .filter(jsData -> jsData.length > 0)
                .flatMap(jsData -> Optional.of(
                            Arrays.stream(jsData)
                            .filter(data -> !data.key().isEmpty())
                            .filter(data -> !data.value().isEmpty())
                            .collect(Collectors.toMap(JSData::key,JSData::value))
                        )
                )
                .orElse(null);
    }

    /**
     * Retrieves the annotation instance of the given type, either from the field it self or (if not present) from its getter.
     *
     * @param <A> type of annotation
     * @param member field or method to retrieve annotation instance from (or from a field's getter or getter method's field)
     * @param annotationClass type of annotation
     * @return annotation instance (or {@code null})
     * @see MemberScope#getAnnotation(Class)
     * @see FieldScope#findGetter()
     * @see MethodScope#findGetterField()
     */
    protected <A extends Annotation> Optional<A> getAnnotationFromFieldOrGetter(MemberScope<?, ?> member, Class<A> annotationClass) {
        A annotation = member.getAnnotation(annotationClass);
        if (annotation == null) {
            MemberScope<?, ?> associatedGetterOrField;
            if (member instanceof FieldScope) {
                associatedGetterOrField = ((FieldScope) member).findGetter();
            } else if (member instanceof MethodScope) {
                associatedGetterOrField = ((MethodScope) member).findGetterField();
            } else {
                associatedGetterOrField = null;
            }
            annotation = associatedGetterOrField == null ? null : associatedGetterOrField.getAnnotation(annotationClass);
        }
        return Optional.ofNullable(annotation);
    }
}
