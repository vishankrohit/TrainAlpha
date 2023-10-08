package com.livcorp.trainalpha.Models;

public class DataModel {
    private double Calories;
    private double Time;

    public DataModel(double calories, double time) {
        Calories = calories;
        Time = time;
    }
    public DataModel(){}

    public double getCalories() {
        return Calories;
    }

    public void setCalories(double calories) {
        Calories = calories;
    }

    public double getTime() {
        return Time;
    }

    public void setTime(double time) {
        Time = time;
    }
}
