package com.example.lab3.utils;

import com.example.lab3.db.DAOFactory;
import com.example.lab3.entity.ResultEntity;

public class ResultUtils {
    public static void addResult(final int x, final double y, final double r) {
        ResultEntity point = new ResultEntity();
        point.setX(x);
        point.setY(y);
        point.setR(r);

        DAOFactory.getInstance().getResultDAO().addNewResult(point);
    }
}
