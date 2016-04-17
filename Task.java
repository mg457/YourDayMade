package com.example.maddie.myapp;

import java.io.Serializable;

/**
 * Created by maddie on 4/16/16.
 */
public class Task implements Serializable {

    private String taskName;
    private int q1, q2, q3, q4, q5;
    private double average;


    public Task(int q1, int q2, int q3, int q4, int q5) {
        //assign parameters to inst vars
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        average = ((double) (q1 + q2 + q3 + q4 + q5)) / 5.0;
    }

    public double getAverage() {
        return average;
    }

    public void setName(String newName) {
        taskName = newName;
    }

    public void setQ1(int newVal) {
        q1 = newVal;
    }

    public void setQ2(int newVal) {
        q2 = newVal;
    }

    public void setQ3(int newVal) {
        q3 = newVal;
    }

    public void setQ4(int newVal) {
        q4 = newVal;
    }

    public void setQ5(int newVal) {
        q5 = newVal;
    }

    public String getName() {
        return taskName;
    }

    public int getQ1() {
        return q1;
    }

    public int getQ2() {
        return q2;
    }

    public int getQ3() {
        return q3;
    }

    public int getQ4() {
        return q4;
    }

    public int getQ5() {
        return q5;
    }
}
