package by.issoft.store.util;

import by.issoft.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonValue;

import java.io.File;
import java.io.IOException;

public class Converter {
    private final static String baseFile = "domain.src.main.java.by.issoft.domain.Product.java";

    public static JsonValue toJSON(Product product) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), product);
        System.out.println("json created!");
        return (JsonValue) mapper;
    }

    public static Product toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(baseFile), Product.class);
    }
}
