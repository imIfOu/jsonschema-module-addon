package com.github.imifou.jsonschema.module.addon.mock;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

public class TestClassForResolveDescription {

    String unannotatedField;
    @JsonSchema
    int annotatedWithoutValueField;
    double annotatedWithoutValueGetterField;
    @JsonSchema(description = "annotation with description 1")
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

    @JsonSchema(description = "annotation with description 2")
    public boolean isAnnotatedGetterField() {
        return this.annotatedGetterField;
    }
}
