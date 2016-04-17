package com.example.maddie.myapp;

import java.io.Serializable;

/**
 * Created by maddie on 4/16/16.
 */
public class ItemAvg implements Serializable, Comparable<ItemAvg> {

    public String item;
    public double avg;
    public Task task; // TODO: integrate data storage into one class (this class)

    public ItemAvg(String item, double avg, Task task) {
        this.item = item;
        this.avg = avg;
        this.task = task;
    }

    public double getAvg() {
        return avg;
    }


    public Task getTask() {
        return task;
    }

    public void setTask(Task t) {
        task = t;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return item;
    }

    @Override
    public int compareTo(ItemAvg itemAvg) {
        if (getAvg() == itemAvg.getAvg())
            return 0;
        else if (getAvg() > itemAvg.getAvg())
            return -1;
        else
            return 1;
    }
}
