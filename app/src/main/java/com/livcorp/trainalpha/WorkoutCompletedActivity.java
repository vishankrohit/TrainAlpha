package com.livcorp.trainalpha;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.livcorp.trainalpha.Helpers.DBHelper;
import com.livcorp.trainalpha.databinding.ActivityWorkoutCompletedBinding;
import com.google.firebase.auth.FirebaseAuth;

public class WorkoutCompletedActivity extends AppCompatActivity {

    ActivityWorkoutCompletedBinding binding;
    FirebaseAuth auth;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutCompletedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        String ID = auth.getCurrentUser().getUid();
        DBHelper helper = new DBHelper(this);
        Cursor c = helper.getWorkoutDataById(ID);
        dialog = new Dialog(this);

        boolean isPointsValid = getIntent().getBooleanExtra("isPointsValid",false);
        double NewTime = getIntent().getDoubleExtra("TotalTime", 0);
        double NewCalories = getIntent().getDoubleExtra("TotalCalories", 0);
        String exercises = getIntent().getStringExtra("Exercises");

        binding.Calories.setText(String.format("%.1f", NewCalories));
        binding.time.setText(String.format("%.1f", NewTime));
        binding.ExercisesNumber.setText(exercises);

        int CaloriesBurntDatabase = c.getInt(1);
        int WorkoutTimeDatabase = c.getInt(2);
        int WorkoutsCompletedDatabase = c.getInt(3);

        double OldCalories = (double) CaloriesBurntDatabase;
        double OldTime = (double) WorkoutTimeDatabase;

        double TotalCalories = OldCalories + NewCalories;
        double TotalTime = OldTime + NewTime;
        int NewWorkoutsCompleted = WorkoutsCompletedDatabase + 1;

        helper.updateData(
                ID,
                (int)TotalCalories,
                (int)TotalTime,
                NewWorkoutsCompleted
        );

        if(isPointsValid){
            int Points = getIntent().getIntExtra("Points",0);
            int PointsDatabase = c.getInt(4);
            int NewPoints = PointsDatabase + Points;
            helper.updatePoints(ID,NewPoints);
            showDialogPoints(Points);
        }

        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutCompletedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        binding.btnSaveFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WorkoutCompletedActivity.this, "Feedback Saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WorkoutCompletedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showDialogPoints(int Points){
        dialog.setContentView(R.layout.dialog_points_gained);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tvPointsDialog = dialog.findViewById(R.id.tvPointsDialog);
        Button btnCloseDialog = dialog.findViewById(R.id.btnCloseDialog);
        dialog.show();
        tvPointsDialog.setText("+"+Integer.toString(Points)+" Points");
        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}