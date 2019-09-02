package com.github.imifou.jsonschema.module.addon.mock;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

public final class TestClassForResolveMinimumExclusive {

    float unannotatedField;
    @JsonSchema
    int annotatedWithoutValueField;
    double annotatedWithoutValueGetterField;
    @JsonSchema(min = 1)
    int annotatedField;
    @JsonSchema(min = 1, exclusiveMin = true)
    double annotatedFieldExclusive;
    @JsonSchema(min = 1, exclusiveMin = false)
    double annotatedFieldInclusive;

    float annotatedGetterField;
    long annotatedGetterFieldExclusive;
    long annotatedGetterFieldInclusive;

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

    public double annotatedFieldExclusive() {
        return this.annotatedFieldExclusive;
    }

    @JsonSchema(min = 2)
    public float isAnnotatedGetterField() {
        return this.annotatedGetterField;
    }

    @JsonSchema(min = 2, exclusiveMin = true)
    public long isAnnotatedGetterFieldExclusive() {
        return this.annotatedGetterFieldExclusive;
    }

    @JsonSchema(min = 2, exclusiveMin = false)
    public long isAnnotatedGetterFieldInclusive() {
        return this.annotatedGetterFieldInclusive;
    }
}
