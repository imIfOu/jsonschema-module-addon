package com.github.imifou.jsonschema.module.addon.annotation;

import com.github.imifou.jsonschema.module.addon.TypeFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface JsonSchema {

    String title() default "";
    String description() default "";
    TypeFormat format() default TypeFormat.NONE;
    String defaultValue() default "";
    boolean required() default false;
    boolean ignore() default false;
    JSData[] metadata() default {};
}
