package com.damon.ai.expense.trackerr.aiexpensetracker.service;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.SpendingRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AiAdvisorService {

        @Value("${gemini.api.key}")
        private String apiKey;

        @Value("${gemini.api.url}")
        private String apiUrl;

        private final RestTemplate restTemplate = new RestTemplate();

        public String generateInsights(List<SpendingRecord> records) {
                // Build a summary of spending records
                String summary = records.stream()
                                .map(r -> String.format("Category: %s, Cost: %.2f, Note: %s",
                                                r.getCategory(), r.getCost(), r.getNote()))
                                .collect(Collectors.joining("\n"));

                // Gemini request format
                Map<String, Object> request = Map.of(
                                "contents", List.of(
                                                Map.of("parts", List.of(
                                                                Map.of("text", "You are a financial coach. Analyze these spending records:\n"
                                                                                + summary)))));

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("Authorization", "Bearer " + apiKey);

                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

                ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, entity, Map.class);

                Map body = response.getBody();
                if (body != null && body.containsKey("candidates")) {
                        Map candidate = (Map) ((List) body.get("candidates")).get(0);
                        Map content = (Map) candidate.get("content");
                        List parts = (List) content.get("parts");
                        Map firstPart = (Map) parts.get(0);
                        return firstPart.get("text").toString();
                }
                return "No AI insight available.";
        }
}
