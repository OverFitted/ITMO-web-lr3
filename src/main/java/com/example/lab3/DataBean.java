package com.example.lab3;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.ValidatorException;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Objects;

@Named
@SessionScoped
public class DataBean implements Serializable {
    private int x = 0;
    private double y = 0.0;
    private double r = 1.0;

    public Integer[] getXValues() {
        return new Integer[] { -4, -3, -2, -1, 0, 1, 2, 3, 4 };
    }

    public Double[] getRValues() {
        return new Double[] { 1., 1.5, 2., 2.5, 3. };
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void validateDataBeanValue(Object o) {
        if (o == null) {
            FacesMessage message = new FacesMessage("Input values!");
            throw new ValidatorException(message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DataBean dataBean = (DataBean) o;
        return Objects.equals(x, dataBean.x) && Objects.equals(y, dataBean.y) && Objects.equals(r, dataBean.r);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r);
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                '}';
    }
}
