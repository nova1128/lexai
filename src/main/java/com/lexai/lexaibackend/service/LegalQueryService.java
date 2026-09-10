package com.lexai.lexaibackend.service;

import com.lexai.lexaibackend.model.LegalAnalysisResponse;
import com.lexai.lexaibackend.model.LegalQuery;
import com.lexai.lexaibackend.repository.LegalQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalQueryService {

    @Autowired
    private LegalQueryRepository repository;

    @Autowired
    private OpenAIService openAIService;

    public LegalQuery submitQuery(LegalQuery query) {
        return repository.save(query);
    }

    public LegalAnalysisResponse analyzeQuery(Long id) {
        LegalQuery query = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Query not found with id: " + id));
        return openAIService.analyzeLegalProblem(query.getProblemText(), query.getCategory());
    }

    public List<LegalQuery> getAllQueries() {
        return repository.findAll();
    }
}