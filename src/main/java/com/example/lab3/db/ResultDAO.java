package com.example.lab3.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Root;

import java.util.Collection;

import com.example.lab3.entity.ResultEntity;

public class ResultDAO {
    private EntityManager entityManager;
    private static ResultDAO instance;

    public ResultDAO() {
        this.entityManager = JPAUtils.getFactory().createEntityManager();
    }

    public ResultDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static ResultDAO getInstance() {
        if (instance == null)
            instance = new ResultDAO();
        return instance;
    }

    public void addNewResult(ResultEntity result) {
        entityManager.getTransaction().begin();
        entityManager.persist(result);
        entityManager.getTransaction().commit();
    }

    public void updateResult(Long result_id, ResultEntity result) {
        entityManager.getTransaction().begin();
        entityManager.merge(result);
        entityManager.getTransaction().commit();
    }

    public ResultEntity getResultById(Long result_id) {
        return entityManager.getReference(ResultEntity.class, result_id);
    }

    public Collection<ResultEntity> getAllResults() {
        var cm = entityManager.getCriteriaBuilder().createQuery(ResultEntity.class);
        Root<ResultEntity> root = cm.from(ResultEntity.class);
        return entityManager.createQuery(cm.select(root)).getResultList();
    }

    public void deleteResult(ResultEntity result) {
        entityManager.getTransaction().begin();
        entityManager.remove(result);
        entityManager.getTransaction().commit();
    }

    public void clearResults() {
        entityManager.clear();
    }
}
