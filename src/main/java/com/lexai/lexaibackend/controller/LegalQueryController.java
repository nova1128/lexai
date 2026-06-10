package com.lexai.lexaibackend.controller;

import com.lexai.lexaibackend.model.LegalQuery;
import com.lexai.lexaibackend.service.LegalQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/legal")
public class LegalQueryController {

    @Autowired
    private LegalQueryService service;

    @PostMapping("/submit")
    public ResponseEntity<LegalQuery> submitQuery(@RequestBody LegalQuery query) {
        LegalQuery saved = service.submitQuery(query);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LegalQuery>> getAllQueries() {
        List<LegalQuery> queries = service.getAllQueries();
        return ResponseEntity.ok(queries);
    }
}