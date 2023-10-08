package com.livcorp.trainalpha.Models;

public class CardModel {
    private int bg;
    private String workoutName;
    private String workoutTime;
    private String color;
    private String difficulty;
    private String tableName;
    private int costOrPoints;

    public CardModel(int bg, String workoutName, String workoutTime, String color, String difficulty, String tableName, int cost) {
        this.bg = bg;
        this.workoutName = workoutName;
        this.workoutTime = workoutTime;
        this.color = color;
        this.difficulty = difficulty;
        this.tableName = tableName;
        this.costOrPoints = cost;
    }


    public CardModel(){}

    public int getPoints() {
        return costOrPoints;
    }

    public void setPoints(int points) {
        this.costOrPoints = points;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutTime() {
        return workoutTime;
    }

    public void setWorkoutTime(String workoutTime) {
        this.workoutTime = workoutTime;
    }
}
