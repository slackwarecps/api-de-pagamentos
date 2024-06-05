package br.com.fabioalvaro;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonTestUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String readFileAsString(String filePath) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        return new String(encoded);
    }

    public static <T> T convertJsonToObject(String json, Class<T> valueType) throws IOException {
        return objectMapper.readValue(json, valueType);
    }

    public static <T> T convertFileToObject(String filePath, String fileName, Class<T> valueType) throws IOException {
        String json = readFileAsString(filePath.concat(fileName));
        return convertJsonToObject(json, valueType);
    }

}
