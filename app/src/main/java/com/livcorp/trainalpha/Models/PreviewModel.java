package com.livcorp.trainalpha.Models;

import com.livcorp.trainalpha.Classes.Exercise;

import java.io.Serializable;

public class PreviewModel implements Serializable {
    private Exercise exercise;
    private String Reps;
    private int lineColor;

    public PreviewModel(Exercise exercise, String reps, int lineColor) {
        this.exercise = exercise;
        Reps = reps;
        this.lineColor = lineColor;
    }

    public PreviewModel(Exercise exercise, String reps) {
        this.exercise = exercise;
        Reps = reps;
    }

    public PreviewModel(){}

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public String getReps() {
        return Reps;
    }

    public void setReps(String reps) {
        Reps = reps;
    }
}
