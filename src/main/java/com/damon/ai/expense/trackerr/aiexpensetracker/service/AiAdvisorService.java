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

	@Value("${ollama.url}")
	private String ollamaUrl;

	@Value("${ollama.model}")
	private String model;

	private final RestTemplate restTemplate = new RestTemplate();

	public String generateInsights(List<SpendingRecord> records) {
		// Build a summary of spending records
		String summary = records.stream().map(
				r -> String.format("Category: %s, Cost: %.2f, Note: %s", r.getCategory(), r.getCost(), r.getNote()))
				.collect(Collectors.joining("\n"));

		Map<String, Object> request = Map.of("model", model, "prompt",
				"You are a financial coach. Analyze these spending records:\n" + summary, "stream", false);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

		ResponseEntity<Map> response = restTemplate.postForEntity(ollamaUrl, entity, Map.class);

		Map body = response.getBody();

		if (body != null) {
			return body.get("response").toString();
		}

		return "No AI insight available";
	}
}
