package com.example.lab3;

import jakarta.annotation.PostConstruct;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.lab3.db.ResultDAO;
import com.example.lab3.entity.ResultEntity;
import com.example.lab3.utils.ResultUtils;

@Named
@SessionScoped
public class ResultsControllerBean implements Serializable {
    private ArrayList<ResultEntity> results = new ArrayList<>();
    private String resultsAsJson;

    @PostConstruct
    public void init() {
        var resultDAO = ResultDAO.getInstance();
        var resultsEntities = resultDAO.getAllResults();
        results = new ArrayList<>(resultsEntities);
        Collections.reverse(results);
    }

    public void addResult(final int x, final double y, final double r) {
        if (x < -2 || x > 2 || y < -5 || y > 3 || r < 1 || r > 4) {
            return;
        }

        Long startTime = System.nanoTime();

        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setX(x);
        resultEntity.setY(y);
        resultEntity.setR(r);
        resultEntity.setRequestTime(new Timestamp(System.currentTimeMillis()));
        resultEntity.setHit(ResultUtils.checkHit(x, y, r));

        Double executionTime = (System.nanoTime() - startTime) / 1000.;
        resultEntity.setExecutionTime(executionTime);

        ResultUtils.addResult(resultEntity);
        results.add(0, resultEntity);
    }

    public ArrayList<ResultEntity> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultEntity> results) {
        this.results = results;
    }

    public String getResultsAsJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            resultsAsJson = objectMapper.writeValueAsString(results);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            resultsAsJson = "[]";
        }
        return resultsAsJson;
    }

    public void setResultsAsJson(String resultsAsJson) {
        this.resultsAsJson = resultsAsJson;
    }
}
