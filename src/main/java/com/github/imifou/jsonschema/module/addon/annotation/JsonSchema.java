package com.github.imifou.jsonschema.module.addon.annotation;

import com.github.imifou.jsonschema.module.addon.TypeFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JsonSchema annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface JsonSchema {

    /**
     * Title of properties
     */
    String title() default "";

    /**
     * Description of properties
     */
    String description() default "";

    /**
     * Format for String properties
     */
    TypeFormat format() default TypeFormat.NONE;

    /**
     * Default value of properties
     */
    String defaultValue() default "";

    /**
     * MultipleOf value for Number properties
     */
    double multipleOf() default 0;

    /**
     * Minimum value for Number properties
     */
    double min() default Double.MIN_VALUE;

    /**
     * Exclusive minimum value for Number properties
     */
    boolean exclusiveMin() default false;

    /**
     * Maximum value for Number properties
     */
    double max() default Double.MAX_VALUE;

    /**
     * Exclusive maximum value for Number properties
     */
    boolean exclusiveMax() default false;

    /**
     * Required properties, properties can't be null
     */
    boolean required() default false;

    /**
     * Ignore field/ method during the process of generation of json schema
     */
    boolean ignore() default false;

    /**
     * Metada properties
     *
     * @return
     */
    JSData[] metadata() default {};


}
