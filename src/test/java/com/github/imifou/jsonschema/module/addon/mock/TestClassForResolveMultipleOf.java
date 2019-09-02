package com.github.imifou.jsonschema.module.addon.mock;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

public final class TestClassForResolveMultipleOf {

    float unannotatedField;
    @JsonSchema
    int annotatedWithoutValueField;
    double annotatedWithoutValueGetterField;
    @JsonSchema(multipleOf = 1)
    int annotatedField;
    @JsonSchema(multipleOf = 0)
    double annotatedFieldBadValue;

    float annotatedGetterField;
    long annotatedGetterFieldBadValue;

    public float getUnannotatedField() {
        return this.unannotatedField;
    }

    public int getAnnotatedWithoutValueField() {
        return this.annotatedWithoutValueField;
    }

    @JsonSchema
    public double getAnnotatedWithoutValueGetterField() {
        return this.annotatedWithoutValueGetterField;
    }

    public int getAnnotatedField() {
        return this.annotatedField;
    }

    public double annotatedFieldBadValue() {
        return this.annotatedFieldBadValue;
    }

    @JsonSchema(multipleOf = 2)
    public float isAnnotatedGetterField() {
        return this.annotatedGetterField;
    }

    @JsonSchema(multipleOf = 0)
    public long isAnnotatedGetterFieldBadValue() {
        return this.annotatedGetterFieldBadValue;
    }
}
