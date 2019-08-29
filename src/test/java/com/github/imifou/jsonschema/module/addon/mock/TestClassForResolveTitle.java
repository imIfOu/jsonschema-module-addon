package com.github.imifou.jsonschema.module.addon.mock;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

public final class TestClassForResolveTitle {

    String unannotatedField;
    @JsonSchema
    int annotatedWithoutValueField;
    double annotatedWithoutValueGetterField;
    @JsonSchema(title = "annotation with title 1")
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

    @JsonSchema(title = "annotation with title 2")
    public boolean isAnnotatedGetterField() {
        return this.annotatedGetterField;
    }
}
