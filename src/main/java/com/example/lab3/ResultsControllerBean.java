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

import com.example.lab3.db.DAOFactory;
import com.example.lab3.entity.ResultEntity;
import com.example.lab3.utils.ResultUtils;

@Named
@SessionScoped
public class ResultsControllerBean implements Serializable {
    private DataBean dataBean;
    private ArrayList<ResultEntity> results = new ArrayList<>();
    private String resultsAsJson;

    @PostConstruct
    public void init() {
        var daoFactory = DAOFactory.getInstance();
        var resultDAO = daoFactory.getResultDAO();
        var resultsEntities = resultDAO.getAllResults();
        results = new ArrayList<>(resultsEntities);
        Collections.reverse(results);
    }

    public int addResult(final int x, final double y, final double r) {
        if (x < -2 || x > 2 || y < -5 || y > 3 || r < 1 || r > 4) {
            return 500;
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

        results.add(0, resultEntity);
        ResultUtils.addResult(resultEntity);

        return 200;
    }

    public DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(DataBean dataBean) {
        this.dataBean = dataBean;
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
