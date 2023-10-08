package com.livcorp.trainalpha;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class RestActivity extends AppCompatActivity {

    Button btnNext;
    TextView tvNextExercise, tvNextReps;
    RelativeLayout SkipButtonLayout;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        dialog = new Dialog(this);
        btnNext = findViewById(R.id.btnSkip);
        tvNextExercise = findViewById(R.id.tvNextExercise);
        tvNextReps = findViewById(R.id.tvNextReps);
        SkipButtonLayout = findViewById(R.id.SkipButtonLayout);

        boolean isPointsValid = getIntent().getBooleanExtra("isPointsValid",false);
        int Points = getIntent().getIntExtra("Points",0);
        String Color = getIntent().getStringExtra("Color");
        String workoutName = getIntent().getStringExtra("WorkoutName");
        String TableName = getIntent().getStringExtra("Table");
        int count = getIntent().getIntExtra("num1", 0);
        String NextExerciseName = getIntent().getStringExtra("NextExerciseName");
        String NextExerciseReps = getIntent().getStringExtra("NextExerciseReps");
        double TotalTime = getIntent().getDoubleExtra("TotalTime", 0);
        double TotalCalories = getIntent().getDoubleExtra("TotalCalories", 0);


        tvNextExercise.setText(NextExerciseName);
        tvNextReps.setText(NextExerciseReps);

        if (Color.equals("1_1")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.one_one_btn);
        }
        if (Color.equals("1_2")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.one_two_btn);
        }
        if (Color.equals("1_3")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.one_three_btn);
        }
        if (Color.equals("1_4")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.one_four_btn);
        }
        if (Color.equals("2_1")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.two_one_btn);
        }
        if (Color.equals("2_2")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.two_two_btn);
        }
        if (Color.equals("2_3")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.two_three_btn);
        }
        if (Color.equals("2_4")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.two_four_btn);
        }
        if (Color.equals("3_1")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.three_one_btn);
        }
        if (Color.equals("3_2")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.three_two_btn);
        }
        if (Color.equals("3_3")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.three_three_btn);
        }
        if (Color.equals("3_4")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.three_four_btn);
        }
        if (Color.equals("4_1")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.long_purple_start_btn);
        }
        if (Color.equals("4_2")) {
            SkipButtonLayout.setBackgroundResource(R.drawable.long_blue_start_btn);
        } else {

        }


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num2 = count + 1;
                Intent intent = new Intent(RestActivity.this, WorkoutActivity.class);
                intent.putExtra("num2", num2);
                intent.putExtra("Time", TotalTime);
                intent.putExtra("Calories", TotalCalories);
                intent.putExtra("Color", Color);
                intent.putExtra("Table", TableName);
                intent.putExtra("WorkoutName",workoutName);
                intent.putExtra("Points",Points);
                intent.putExtra("isPointsValid",isPointsValid);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        dialog.setContentView(R.layout.dialog_on_back_pressed);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnBack = dialog.findViewById(R.id.btnTakeMeBack);
        Button btnExit = dialog.findViewById(R.id.btnYesExit);
        dialog.show();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(RestActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}