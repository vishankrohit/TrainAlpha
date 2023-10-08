package com.livcorp.trainalpha.Models;

public class UserModel {
    private String UserId;
    private String Name, Email, Password;
    private String CaloriesBurnt, WorkoutsCompleted, WorkoutTime, PointsEarned;
    private String Level;
    private String Weight, Height, Age, Sex, Bmi;

    public UserModel(String userId, String name, String email, String password, String caloriesBurnt, String workoutsCompleted, String workoutTime, String pointsEarned, String level, String weight, String height, String age, String sex, String bmi) {
        UserId = userId;
        Name = name;
        Email = email;
        Password = password;
        CaloriesBurnt = caloriesBurnt;
        WorkoutsCompleted = workoutsCompleted;
        WorkoutTime = workoutTime;
        PointsEarned = pointsEarned;
        Level = level;
        Weight = weight;
        Height = height;
        Age = age;
        Sex = sex;
        Bmi = bmi;
    }

    public UserModel(String name, String email, String password) {
        Name = name;
        Email = email;
        Password = password;
    }

    public UserModel(String level) {
        Level = level;
    }

    public UserModel(String weight, String height, String age, String sex) {
        Weight = weight;
        Height = height;
        Age = age;
        Sex = sex;
    }


    public UserModel(){}

    public String getBmi() {
        return Bmi;
    }

    public void setBmi(String bmi) {
        Bmi = bmi;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCaloriesBurnt() {
        return CaloriesBurnt;
    }

    public void setCaloriesBurnt(String caloriesBurnt) {
        CaloriesBurnt = caloriesBurnt;
    }

    public String getWorkoutsCompleted() {
        return WorkoutsCompleted;
    }

    public void setWorkoutsCompleted(String workoutsCompleted) {
        WorkoutsCompleted = workoutsCompleted;
    }

    public String getWorkoutTime() {
        return WorkoutTime;
    }

    public void setWorkoutTime(String workoutTime) {
        WorkoutTime = workoutTime;
    }

    public String getPointsEarned() {
        return PointsEarned;
    }

    public void setPointsEarned(String pointsEarned) {
        PointsEarned = pointsEarned;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }
}
