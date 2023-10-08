package com.livcorp.trainalpha.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "userWorkoutData.db";
    final static int DB_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table workoutData" +
                        "(id text," +
                        "calories integer,"+
                        "workoutTime integer,"+
                        "workoutsCompleted integer,"+
                        "pointsEarned integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists workoutData");
        onCreate(db);
    }

    public void insertData(String id, int calories,int workoutTime,int workoutsCompleted, int pointsEarned){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("calories",calories);
        values.put("workoutTime",workoutTime);
        values.put("workoutsCompleted",workoutsCompleted);
        values.put("pointsEarned", pointsEarned);
        database.insert("workoutData",null, values);
    }

    public Cursor getWorkoutDataById(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery("Select * from workoutData where id = \""+id+"\"",null);
        if(c != null)
            c.moveToFirst();
        return c;
    }

    public void updateData(String id, int calories,int workoutTime,int workoutsCompleted){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("calories",calories);
        values.put("workoutTime",workoutTime);
        values.put("workoutsCompleted",workoutsCompleted);
        database.update("workoutData",values,"id = ?",new String[] {id});
    }

    public void updatePoints(String id, int pointsEarned){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pointsEarned", pointsEarned);
        database.update("workoutData",values,"id = ?",new String[] {id});
    }
}
