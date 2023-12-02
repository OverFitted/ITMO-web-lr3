package com.example.lab3.utils;

import com.example.lab3.db.DAOFactory;
import com.example.lab3.entity.ResultEntity;

public class ResultUtils {
    public static void addResult(ResultEntity resultEntity) {
        DAOFactory.getInstance().getResultDAO().addNewResult(resultEntity);
    }

    

    public static boolean checkHit(final int x, final double y, final double r) {
        return (x >= 0 && y >= 0 && y <= r && x <= r) || (x <= 0 && y >= 0 && y <= x + r) || (x <= 0 && y <= 0 && x * x + y * y <= r * r);
    }
}
