package com.livcorp.trainalpha.Classes;

public class Exercise {
    private int id;
    private String ExerciseName;
    private String Gif;
    private String Video;
    private double Calories;
    private String Description;

    public Exercise(int id, String exerciseName, String gif, String video, double calories, String description) {
        this.id = id;
        ExerciseName = exerciseName;
        Gif = gif;
        Video = video;
        Calories = calories;
        Description = description;
    }

    public Exercise() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseName() {
        return ExerciseName;
    }

    public void setExerciseName(String exerciseName) {
        ExerciseName = exerciseName;
    }

    public String getGif() {
        return Gif;
    }

    public void setGif(String gif) {
        Gif = gif;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }

    public double getCalories() {
        return Calories;
    }

    public void setCalories(double calories) {
        Calories = calories;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
