package com.github.imifou.jsonschema.module.addon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.imifou.jsonschema.module.addon.annotation.Default;
import com.github.imifou.jsonschema.module.addon.annotation.Format;
import com.github.victools.jsonschema.generator.FieldScope;
import com.github.victools.jsonschema.generator.MemberScope;
import com.github.victools.jsonschema.generator.MethodScope;
import com.github.victools.jsonschema.generator.Module;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;

import java.lang.annotation.Annotation;

public class AddonModule implements Module {

    private ObjectMapper objectMapper;

    @Override
    public void applyToConfigBuilder(SchemaGeneratorConfigBuilder schemaGeneratorConfigBuilder) {
        schemaGeneratorConfigBuilder.forFields()
                .withStringFormatResolver(this::resolveFormat)
                .withDefaultResolver(this::resolveDefault);
    }


    protected String resolveFormat(FieldScope fieldScope){
        Format formatAnnotation = this.getAnnotationFromFieldOrGetter(fieldScope, Format.class);
        if(formatAnnotation != null && !formatAnnotation.value().isEmpty()){
            return formatAnnotation.value();
        }
        return null;
    }

    protected String resolveDefault(FieldScope fieldScope){
        Default defaultAnnotation = this.getAnnotationFromFieldOrGetter(fieldScope, Default.class);
        if(defaultAnnotation != null && !defaultAnnotation.value().isEmpty()){
            return defaultAnnotation.value();
        }
        return null;
    }


    /**
     * Retrieves the annotation instance of the given type, either from the field it self or (if not present) from its getter.
     *
     * @param <A> type of annotation
     * @param field field to retrieve annotation instance from (or from its getter)
     * @param annotationClass type of annotation
     * @return annotation instance (or {@code null})
     * @see MemberScope#getAnnotation(Class)
     * @see FieldScope#findGetter()
     */
    protected <A extends Annotation> A getAnnotationFromFieldOrGetter(FieldScope field, Class<A> annotationClass) {
        A annotation = field.getAnnotation(annotationClass);
        if (annotation == null) {
            MethodScope getter = field.findGetter();
            annotation = getter == null ? null : getter.getAnnotation(annotationClass);
        }
        return annotation;
    }
}
