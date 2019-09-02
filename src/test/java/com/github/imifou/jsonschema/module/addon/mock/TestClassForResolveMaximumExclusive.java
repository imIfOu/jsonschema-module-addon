package com.github.imifou.jsonschema.module.addon.mock;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

public final class TestClassForResolveMaximumExclusive {

    float unannotatedField;
    @JsonSchema
    int annotatedWithoutValueField;
    double annotatedWithoutValueGetterField;
    @JsonSchema(max = 1)
    int annotatedField;
    @JsonSchema(max = 1, exclusiveMax = true)
    double annotatedFieldExclusive;
    @JsonSchema(max = 1, exclusiveMax = false)
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

    @JsonSchema(max = 2)
    public float isAnnotatedGetterField() {
        return this.annotatedGetterField;
    }

    @JsonSchema(max = 2, exclusiveMax = true)
    public long isAnnotatedGetterFieldExclusive() {
        return this.annotatedGetterFieldExclusive;
    }

    @JsonSchema(max = 2, exclusiveMax = false)
    public long isAnnotatedGetterFieldInclusive() {
        return this.annotatedGetterFieldInclusive;
    }
}
