package com.github.imifou.jsonschema.module.addon.mock;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

public class TestClassForResolvePattern {

    String unannotatedField;
    @JsonSchema
    String annotatedWithoutValueField;
    String annotatedWithoutValueGetterField;
    @JsonSchema(pattern = "annotation with pattern 1")
    String annotatedField;
    String annotatedGetterField;

    public String getUnannotatedField() {
        return this.unannotatedField;
    }

    public String getAnnotatedWithoutValueField() {
        return this.annotatedWithoutValueField;
    }

    @JsonSchema
    public String getAnnotatedWithoutValueGetterField() {
        return this.annotatedWithoutValueGetterField;
    }

    public String getAnnotatedField() {
        return this.annotatedField;
    }

    @JsonSchema(pattern = "annotation with pattern 2")
    public String isAnnotatedGetterField() {
        return this.annotatedGetterField;
    }
}
