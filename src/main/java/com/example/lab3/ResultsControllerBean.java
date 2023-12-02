package com.example.lab3;

import jakarta.annotation.PostConstruct;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import com.example.lab3.db.DAOFactory;
import com.example.lab3.entity.ResultEntity;
import com.example.lab3.utils.ResultUtils;

@Named
@SessionScoped
public class ResultsControllerBean implements Serializable {
    private DataBean dataBean;
    private ArrayList<ResultEntity> results = new ArrayList<>();

    @PostConstruct
    public void init() {
        var resultsEntities = DAOFactory.getInstance().getResultDAO().getAllResults();
        results = new ArrayList<>(resultsEntities);
    }


    public void addResult(final int x, final double y, final double r) {
        Long startTime = System.nanoTime();

        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setX(x);
        resultEntity.setY(y);
        resultEntity.setR(r);
        resultEntity.setRequestTime(new Date(System.currentTimeMillis()));
        resultEntity.setHit(ResultUtils.checkHit(x, y, r));

        Double executionTime = (System.nanoTime() - startTime) / 1000.;
        resultEntity.setExecutionTime(executionTime);

        results.add(resultEntity);
        ResultUtils.addResult(resultEntity);
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
}
