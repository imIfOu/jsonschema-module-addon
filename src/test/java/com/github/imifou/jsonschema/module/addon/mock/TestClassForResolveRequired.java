package com.github.imifou.jsonschema.module.addon.mock;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

public class TestClassForResolveRequired {

    String unannotatedField;
    @JsonSchema(required = false)
    int annotatedAsNotHiddenField;
    double annotatedAsNotHiddenGetterField;
    @JsonSchema(required = true)
    Object annotatedField;
    boolean annotatedGetterField;

    public String getUnannotatedField() {
        return this.unannotatedField;
    }

    public int getAnnotatedWithoutValueField() {
        return this.annotatedAsNotHiddenField;
    }

    @JsonSchema
    public double getAnnotatedWithoutValueGetterField() {
        return this.annotatedAsNotHiddenGetterField;
    }

    public Object getAnnotatedField() {
        return this.annotatedField;
    }

    @JsonSchema(required = true)
    public boolean isAnnotatedGetterField() {
        return this.annotatedGetterField;
    }
}
