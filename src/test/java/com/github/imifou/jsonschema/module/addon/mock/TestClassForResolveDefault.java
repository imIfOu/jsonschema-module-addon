package com.github.imifou.jsonschema.module.addon.mock;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

public class TestClassForResolveDefault {

    String unannotatedField;
    @JsonSchema
    int annotatedWithoutValueField;
    double annotatedWithoutValueGetterField;
    @JsonSchema(defaultValue = "annotation with defaultValue 1")
    Object annotatedField;
    boolean annotatedGetterField;

    public String getUnannotatedField() {
        return this.unannotatedField;
    }

    public int getAnnotatedWithoutValueField() {
        return this.annotatedWithoutValueField;
    }

    @JsonSchema
    public double getAnnotatedWithoutValueGetterField() {
        return this.annotatedWithoutValueGetterField;
    }

    public Object getAnnotatedField() {
        return this.annotatedField;
    }

    @JsonSchema(defaultValue = "annotation with defaultValue 2")
    public boolean isAnnotatedGetterField() {
        return this.annotatedGetterField;
    }
}
