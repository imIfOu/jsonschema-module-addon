package com.github.imifou.jsonschema.module.addon;

/**
 * Enum all types for string format
 */
public enum TypeFormat {

    DATE("date"),
    TIME("time"),
    DATE_TIME("date-time"),
    REGEX("regex"),
    EMAIL("email"),
    IDN_EMAIL("idn-email"),
    HOSTNAME("hostname"),
    IDN_HOSTNAME("idn-hostname"),
    IPV4("ipv4"),
    IPV6("ipv6"),
    JSON_POINTER("json-pointer"),
    RELATIVE_JSON_POINTER("relative-json-pointer"),
    URI("uri"),
    URI_REFERENCE("uri-reference"),
    URI_TEMPLATE("uri-template"),
    IRI("iri"),
    IRI_REFERENCE("iri-reference"),
    NONE("none");

    private String value;

    TypeFormat(final String value){
        this.value=value;
    }

    @Override
    public String toString() {
        return value;
    }
}
