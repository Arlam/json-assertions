package com.aa.assertions;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import java.util.ArrayList;

import static com.aa.assertions.JsonAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;


public class JsonAssertTest {
    String json = "{\n" +
            "  \"firstName\": \"Andrii\",\n" +
            "  \"lastName\": \"Arlamovskyi\",\n" +
            "  \"age\":35,\n" +
            "  \"phone\": [\n" +
            "    {\n" +
            "      \"type\": \"mobile\",\n" +
            "      \"number\":\"+380992997845\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"home\",\n" +
            "      \"number\":\"+380342297845\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Test
    public void sample() {
        assertThat(new ArrayList())
                .hasSize(0);
    }

    @Test
    public void hasJson() throws Exception {
        assertThat(JsonPath.parse(json))
                .hasPropertyEqualsTo("$.lastName", "Arlamovskyi");
    }

    @Test(expected = AssertionError.class)
    public void hasJson_int() throws Exception {
        assertThat(JsonPath.parse(json))
                .hasPropertyEqualsTo("$.age", "35");
    }

    @Test(expected = AssertionError.class)
    public void hasJson_object() throws Exception {
        assertThat(JsonPath.parse(json))
                .hasPropertyEqualsTo("$.phone.[0]", "");
    }

    @Test(expected = AssertionError.class)
    public void hasJson_array() throws Exception {
        assertThat(JsonPath.parse(json))
                .hasPropertyEqualsTo("$.phone", "");
    }


    @Test(expected = AssertionError.class)
    public void hasJson_noValue() throws Exception {
        assertThat(JsonPath.parse(json))
                .hasPropertyEqualsTo("$.name", "value");
    }


}