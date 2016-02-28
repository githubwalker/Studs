package com.alprojects;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class JsonBuilder {
    private Map<String, Object> mp = new HashMap<>();

    public JsonBuilder() {
    }

    public JsonBuilder add(String name, Object obj) {
        mp.put(name, obj);
        return this;
    }

    String build() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(mp);
    }
}
