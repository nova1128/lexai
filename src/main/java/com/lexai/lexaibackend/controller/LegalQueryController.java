package com.lexai.lexaibackend.controller;

import com.lexai.lexaibackend.model.LegalAnalysisResponse;
import com.lexai.lexaibackend.model.LegalQuery;
import com.lexai.lexaibackend.service.LegalQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/legal")
@Tag(name="Legal Quesries" , description = "Submit and analyze legal queries")
public class LegalQueryController {

    @Autowired
    private LegalQueryService service;

    @Operation(summary = "Submit a legal Query" , description ="Saves legal Query to db for analysis")
    @PostMapping("/submit")
    public ResponseEntity<LegalQuery> submitQuery(@RequestBody LegalQuery query) {
        LegalQuery saved = service.submitQuery(query);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Analyzes the Query" , description = "Retrieves relevant Indian law sections and generates legal analysis using RAG pipeline")
    @GetMapping("/analyze/{id}")
    public ResponseEntity<LegalAnalysisResponse> analyzeQuery(@PathVariable Long id) {
        LegalAnalysisResponse analysis = service.analyzeQuery(id);
        return ResponseEntity.ok(analysis);
    }

    @Operation(summary = "Get all legal Queries", description = "return all legal queries from db")
    @GetMapping("/all")
    public ResponseEntity<List<LegalQuery>> getAllQueries() {
        List<LegalQuery> queries = service.getAllQueries();
        return ResponseEntity.ok(queries);
    }
}