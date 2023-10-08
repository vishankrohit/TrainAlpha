package com.livcorp.trainalpha.Models;

public class WorkoutModel {
    private int Calories;
    private int Time;
    private int Workouts;
    private int points;

    public WorkoutModel(int calories, int time, int workouts, int points) {
        Calories = calories;
        Time = time;
        Workouts = workouts;
        this.points = points;
    }

    public WorkoutModel(){}

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) {
        Calories = calories;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public int getWorkouts() {
        return Workouts;
    }

    public void setWorkouts(int workouts) {
        Workouts = workouts;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
