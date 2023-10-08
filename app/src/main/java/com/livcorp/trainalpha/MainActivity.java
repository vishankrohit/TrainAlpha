package com.livcorp.trainalpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.livcorp.trainalpha.Adapters.CardAdapter;
import com.livcorp.trainalpha.Helpers.DBHelper;
import com.livcorp.trainalpha.Models.CardModel;
import com.livcorp.trainalpha.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth;
    DatabaseReference reference;
    String level;
    int Level = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavMain);
        bottomNavigationView.setSelectedItemId(R.id.home);
        auth = FirebaseAuth.getInstance();
        final String ID = auth.getCurrentUser().getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(ID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                level = snapshot.child("Info").child("level").getValue().toString();
                if(level.equals("Advanced")){
                    Level = 3;
                }
                if(level.equals("In Shape")){
                    Level = 2;
                }
                else{
                    Level = 1;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayList<CardModel> list = new ArrayList<>();
        ArrayList<CardModel> list2 = new ArrayList<>();
        DBHelper helper = new DBHelper(this);
        Cursor c = helper.getWorkoutDataById(ID);

        int CaloriesBurntDatabase = c.getInt(1);
        int WorkoutTimeDatabase = c.getInt(2);
        int WorkoutsCompletedDatabase = c.getInt(3);
        int PointsDatabase = c.getInt(4);

        binding.caloriesBurnt.setText(Integer.toString(CaloriesBurntDatabase));
        binding.timeWorkedOut.setText(Integer.toString(WorkoutTimeDatabase));
        binding.workoutsCompleted.setText(Integer.toString(WorkoutsCompletedDatabase));
        binding.pointsEarned.setText(Integer.toString(PointsDatabase));

        if(Level==3){
            list.add(new CardModel(R.drawable.leg_day, "Leg Day", "30","1_1","Hard","bWorkoutD",100));
            list.add(new CardModel(R.drawable.arms_back, "Arms & Back", "35","1_2","Hard","bWorkoutC",100));
            list.add(new CardModel(R.drawable.abs_day, "Abs Day", "30","1_3","Hard","bWorkoutB",100));
            list.add(new CardModel(R.drawable.chest_legs, "Chest & Legs", "25","1_4","Hard","bWorkoutA",100));
        }
        if(Level==2){
            list.add(new CardModel(R.drawable.leg_day, "Leg Day", "30","1_1","Medium","bWorkoutD",100));
            list.add(new CardModel(R.drawable.arms_back, "Arms & Back", "35","1_2","Medium","bWorkoutC",100));
            list.add(new CardModel(R.drawable.abs_day, "Abs Day", "30","1_3","Medium","bWorkoutB",100));
            list.add(new CardModel(R.drawable.chest_legs, "Chest & Legs", "25","1_4","Medium","bWorkoutA",100));
        }
        else{
            list.add(new CardModel(R.drawable.leg_day, "Leg Day", "30","1_1","Easy","bWorkoutD",100));
            list.add(new CardModel(R.drawable.arms_back, "Arms & Back", "35","1_2","Easy","bWorkoutC",100));
            list.add(new CardModel(R.drawable.abs_day, "Abs Day", "30","1_3","Easy","bWorkoutB",100));
            list.add(new CardModel(R.drawable.chest_legs, "Chest & Legs", "25","1_4","Easy","bWorkoutA",100));
        }

        list2.add(new CardModel(R.drawable.lowerbody,"Lower Body", "30","2_1","Medium","aWorkoutD",100));
        list2.add(new CardModel(R.drawable.shoulder_back, "Back Workout", "25","2_2","Hard","aWorkoutC",100));
        list2.add(new CardModel(R.drawable.upperbody, "Upper Body", "30","2_3","Easy","aWorkoutA",100));
        list2.add(new CardModel(R.drawable.abs_legs, "Abs & Legs", "40","2_4","Easy","aWorkoutB",100));



        CardAdapter adapter = new CardAdapter(list, this, Level);
        binding.recyclerView1.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.recyclerView1.setLayoutManager(layoutManager);

        CardAdapter adapter2 = new CardAdapter(list2,this, Level);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        binding.recyclerView2.setAdapter(adapter2);
        binding.recyclerView2.setLayoutManager(layoutManager2);

        binding.tvSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiscoverActivity.class);
                startActivity(intent);
            }
        });
        binding.tvSeeAll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiscoverActivity.class);
                startActivity(intent);
            }
        });
        binding.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Explore:
                        startActivity(new Intent(getApplicationContext(),DiscoverActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Stats:
                        startActivity(new Intent(getApplicationContext(),StatsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Home:
                        return true;
                }
                return false;
            }
        });
    }
}