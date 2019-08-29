package com.github.imifou.jsonschema.module.addon;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.ResolvedTypeWithMembers;
import com.fasterxml.classmate.members.ResolvedField;
import com.fasterxml.classmate.members.ResolvedMethod;
import com.github.victools.jsonschema.generator.FieldScope;
import com.github.victools.jsonschema.generator.MethodScope;
import com.github.victools.jsonschema.generator.TypeContext;
import com.github.victools.jsonschema.generator.impl.TypeContextFactory;

import java.util.stream.Stream;
public class TestType {

    private final TypeContext context;
    private final ResolvedType resolvedTestClass;
    private final ResolvedTypeWithMembers testClassMembers;

    public TestType(Class<?> testClass) {
        this.context = TypeContextFactory.createDefaultTypeContext();
        this.resolvedTestClass = this.context.resolve(testClass);
        this.testClassMembers = this.context.resolveWithMembers(this.resolvedTestClass);
    }

    public FieldScope getMemberField(String fieldName) {
        return this.getField(this.testClassMembers.getMemberFields(), fieldName);
    }

    public FieldScope getStaticField(String fieldName) {
        return this.getField(this.testClassMembers.getStaticFields(), fieldName);
    }

    private FieldScope getField(ResolvedField[] fields, String fieldName) {
        return Stream.of(fields)
                .filter(field -> fieldName.equals(field.getName()))
                .findAny()
                .map(field -> this.context.createFieldScope(field, this.testClassMembers))
                .get();
    }

    public MethodScope getMemberMethod(String methodName) {
        return this.getMethod(this.testClassMembers.getMemberMethods(), methodName);
    }

    public MethodScope getStaticMethod(String methodName) {
        return this.getMethod(this.testClassMembers.getStaticMethods(), methodName);
    }

    private MethodScope getMethod(ResolvedMethod[] methods, String methodName) {
        return Stream.of(methods)
                .filter(method -> methodName.equals(method.getName()))
                .findAny()
                .map(method -> this.context.createMethodScope(method, this.testClassMembers))
                .get();
    }
}