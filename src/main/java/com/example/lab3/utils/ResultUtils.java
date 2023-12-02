package com.example.lab3.utils;

import com.example.lab3.db.DAOFactory;
import com.example.lab3.entity.ResultEntity;

public class ResultUtils {
    public static void addResult(ResultEntity resultEntity) {
        DAOFactory.getInstance().getResultDAO().addNewResult(resultEntity);
    }

    public static boolean checkHit(final int x, final double y, final double R) {
        boolean insideCircle = (x <= 0 && y >= 0) && (x * x + y * y <= R * R);
        boolean insideRectangle = (x >= 0 && x <= R) && (y <= R && y >= R / 2);
        boolean insideTriangle = (x <= 0 && y <= 0) && (y >= -x - R);
        return insideCircle || insideRectangle || insideTriangle;
    }
}
