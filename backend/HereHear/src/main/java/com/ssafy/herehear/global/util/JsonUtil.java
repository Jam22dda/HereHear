package com.ssafy.herehear.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

@Slf4j
public class JsonUtil {

    private JsonUtil() {
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String extractFieldsAsJsonString(String json, String... fields) {
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            Map<String, JsonNode> result = new HashMap<>();

            for (String field : fields) {
                JsonNode value = jsonNode.get(field);
                if (value != null) {
                    result.put(field, value);
                }
            }

            return objectMapper.writeValueAsString(result);
        } catch (IOException e) {
            log.error("Error parsing JSON: " + e.getMessage());
            throw new CustomException(ExceptionStatus.JSON_PARSE_ERROR);
        }
    }

    public static String getValueAsString(String json, String key) {
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode valueNode = jsonNode.get(key);
            if (valueNode != null) {
                return valueNode.asText();
            }
        } catch (IOException e) {
            log.error("Error parsing JSON: " + e.getMessage());
            throw new CustomException(ExceptionStatus.JSON_PARSE_ERROR);
        }
        return null;
    }

    public static String getValueAsJson(String json, String key) {
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode valueNode = jsonNode.get(key);
            if (valueNode != null) {
                return valueNode.toString();
            }
        } catch (IOException e) {
            log.error("Error parsing JSON: " + e.getMessage());
            throw new CustomException(ExceptionStatus.JSON_PARSE_ERROR);
        }
        return null;
    }

    public static <T> T fromJson(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Optional<List<String>> getValuesAsList(String json, String key) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode targetNode = rootNode.path(key);

            if (targetNode.isArray()) {
                List<String> values = new ArrayList<>();
                targetNode.forEach(element -> values.add(element.toString()));
                return Optional.of(values);
            }

            return Optional.empty();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
