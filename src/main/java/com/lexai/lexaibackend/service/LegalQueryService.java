package com.lexai.lexaibackend.service;

import com.lexai.lexaibackend.model.LegalQuery;
import com.lexai.lexaibackend.repository.LegalQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalQueryService {

    @Autowired
    private LegalQueryRepository repository;

    public LegalQuery submitQuery(LegalQuery query) {
        return repository.save(query);
    }

    public List<LegalQuery> getAllQueries() {
        return repository.findAll();
    }
}