# Java JSON Schema Generation – Module Addon
[![Build Status](https://travis-ci.com/imIfOu/jsonschema-module-addon.svg?branch=master)](https://travis-ci.com/imIfOu/jsonschema-module-addon)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.imifou/jsonschema-module-addon/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.imifou/jsonschema-module-addon)

Module for the [victools/jsonschema-generator](https://github.com/victools/jsonschema-generator) – deriving JSON Schema attributes from annotations

## Features

* Indicate a title on property(field/method) `@JsonSchema(title="...")`
* Indicate a description on property(field/method) `@JsonSchema(description="...")`
* Indicate a format on string property(field/method) `@JsonSchema(format=TypeFormat.DATE)`
* Indicate a minimum value on number property(field/method) `@JsonSchema(min=0)`
* Indicate a maximum value on number property(field/method) `@JsonSchema(max=10)`
* Indicate a minimum exclusive value on number property(field/method) `@JsonSchema(min=0, exclusiveMin = true)`
* Indicate a maximum exclusive value on number property(field/method) `@JsonSchema(max=10, exclusiveMin = false)`
* Indicate a multiple of value on number property(field/method) `@JsonSchema(multipleOf=2)`
* Indicate a default value on property(field/method) `@JsonSchema(defaultValue="...")`
* Indicate if property is required(field/method)  `@JsonSchema(required=true)`
* Optionally ignore a property(field/method) `@JsonSchema(ignore=true)`
* Indicate new metadata(field/method) `@JsonSchema(metadata={@JSData(key="...",value="...")})`

## Usage
### Dependency (Maven)
```xml
<dependency>
    <groupId>com.github.imifou</groupId>
    <artifactId>jsonschema-module-addon</artifactId>
    <version>1.1.0</version>
</dependency>
```

### Code
#### Passing into SchemaGeneratorConfigBuilder.with(Module)
```java
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.imifou.jsonschema.module.addon.AddonModule;
```
```java
AddonModule module = new AddonModule();
SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(objectMapper).with(module);
```

#### Complete Example
```java
import com.github.imifou.jsonschema.module.addon.AddonModule;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
```
```java
ObjectMapper objectMapper = new ObjectMapper();
AddonModule module = new AddonModule();
SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(objectMapper,OptionPreset.PLAIN_JSON)
  .with(module);
SchemaGeneratorConfig config = configBuilder.build();
SchemaGenerator generator = new SchemaGenerator(config);
JsonNode jsonSchema = generator.generateSchema(getEntityClass());
        
System.out.println(jsonSchema.toString());
```
