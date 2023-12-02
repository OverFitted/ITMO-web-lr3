package com.example.lab3.db;

import java.util.Collection;

import com.example.lab3.entity.ResultEntity;

public interface ResultDAO {
    void addNewResult(ResultEntity result);

    void updateResult(Long result_id, ResultEntity result);

    ResultEntity getResultById(Long result_id);

    Collection<ResultEntity> getAllResults();

    void deleteResult(ResultEntity result);

    void clearResults();
}
