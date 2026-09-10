package com.lexai.lexaibackend.service;

import com.lexai.lexaibackend.model.LegalAnalysisResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final WebClient webClient = WebClient.builder().build();

    public LegalAnalysisResponse analyzeLegalProblem(String problemText, String category) {

        String prompt = buildPrompt(problemText, category);

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                        Map.of("role", "system", "content",
                                "You are an expert Indian lawyer with deep knowledge of IPC, CrPC, and all Indian laws. " +
                                        "Analyze legal problems and provide structured advice. " +
                                        "Always respond in this exact format:\n" +
                                        "RELEVANT_LAWS: [list the relevant laws and sections]\n" +
                                        "IN_YOUR_FAVOR: [what supports the user's case]\n" +
                                        "AGAINST_YOU: [what works against the user]\n" +
                                        "RECOMMENDED_STEPS: [step by step legal actions to take]\n" +
                                        "URGENCY: [LOW/MEDIUM/HIGH]"),
                        Map.of("role", "user", "content", prompt)
                ),
                "max_tokens", 1000,
                "temperature", 0.3
        );

        try {
            Map response = webClient.post()
                    .uri(apiUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            String content = extractContent(response);
            return parseResponse(content);

        } catch (Exception e) {
            LegalAnalysisResponse errorResponse = new LegalAnalysisResponse();
            errorResponse.setRawAnalysis("Analysis failed: " + e.getMessage());
            return errorResponse;
        }
    }

    private String buildPrompt(String problemText, String category) {
        return String.format(
                "Legal Problem Category: %s\n\nProblem Description: %s\n\n" +
                        "Please analyze this legal problem under Indian law and provide detailed guidance.",
                category, problemText
        );
    }

    private String extractContent(Map response) {
        try {
            List choices = (List) response.get("choices");
            Map firstChoice = (Map) choices.get(0);
            Map message = (Map) firstChoice.get("message");
            return (String) message.get("content");
        } catch (Exception e) {
            return "Could not extract response";
        }
    }

    private LegalAnalysisResponse parseResponse(String content) {
        LegalAnalysisResponse result = new LegalAnalysisResponse();
        result.setRawAnalysis(content);

        result.setRelevantLaws(extractSection(content, "RELEVANT_LAWS:"));
        result.setInYourFavor(extractSection(content, "IN_YOUR_FAVOR:"));
        result.setAgainstYou(extractSection(content, "AGAINST_YOU:"));
        result.setRecommendedSteps(extractSection(content, "RECOMMENDED_STEPS:"));
        result.setUrgency(extractSection(content, "URGENCY:"));

        return result;
    }

    private String extractSection(String content, String sectionKey) {
        try {
            int start = content.indexOf(sectionKey);
            if (start == -1) return "Not available";
            start += sectionKey.length();
            int end = content.indexOf("\n", start);
            if (end == -1) end = content.length();
            return content.substring(start, end).trim();
        } catch (Exception e) {
            return "Not available";
        }
    }
}