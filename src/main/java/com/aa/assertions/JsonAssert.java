package com.aa.assertions;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.PathNotFoundException;
import net.minidev.json.JSONArray;
import org.assertj.core.api.AbstractAssert;

import java.util.LinkedHashMap;
import java.util.Objects;

public class JsonAssert extends AbstractAssert<JsonAssert, DocumentContext> {

    private JsonAssert(DocumentContext documentContext) {
        super(documentContext, JsonAssert.class);
    }

    public static JsonAssert assertThat(DocumentContext actual) {
        return new JsonAssert(actual);
    }

    public JsonAssert hasPropertyEqualsTo(String path, String value) {
        isNotNull();

        try {
            Object actualValue = actual.read(path);
            isNotAnArray(path);
            isNotInteger(path);
            isNotAnObject(path);
            if (!Objects.equals(actualValue, value)) {
                failWithMessage("Expected path <%s> to be <%s> but was <%s>", path, value, actualValue);
            }
        } catch (PathNotFoundException e) {
            failWithMessage("Expected path <%s> to be <%s> but was <%s>", path, value, null);
        }
        return this;
    }

    public void isNotAnObject(String path) {
        Object actualValue = actual.read(path);
        if (actualValue instanceof JSONArray) {
            failWithMessage("Expected path <%s> to be String value but was an array", path);
        }
    }

    public void isNotAnArray(String path) {
        Object actualValue = actual.read(path);
        if (actualValue instanceof LinkedHashMap) {
            failWithMessage("Expected path <%s> to be String value but was an object", path);
        }
    }

    public void isNotInteger(String path) {
        Object actualValue = actual.read(path);
        if (actualValue instanceof Integer) {
            failWithMessage("Expected path <%s> to be String value but was an Integer", path);
        }
    }

}
