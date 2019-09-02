package com.github.imifou.jsonschema.module.addon.mock;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

public class TestClassForResolveMinLength {

    String unannotatedField;
    @JsonSchema
    String annotatedWithoutValueField;
    String annotatedWithoutValueGetterField;
    @JsonSchema(minLength = 1)
    String annotatedField;
    @JsonSchema(minLength = -1)
    String annotatedFieldNegatif;
    @JsonSchema(minLength = 0)
    String annotatedFieldNull;

    String annotatedGetterField;
    String annotatedGetterFieldNegatif;
    String annotatedGetterFieldNull;

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

    @JsonSchema(minLength = 2)
    public String isAnnotatedGetterField() {
        return this.annotatedGetterField;
    }

    @JsonSchema(minLength = -2)
    public String isAnnotatedGetterFieldNegatif() {
        return this.annotatedGetterFieldNegatif;
    }

    @JsonSchema(minLength = 0)
    public String isAnnotatedGetterFieldNull() {
        return this.annotatedGetterFieldNull;
    }
}
