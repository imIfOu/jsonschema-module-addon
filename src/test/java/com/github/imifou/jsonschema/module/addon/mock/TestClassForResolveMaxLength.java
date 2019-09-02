package com.github.imifou.jsonschema.module.addon.mock;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

public class TestClassForResolveMaxLength {

    String unannotatedField;
    @JsonSchema
    String annotatedWithoutValueField;
    String annotatedWithoutValueGetterField;
    @JsonSchema(maxLength = 1)
    String annotatedField;
    @JsonSchema(maxLength = Integer.MAX_VALUE)
    String annotatedFieldMax;

    String annotatedGetterField;
    String annotatedGetterFieldMax;

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

    @JsonSchema(maxLength = 2)
    public String isAnnotatedGetterField() {
        return this.annotatedGetterField;
    }

    @JsonSchema(maxLength = Integer.MAX_VALUE)
    public String isAnnotatedGetterFieldMax() {
        return this.annotatedGetterFieldMax;
    }

}
