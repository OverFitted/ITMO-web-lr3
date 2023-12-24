package com.example.lab3.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="lab3_data_table", schema="s387612")
public class ResultEntity {
    private long id;
    private int x;
    private double y;
    private double r;
    private boolean isHit;
    private double executionTime;
    private Timestamp requestTime;

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence-generator")
    @SequenceGenerator(name="sequence-generator", sequenceName="lab3_data_table_id_seq", allocationSize=1)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Column
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Column
    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Column
    public boolean getHit() {
        return isHit;
    }

    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    @Column
    public double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(double executionTime) {
        this.executionTime = executionTime;
    }

    @Column
    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ResultEntity that))
            return false;
        return getId() == that.getId() && Double.compare(getX(), that.getX()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getX(), getY(), getR());
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                '}';
    }
}
