package com.lexai.lexaibackend.model;

import lombok.Data;

@Data
public class LegalAnalysisResponse {
    private String relevantLaws;
    private String inYourFavor;
    private String againstYou;
    private String recommendedSteps;
    private String urgency;
    private String rawAnalysis;
}