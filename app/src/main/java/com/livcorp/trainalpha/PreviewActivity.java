package com.livcorp.trainalpha;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.livcorp.trainalpha.Adapters.PreviewAdapter;
import com.livcorp.trainalpha.Classes.Exercise;
import com.livcorp.trainalpha.Helpers.DatabaseHelper;
import com.livcorp.trainalpha.Models.PreviewModel;
import com.livcorp.trainalpha.databinding.ActivityPreviewBinding;

import java.io.IOException;
import java.util.ArrayList;

public class PreviewActivity extends AppCompatActivity {
    ActivityPreviewBinding binding;
    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Exercise> list = new ArrayList<>();
        ArrayList<PreviewModel> workoutList = new ArrayList<>();

        boolean isPointsValid = getIntent().getBooleanExtra("isPointsValid",false);
        boolean isMainActivity = getIntent().getBooleanExtra("isMainActivity",false);
        int Bg = getIntent().getIntExtra("Bg", 0);
        int Points = getIntent().getIntExtra("Points",0);
        String workoutName = getIntent().getStringExtra("WorkoutName");
        String workoutTime = getIntent().getStringExtra("WorkoutTime");
        String Color = getIntent().getStringExtra("Color");
        int level = getIntent().getIntExtra("level",1);
        String difficulty = getIntent().getStringExtra("Difficulty");
        String TableName = getIntent().getStringExtra("Table");
        int lineColor = 0;

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(isMainActivity){
                    intent = new Intent(PreviewActivity.this, MainActivity.class);
                }
                else{
                    intent = new Intent(PreviewActivity.this, DiscoverActivity.class);
                }
                startActivity(intent);
            }
        });

        if (difficulty.equals("Easy")) {
            binding.difficultyIcon3.setVisibility(View.GONE);
            binding.difficultyIcon2.setVisibility(View.GONE);
        }
        if (difficulty.equals("Medium")) {
            binding.difficultyIcon3.setVisibility(View.GONE);
        }
        if (difficulty.equals("nil")) {
            binding.difficultyIcon3.setVisibility(View.GONE);
            binding.difficultyIcon2.setVisibility(View.GONE);
            binding.difficultyIcon1.setVisibility(View.GONE);
            binding.tvDifficulty.setVisibility(View.GONE);
        }

        if (Color.equals("1_1")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.one_one));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.one_one));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.one_one));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.one_one_btn);
            lineColor = R.color.one_one;
        }
        if (Color.equals("1_2")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.one_two));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.one_two));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.one_two));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.one_two_btn);
            lineColor = R.color.one_two;
        }
        if (Color.equals("1_3")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.one_three));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.one_three));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.one_three));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.one_three_btn);
            lineColor = R.color.one_three;
        }
        if (Color.equals("1_4")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.one_four));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.one_four));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.one_four));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.one_four_btn);
            lineColor = R.color.one_four;
        }
        if (Color.equals("2_1")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.two_one));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.two_one));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.two_one));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.two_one_btn);
            lineColor = R.color.two_one;
        }
        if (Color.equals("2_2")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.two_two));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.two_two));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.two_two));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.two_two_btn);
            lineColor = R.color.two_two;
        }
        if (Color.equals("2_3")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.two_three));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.two_three));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.two_three));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.two_three_btn);
            lineColor = R.color.two_three;
        }
        if (Color.equals("2_4")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.two_four));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.two_four));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.two_four));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.two_four_btn);
            lineColor = R.color.two_four;
        }
        if (Color.equals("3_1")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.three_one));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.three_one));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.three_one));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.three_one_btn);
            lineColor = R.color.three_one;
        }
        if (Color.equals("3_2")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.three_two));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.three_two));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.three_two));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.three_two_btn);
            lineColor = R.color.three_two;
        }
        if (Color.equals("3_3")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.three_three));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.three_three));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.three_three));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.three_three_btn);
            lineColor = R.color.three_three;
        }
        if (Color.equals("3_4")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.three_four));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.three_four));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.three_four));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.three_four_btn);
            lineColor = R.color.three_four;
        }
        if (Color.equals("4_1")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.long_purple));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.long_purple));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.long_purple));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.long_purple_start_btn);
            lineColor = R.color.long_purple;
        }
        if (Color.equals("4_2")) {
            binding.difficultyIcon1.setColorFilter(ContextCompat.getColor(this, R.color.long_blue));
            binding.difficultyIcon2.setColorFilter(ContextCompat.getColor(this, R.color.long_blue));
            binding.difficultyIcon3.setColorFilter(ContextCompat.getColor(this, R.color.long_blue));
            binding.StartButtonLayout.setBackgroundResource(R.drawable.long_blue_start_btn);
            lineColor = R.color.long_blue;
        }
        if(Color.equals("body")){
            lineColor = R.color.three_one;
        }

        if (workoutName.equals("FullBody")) {
            lineColor = R.color.three_one;

            list.add(new Exercise(1, "Jumping Jacks", "https://media.giphy.com/media/RgtuKqJ8rPII4qdRjp/giphy.gif", "https://www.youtube.com/watch?v=2W4ZNSwoW_4&list=PLoVy-85EFtK92qMfHTNZi0BAA3T1AbDys&index=2", 9, "Start with your feet together and your arms by your sides, then jump up with your feet apart and your hands overhead. Return to the start position then do the next rep. This exercise provides a full-body workout and works on all your large muscle groups."));
            list.add(new Exercise(2, "Push-ups", "https://media.giphy.com/media/ZcpLmSQs5ACLVcyg5N/giphy.gif", "https://www.youtube.com/watch?v=R08gYyypGto&list=PLoVy-85EFtK92qMfHTNZi0BAA3T1AbDys&index=9", 8, "Lay prone in the ground with arms supporting your body. Keep your body straight while raising and lowering your body with your arms. This exercise works the chest, shoulders, triceps, back and legs."));
            list.add(new Exercise(3, "Diamond push ups", "https://media.giphy.com/media/JZPaw2Y2oENHcZrHja/giphy.gif", "https://www.youtube.com/watch?v=UCmqw3kKZ38", 8.5, "Start in the push-up position. Make a diamond shape with your forefingers and thumbs together under your chest. Then push your body up and down. Remember to keep your body straight."));
            list.add(new Exercise(4, "Push ups and rotation", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/workouts/2016/04/tpushup-1461693226.gif", "https://www.youtube.com/watch?v=Plv5CIclPtQ", 16, "Start in the push-up position. Then go down for a push-up and as you come up, rotate your upper body and extend your right arm upwards. Repeat the exercise with the other arm. It's a great exercise for the chest, shoulders, arms and core."));
            list.add(new Exercise(5, "Burpees", "https://media.giphy.com/media/KxuGSIZU1QZfRiRx4h/giphy.gif", "https://www.youtube.com/watch?v=818SkLAPyKY", 17.2, "Stand with your feet shoulder width apart, then put your hands on the ground and kick your feet backward. Do a quick push-up and then jump up."));
            list.add(new Exercise(6, "Sit-ups", "https://media2.giphy.com/media/eKmtS6cyCggFH0R8qN/giphy.gif", "https://www.youtube.com/watch?v=swOyWKk7Oko", 7, "Lie on your back with your hands behind your ears. Then lift your upper body off the floor and slowly up to the sitting position. Don't tug your neck when you get up. Slowly go back to the start position and repeat the exercise. If your spine hurts, please change to another workout."));
            list.add(new Exercise(7, "Butt bridge", "https://media.giphy.com/media/THOxWu8j7zhgwveMWp/giphy.gif", "https://www.youtube.com/watch?v=9qo48CYN06w", 8, "Lie on your back with your knees bent and feet flat on the floor. Put your arms flat at your sides. Then lift your butt up and down."));
            list.add(new Exercise(8, "Leg raises", "https://media.giphy.com/media/mVpOJcNBGPwzlfkTxJ/giphy.gif", "https://www.youtube.com/watch?v=dGKbTKLnym4", 7, "Lie down on your back, and put your hands beneath your hips for support. Then lift your legs up until they form a right angle with the floor. Slowly bring your legs back down and repeat the exercise."));
            list.add(new Exercise(9, "Squats", "https://media.giphy.com/media/xc7zZmlLXCMwMrziIz/giphy.gif", "https://www.youtube.com/watch?v=42bFodPahBU", 8, "Stand with your feet shoulder width apart and your arms stretched forward, then lower your body until your thighs are parallel with the floor. Your knees should extended in the same direction as your toes. Return to the start position and do the next rep. This works the thighs, hips, quads, hamstrings and lower body."));
            list.add(new Exercise(10, "Jumping Lunges", "https://i.giphy.com/media/TK1zhbrl6KEiFsDq6e/giphy.gif", "https://www.youtube.com/watch?v=hTdcOG9muQk", 8, "Stand with your feet shoulder width apart and your hands on your hips. Take a step forward with your right leg and lower your body until your right thigh is parallel with the floor. Then jump and return and switch to the other leg. This exercise strengthens the quadriceps, gluteus maximus and hamstrings."));
            list.add(new Exercise(11, "Superman", "https://media.giphy.com/media/idRxlUM2oXaBjKK2Nc/giphy.gif", "https://www.youtube.com/watch?v=pGeaBXLwDtw", 7, "Lie on your stomach with your arms extended straight overhead. Alternatively lift your opposite arm and leg."));
            list.add(new Exercise(12, "Hyperextension", "https://media2.giphy.com/media/VPByqa8IPNAZm26neB/giphy.gif", "https://www.youtube.com/watch?v=W9y8xq4Ya_E", 7, "Lie down on your stomach with your toes touching the floor, and your chin on your hand. Raise your chest up as high as possible off the floor. Hold this position for a few seconds and then go back to the start position. Repeat this exercise."));
            list.add(new Exercise(13, "Cobras", "https://thumbs.gfycat.com/DarkDentalJackal-max-1mb.gif", "https://www.youtube.com/watch?v=q46qN4ypiFo", 5, "Lie down on your stomach and bend your elbows with your hands beneath your shoulders. Then push your chest up off the gound as far as possible. Return to the start position and repeat."));


            workoutList.add(new PreviewModel(list.get(0), "25", lineColor));
            workoutList.add(new PreviewModel(list.get(1), "10", lineColor));
            workoutList.add(new PreviewModel(list.get(2), "8", lineColor));
            workoutList.add(new PreviewModel(list.get(3), "8", lineColor));
            workoutList.add(new PreviewModel(list.get(4), "8", lineColor));
            workoutList.add(new PreviewModel(list.get(5), "20", lineColor));
            workoutList.add(new PreviewModel(list.get(6), "12", lineColor));
            workoutList.add(new PreviewModel(list.get(7), "12", lineColor));
            workoutList.add(new PreviewModel(list.get(8), "15", lineColor));
            workoutList.add(new PreviewModel(list.get(9), "12", lineColor));
            workoutList.add(new PreviewModel(list.get(10), "10", lineColor));
            workoutList.add(new PreviewModel(list.get(11), "10", lineColor));
            workoutList.add(new PreviewModel(list.get(12), "20", lineColor));


            PreviewAdapter adapter = new PreviewAdapter(workoutList, this);
            binding.recyclerView.setAdapter(adapter);


            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            binding.recyclerView.setLayoutManager(layoutManager);
        } else {
            DatabaseHelper myDbHelper = new DatabaseHelper(PreviewActivity.this);
            try {
                myDbHelper.createDataBase();
            } catch (IOException ioe) {
                throw new Error("Unable to create database");
            }
            try {
                myDbHelper.openDataBase();
            } catch (SQLException sqle) {
                throw sqle;
            }

            c = myDbHelper.query(TableName, null, null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    Exercise model = new Exercise();
                    model.setId(Integer.parseInt(c.getString(0)));
                    model.setExerciseName(c.getString(1));
                    model.setGif(c.getString(2));
                    model.setVideo(c.getString(3));
                    model.setCalories(c.getDouble(4));
                    model.setDescription(c.getString(5));
                    list.add(model);
                } while (c.moveToNext());
            }


            if (TableName.equals("aWorkoutA")) {
                workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(2), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(11), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(12), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(14), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(15), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(16), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(17), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(18), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(19), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(20), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(21), "6", lineColor));
                workoutList.add(new PreviewModel(list.get(22), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(23), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(24), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(25), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(26), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(27), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(28), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(29), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(30), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(31), "25", lineColor));
            }

            if (TableName.equals("aWorkoutB")) {
                workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(2), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "18", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(11), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(12), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "20", lineColor));
                workoutList.add(new PreviewModel(list.get(14), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(15), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(16), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(17), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(18), "18", lineColor));
                workoutList.add(new PreviewModel(list.get(19), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(20), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(21), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(22), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(23), "20", lineColor));
                workoutList.add(new PreviewModel(list.get(24), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(25), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(26), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(27), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(28), "18", lineColor));
                workoutList.add(new PreviewModel(list.get(29), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(30), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(31), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(32), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(33), "20", lineColor));
            }

            if (TableName.equals("aWorkoutC")) {
                workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(2), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(11), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(12), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(14), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(15), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(16), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(17), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(18), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(19), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(20), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(21), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(22), "25", lineColor));
            }

            if (TableName.equals("aWorkoutD")) {
                workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                workoutList.add(new PreviewModel(list.get(2), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(11), "10", lineColor));

                workoutList.add(new PreviewModel(list.get(12), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(14), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(15), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(16), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(17), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(18), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(19), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(20), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(21), "10", lineColor));

                workoutList.add(new PreviewModel(list.get(22), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(23), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(24), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(25), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(26), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(27), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(28), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(29), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(30), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(31), "10", lineColor));
            }

            if (TableName.equals("bWorkoutA")) {

                if(level == 3){
                    workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                    workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                    workoutList.add(new PreviewModel(list.get(2), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(3), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(4), "16", lineColor));
                    workoutList.add(new PreviewModel(list.get(5), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(6), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(7), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(8), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(9), "12", lineColor));

                    workoutList.add(new PreviewModel(list.get(10), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(11), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(12), "16", lineColor));
                    workoutList.add(new PreviewModel(list.get(13), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(14), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(15), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(16), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(17), "12", lineColor));

                    workoutList.add(new PreviewModel(list.get(18), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(19), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(20), "16", lineColor));
                    workoutList.add(new PreviewModel(list.get(21), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(22), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(23), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(24), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(25), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(26), "25", lineColor));
                }

                if(level == 2){
                    workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                    workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                    workoutList.add(new PreviewModel(list.get(2), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(3), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(4), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(5), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(6), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(7), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(8), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(9), "10", lineColor));

                    workoutList.add(new PreviewModel(list.get(10), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(11), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(12), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(13), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(14), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(15), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(16), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(17), "10", lineColor));

                    workoutList.add(new PreviewModel(list.get(18), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(19), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(20), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(21), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(22), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(23), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(24), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(25), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(26), "25", lineColor));
                }

                else {
                    workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                    workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                    workoutList.add(new PreviewModel(list.get(2), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(3), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(4), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(5), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(6), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(7), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(8), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(9), "6", lineColor));

                    workoutList.add(new PreviewModel(list.get(10), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(11), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(12), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(13), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(14), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(15), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(16), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(17), "6", lineColor));

                    workoutList.add(new PreviewModel(list.get(18), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(19), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(20), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(21), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(22), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(23), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(24), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(25), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(26), "25", lineColor));
                }

            }

            if (TableName.equals("bWorkoutB")) {

                if(level==3){
                    workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                    workoutList.add(new PreviewModel(list.get(1), "25", lineColor));
                    workoutList.add(new PreviewModel(list.get(2), "24", lineColor));

                    workoutList.add(new PreviewModel(list.get(3), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(4), "24", lineColor));
                    workoutList.add(new PreviewModel(list.get(5), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(6), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(7), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(8), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(9), "18", lineColor));
                    workoutList.add(new PreviewModel(list.get(10), "32", lineColor));

                    workoutList.add(new PreviewModel(list.get(11), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(12), "24", lineColor));
                    workoutList.add(new PreviewModel(list.get(13), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(14), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(15), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(16), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(17), "18", lineColor));
                    workoutList.add(new PreviewModel(list.get(18), "32", lineColor));

                    workoutList.add(new PreviewModel(list.get(19), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(20), "24", lineColor));
                    workoutList.add(new PreviewModel(list.get(21), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(22), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(23), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(24), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(25), "18", lineColor));
                    workoutList.add(new PreviewModel(list.get(26), "32", lineColor));

                    workoutList.add(new PreviewModel(list.get(27), "25", lineColor));
                    workoutList.add(new PreviewModel(list.get(28), "12", lineColor));
                }

                if(level==2){
                    workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                    workoutList.add(new PreviewModel(list.get(1), "25", lineColor));
                    workoutList.add(new PreviewModel(list.get(2), "20", lineColor));

                    workoutList.add(new PreviewModel(list.get(3), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(4), "20", lineColor));
                    workoutList.add(new PreviewModel(list.get(5), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(6), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(7), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(8), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(9), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(10), "32", lineColor));

                    workoutList.add(new PreviewModel(list.get(11), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(12), "20", lineColor));
                    workoutList.add(new PreviewModel(list.get(13), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(14), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(15), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(16), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(17), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(18), "32", lineColor));

                    workoutList.add(new PreviewModel(list.get(19), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(20), "20", lineColor));
                    workoutList.add(new PreviewModel(list.get(21), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(22), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(23), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(24), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(25), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(26), "32", lineColor));

                    workoutList.add(new PreviewModel(list.get(27), "20", lineColor));
                    workoutList.add(new PreviewModel(list.get(28), "12", lineColor));
                }

                else{
                    workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                    workoutList.add(new PreviewModel(list.get(1), "25", lineColor));
                    workoutList.add(new PreviewModel(list.get(2), "16", lineColor));

                    workoutList.add(new PreviewModel(list.get(3), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(4), "16", lineColor));
                    workoutList.add(new PreviewModel(list.get(5), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(6), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(7), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(8), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(9), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(10), "20", lineColor));

                    workoutList.add(new PreviewModel(list.get(11), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(12), "16", lineColor));
                    workoutList.add(new PreviewModel(list.get(13), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(14), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(15), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(16), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(17), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(18), "20", lineColor));

                    workoutList.add(new PreviewModel(list.get(19), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(20), "16", lineColor));
                    workoutList.add(new PreviewModel(list.get(21), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(22), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(23), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(24), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(25), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(26), "20", lineColor));

                    workoutList.add(new PreviewModel(list.get(27), "18", lineColor));
                    workoutList.add(new PreviewModel(list.get(28), "12", lineColor));
                }

            }

            if (TableName.equals("bWorkoutC")) {

                if(level==2){
                    workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                    workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                    workoutList.add(new PreviewModel(list.get(2), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(3), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(4), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(5), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(6), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(7), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(8), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(9), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(10), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(11), "20", lineColor));
                    workoutList.add(new PreviewModel(list.get(12), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(13), "12", lineColor));

                    workoutList.add(new PreviewModel(list.get(14), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(15), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(16), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(17), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(18), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(19), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(20), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(21), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(22), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(23), "20", lineColor));
                    workoutList.add(new PreviewModel(list.get(24), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(25), "12", lineColor));

                    workoutList.add(new PreviewModel(list.get(26), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(27), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(28), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(29), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(30), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(31), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(32), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(33), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(34), "25", lineColor));
                }

                if(level==3){
                    workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                    workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                    workoutList.add(new PreviewModel(list.get(2), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(3), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(4), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(5), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(6), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(7), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(8), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(9), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(10), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(11), "20", lineColor));
                    workoutList.add(new PreviewModel(list.get(12), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(13), "12", lineColor));

                    workoutList.add(new PreviewModel(list.get(14), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(15), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(16), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(17), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(18), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(19), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(20), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(21), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(22), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(23), "20", lineColor));
                    workoutList.add(new PreviewModel(list.get(24), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(25), "12", lineColor));

                    workoutList.add(new PreviewModel(list.get(26), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(27), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(28), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(29), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(30), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(31), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(32), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(33), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(34), "25", lineColor));
                }

                else{
                    workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                    workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                    workoutList.add(new PreviewModel(list.get(2), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(3), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(4), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(5), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(6), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(7), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(8), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(9), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(10), "3", lineColor));
                    workoutList.add(new PreviewModel(list.get(11), "15", lineColor));
                    workoutList.add(new PreviewModel(list.get(12), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(13), "8", lineColor));

                    workoutList.add(new PreviewModel(list.get(14), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(15), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(16), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(17), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(18), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(19), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(20), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(21), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(22), "3", lineColor));
                    workoutList.add(new PreviewModel(list.get(23), "15", lineColor));
                    workoutList.add(new PreviewModel(list.get(24), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(25), "4", lineColor));

                    workoutList.add(new PreviewModel(list.get(26), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(27), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(28), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(29), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(30), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(31), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(32), "4", lineColor));
                    workoutList.add(new PreviewModel(list.get(33), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(34), "10", lineColor));
                }

            }

            if (TableName.equals("bWorkoutD")) {
                if(level==3){
                    workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                    workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                    workoutList.add(new PreviewModel(list.get(2), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(3), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(4), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(5), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(6), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(7), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(8), "18", lineColor));
                    workoutList.add(new PreviewModel(list.get(9), "20", lineColor));

                    workoutList.add(new PreviewModel(list.get(10), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(11), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(12), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(13), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(14), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(15), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(16), "18", lineColor));
                    workoutList.add(new PreviewModel(list.get(17), "20", lineColor));

                    workoutList.add(new PreviewModel(list.get(18), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(19), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(20), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(21), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(22), "12", lineColor));
                    workoutList.add(new PreviewModel(list.get(23), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(24), "18", lineColor));
                    workoutList.add(new PreviewModel(list.get(25), "20", lineColor));

                    workoutList.add(new PreviewModel(list.get(26), "12", lineColor));
                }

                if(level==2){
                    workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                    workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                    workoutList.add(new PreviewModel(list.get(2), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(3), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(4), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(5), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(6), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(7), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(8), "16", lineColor));
                    workoutList.add(new PreviewModel(list.get(9), "18", lineColor));

                    workoutList.add(new PreviewModel(list.get(10), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(11), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(12), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(13), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(14), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(15), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(16), "16", lineColor));
                    workoutList.add(new PreviewModel(list.get(17), "18", lineColor));

                    workoutList.add(new PreviewModel(list.get(18), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(19), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(20), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(21), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(22), "10", lineColor));
                    workoutList.add(new PreviewModel(list.get(23), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(24), "16", lineColor));
                    workoutList.add(new PreviewModel(list.get(25), "18", lineColor));

                    workoutList.add(new PreviewModel(list.get(26), "12", lineColor));
                }

                else{
                    workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                    workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                    workoutList.add(new PreviewModel(list.get(2), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(3), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(4), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(5), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(6), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(7), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(8), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(9), "16", lineColor));

                    workoutList.add(new PreviewModel(list.get(10), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(11), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(12), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(13), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(14), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(15), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(16), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(17), "16", lineColor));

                    workoutList.add(new PreviewModel(list.get(18), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(19), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(20), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(21), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(22), "8", lineColor));
                    workoutList.add(new PreviewModel(list.get(23), "6", lineColor));
                    workoutList.add(new PreviewModel(list.get(24), "14", lineColor));
                    workoutList.add(new PreviewModel(list.get(25), "16", lineColor));

                    workoutList.add(new PreviewModel(list.get(26), "8", lineColor));
                }

            }

            if (TableName.equals("cWorkoutA")) {
                workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(2), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "20", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "20", lineColor));
                workoutList.add(new PreviewModel(list.get(11), "22", lineColor));
                workoutList.add(new PreviewModel(list.get(12), "24", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "25", lineColor));
            }

            if (TableName.equals("cWorkoutB")) {
                workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                workoutList.add(new PreviewModel(list.get(2), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "24", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "24", lineColor));
                workoutList.add(new PreviewModel(list.get(11), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(12), "32", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(14), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(15), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(16), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(17), "18", lineColor));
                workoutList.add(new PreviewModel(list.get(18), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(19), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(20), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(21), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(22), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(23), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(24), "18", lineColor));
                workoutList.add(new PreviewModel(list.get(25), "18", lineColor));
                workoutList.add(new PreviewModel(list.get(26), "20", lineColor));
            }

            if (TableName.equals("cWorkoutC")) {
                workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(2), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "15", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "15", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "12", lineColor));
            }

            if (TableName.equals("cWorkoutD")) {
                workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "25", lineColor));
                workoutList.add(new PreviewModel(list.get(2), "24", lineColor));

                workoutList.add(new PreviewModel(list.get(3), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "20", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "26", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(11), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(12), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(14), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(15), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(16), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(17), "20", lineColor));
                workoutList.add(new PreviewModel(list.get(18), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(19), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(20), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(21), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(22), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(23), "22", lineColor));
                workoutList.add(new PreviewModel(list.get(24), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(25), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(26), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(27), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(28), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(29), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(30), "20", lineColor));
            }

            if (TableName.equals("dWorkoutA")) {
                workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                workoutList.add(new PreviewModel(list.get(2), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "12", lineColor));

                workoutList.add(new PreviewModel(list.get(10), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(11), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(12), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(14), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(15), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(16), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(17), "12", lineColor));

                workoutList.add(new PreviewModel(list.get(18), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(19), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(20), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(21), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(22), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(23), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(24), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(25), "12", lineColor));

                workoutList.add(new PreviewModel(list.get(26), "20", lineColor));
            }

            if (TableName.equals("dWorkoutB")) {
                workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                workoutList.add(new PreviewModel(list.get(2), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "20", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "26", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(11), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(12), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(14), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(15), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(16), "12", lineColor));

                workoutList.add(new PreviewModel(list.get(17), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(18), "20", lineColor));
                workoutList.add(new PreviewModel(list.get(19), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(20), "26", lineColor));
                workoutList.add(new PreviewModel(list.get(21), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(22), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(23), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(24), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(25), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(26), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(27), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(28), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(29), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(30), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(31), "12", lineColor));

                workoutList.add(new PreviewModel(list.get(32), "20", lineColor));
            }

            if (TableName.equals("dWorkoutC")) {
                workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                workoutList.add(new PreviewModel(list.get(2), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "22", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(11), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(12), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(14), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(15), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(16), "20", lineColor));

                workoutList.add(new PreviewModel(list.get(17), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(18), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(19), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(20), "8", lineColor));
                workoutList.add(new PreviewModel(list.get(21), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(22), "22", lineColor));
                workoutList.add(new PreviewModel(list.get(23), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(24), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(25), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(26), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(27), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(28), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(29), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(30), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(31), "20", lineColor));
            }

            if (TableName.equals("dWorkoutD")) {
                workoutList.add(new PreviewModel(list.get(0), "30", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "25", lineColor));

                workoutList.add(new PreviewModel(list.get(2), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "20", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(11), "20", lineColor));

                workoutList.add(new PreviewModel(list.get(12), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(14), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(15), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(16), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(17), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(18), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(19), "20", lineColor));
                workoutList.add(new PreviewModel(list.get(20), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(21), "20", lineColor));

                workoutList.add(new PreviewModel(list.get(22), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(23), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(24), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(25), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(26), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(27), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(28), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(29), "20", lineColor));
                workoutList.add(new PreviewModel(list.get(30), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(31), "20", lineColor));
            }

            if (TableName.equals("eWorkoutA")) {
                workoutList.add(new PreviewModel(list.get(0), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(2), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(8), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "14", lineColor));

                workoutList.add(new PreviewModel(list.get(11), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(12), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(14), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(15), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(16), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(17), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(18), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(19), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(20), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(21), "14", lineColor));

                workoutList.add(new PreviewModel(list.get(22), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(23), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(24), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(25), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(26), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(27), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(28), "10", lineColor));
                workoutList.add(new PreviewModel(list.get(29), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(30), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(31), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(32), "14", lineColor));
            }

            if (TableName.equals("eWorkoutB")) {
                workoutList.add(new PreviewModel(list.get(0), "24", lineColor));
                workoutList.add(new PreviewModel(list.get(1), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(2), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(3), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(4), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(5), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(6), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(7), "12", lineColor));

                workoutList.add(new PreviewModel(list.get(8), "24", lineColor));
                workoutList.add(new PreviewModel(list.get(9), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(10), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(11), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(12), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(13), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(14), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(15), "12", lineColor));

                workoutList.add(new PreviewModel(list.get(16), "24", lineColor));
                workoutList.add(new PreviewModel(list.get(17), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(18), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(19), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(20), "16", lineColor));
                workoutList.add(new PreviewModel(list.get(21), "12", lineColor));
                workoutList.add(new PreviewModel(list.get(22), "14", lineColor));
                workoutList.add(new PreviewModel(list.get(23), "12", lineColor));
            }


            PreviewAdapter adapter = new PreviewAdapter(workoutList, this);
            binding.recyclerView.setAdapter(adapter);


            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            binding.recyclerView.setLayoutManager(layoutManager);
        }

        int listSize = list.size();

        binding.tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreviewActivity.this, WorkoutActivity.class);
                intent.putExtra("Color", Color);
                intent.putExtra("WorkoutName",workoutName);
                intent.putExtra("Table", TableName);
                intent.putExtra("WorkoutName", workoutName);
                intent.putExtra("Points",Points);
                intent.putExtra("level",level);
                intent.putExtra("isPointsValid",isPointsValid);
                startActivity(intent);
            }
        });

        binding.workoutName.setText(workoutName);
        binding.workoutTime.setText(workoutTime);
        binding.toolbarBg.setImageResource(Bg);
        String ListSize = Integer.toString(listSize);
        binding.ExercisesNumber.setText(ListSize);
        binding.tvDifficulty.setText(difficulty);
    }
}