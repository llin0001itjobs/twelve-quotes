package org.llin.demo.twelvequotes.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.llin.demo.twelvequotes.model.SingleQuote;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtilSingleQuote<T extends SingleQuote> {

    private String urlApi;
    private RestTemplate restTemplate;
    private static final String ARRAY_PARENT = "data";
    private static List<? extends SingleQuote> cachedResult = null; // Static cache

    public JsonUtilSingleQuote() {
        this.restTemplate = new RestTemplate();
    }

    public JsonUtilSingleQuote(String urlApi) {
        this.urlApi = urlApi; // Limit response size (adjust as per API docs)
        this.restTemplate = new RestTemplate();
    }

    public JsonUtilSingleQuote(String urlApi, RestTemplate restTemplate) {
        this.urlApi = urlApi;
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    public synchronized List<T> retrieveObject() throws IOException {
        // Return cached result if available
        if (cachedResult != null) {
            return (List<T>) cachedResult;
        }

        List<T> list = new ArrayList<>();
        ResponseEntity<String> response = restTemplate.getForEntity(urlApi, String.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            return list;
        }

        list = mapJsonText(response.getBody());
        if (cachedResult == null) {
        	cachedResult = list; // Cache the result
        }                
        return list;
    }

    private List<T> mapJsonText(String jsonText) throws IOException {
        if (jsonText == null || jsonText.trim().isEmpty()) {
            return new ArrayList<>();
        }

        // Clean input to remove non-printable characters
        jsonText = jsonText.replaceAll("[\\p{Cntrl}&&[^\\r\\n\\s]]", "");

        ObjectMapper mapper = new ObjectMapper();
        try (JsonParser parser = mapper.createParser(jsonText.getBytes("UTF-8"))) {
            JsonNode jsonNode = mapper.readTree(parser);
            JsonNode dataNode = jsonNode.findValue(ARRAY_PARENT);
            if (dataNode == null) {
                return new ArrayList<>();
            }
            TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {};
            return mapper.readValue(dataNode.traverse(), typeReference);
        } catch (IOException e) {
            // Log specific error (replace with SLF4J in production)
            System.err.println("JSON parsing error: " + e.getMessage());
            throw e; // Rethrow for test failure reporting
        }
    }

    public static void clearCache() {
        cachedResult = null;
    }

    public static void setCache(List<? extends SingleQuote> mockData) {
        cachedResult = mockData;
    }

    public static void main(String[] args) {
        try {
            JsonUtilSingleQuote<SingleQuote> jsUtil = new JsonUtilSingleQuote<>("(https://api.twelvedata.com/stocks");
            List<SingleQuote> list = jsUtil.retrieveObject();
            for (int i = 0; i < 5 && i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}