package com.livcorp.trainalpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.livcorp.trainalpha.Adapters.DiscoverCardAdapter;
import com.livcorp.trainalpha.Adapters.DiscoverLongCardAdapter;
import com.livcorp.trainalpha.Helpers.DBHelper;
import com.livcorp.trainalpha.Models.CardModel;
import com.livcorp.trainalpha.databinding.ActivityDiscoverBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DiscoverActivity extends AppCompatActivity {

    ActivityDiscoverBinding binding;
    Dialog dialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiscoverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavDiscover);
        bottomNavigationView.setSelectedItemId(R.id.Explore);
        dialog = new Dialog(this);
        DBHelper helper = new DBHelper(this);
        auth = FirebaseAuth.getInstance();
        String ID = auth.getCurrentUser().getUid();
        Cursor c = helper.getWorkoutDataById(ID);
        int PointsDatabase = c.getInt(4);

        ArrayList<CardModel> list1 = new ArrayList<>();
        ArrayList<CardModel> list2 = new ArrayList<>();

        list1.add(new CardModel(R.drawable.cardio_up,"Cardio up", "15","3_1","Easy","cWorkoutA",500));
        list1.add(new CardModel(R.drawable.intense, "Intense", "40","3_2","Hard","cWorkoutB",700));
        list1.add(new CardModel(R.drawable.quick_workout, "Quick\nWorkout", "10","3_3","Easy","cWorkoutC",200));
        list1.add(new CardModel(R.drawable.fatal, "Fatal", "45","3_4","Hard","cWorkoutD",750));

        DiscoverCardAdapter adapter1 = new DiscoverCardAdapter(list1,this);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.discoverCards.setAdapter(adapter1);
        binding.discoverCards.setLayoutManager(layoutManager1);

        list2.add(new CardModel(R.drawable.long_card_1,"Push-up Challenge", "30","4_1","Medium","eWorkoutA",1000));
        list2.add(new CardModel(R.drawable.long_card_2,"Crunches Challenge", "30","4_2","Medium","eWorkoutB",1000));

        DiscoverLongCardAdapter adapter2 = new DiscoverLongCardAdapter(list2, this);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.longCards.setAdapter(adapter2);
        binding.longCards.setLayoutManager(layoutManager2);


        binding.btnFullbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardModel model = new CardModel();
                model.setBg(R.drawable.one_card_img);
                model.setWorkoutName("FullBody");
                model.setWorkoutTime("10 min");
                model.setColor("nil");
                model.setDifficulty("Easy");
                model.setTableName("nil");
                model.setPoints(100);
//                showDialogCard(model,PointsDatabase,helper,ID);
                switchActivity(model);
            }
        });

        binding.btnChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardModel model = new CardModel();
                model.setBg(R.drawable.body_chest);
                model.setWorkoutName("CHEST FOCUS");
                model.setWorkoutTime("10 min");
                model.setColor("body");
                model.setDifficulty("Medium");
                model.setTableName("dWorkoutA");
                model.setPoints(150);
//                showDialogCard(model,PointsDatabase,helper,ID);
                switchActivity(model);
            }
        });
        binding.btnAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardModel model = new CardModel();
                model.setBg(R.drawable.body_abs);
                model.setWorkoutName("ABS FOCUS");
                model.setWorkoutTime("10 min");
                model.setColor("body");
                model.setDifficulty("Medium");
                model.setTableName("dWorkoutB");
                model.setPoints(150);
//                showDialogCard(model,PointsDatabase,helper,ID);
                switchActivity(model);
            }
        });
        binding.btnArms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardModel model = new CardModel();
                model.setBg(R.drawable.body_arms);
                model.setWorkoutName("ARMS FOCUS");
                model.setWorkoutTime("10 min");
                model.setColor("body");
                model.setDifficulty("Medium");
                model.setTableName("dWorkoutC");
                model.setPoints(150);
//                showDialogCard(model,PointsDatabase,helper,ID);
                switchActivity(model);
            }
        });
        binding.btnLegsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardModel model = new CardModel();
                model.setBg(R.drawable.body_legs);
                model.setWorkoutName("LEGS FOCUS");
                model.setWorkoutTime("10 min");
                model.setColor("body");
                model.setDifficulty("Medium");
                model.setTableName("dWorkoutD");
                model.setPoints(150);
//                showDialogCard(model,PointsDatabase,helper,ID);
                switchActivity(model);
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiscoverActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Stats:
                        startActivity(new Intent(getApplicationContext(),StatsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Explore:
                        return true;
                }
                return false;
            }
        });

    }
    public void showDialogCard(CardModel model, int UserPoints, DBHelper helper, String ID){
        dialog.setContentView(R.layout.dialog_unlock_workout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnUsePoints = dialog.findViewById(R.id.btnUsePoints);
        TextView tvCostDialog = dialog.findViewById(R.id.tvCostDialog);
        int pointsRequired = model.getPoints();
        tvCostDialog.setText("You need "+pointsRequired+" points to unlock\nthis workout");
        ImageView ivClose = dialog.findViewById(R.id.ivClose);
        dialog.show();
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnUsePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int OldPoints = UserPoints;
                int Cost = model.getPoints();

                if (OldPoints>=Cost){

                    int NewPoints = OldPoints - Cost;
                    helper.updatePoints(ID,NewPoints);

                    Intent intent = new Intent(DiscoverActivity.this, PreviewActivity.class);
                    intent.putExtra("Bg", model.getBg());
                    intent.putExtra("WorkoutName",model.getWorkoutName());
                    intent.putExtra("WorkoutTime",model.getWorkoutTime());
                    intent.putExtra("Color" ,model.getColor());
                    intent.putExtra("Difficulty",model.getDifficulty());
                    intent.putExtra("Table", model.getTableName());
                    intent.putExtra("Points",model.getPoints());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(DiscoverActivity.this, "You do not have enough Points!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void switchActivity(CardModel model){
        Intent intent = new Intent(DiscoverActivity.this, PreviewActivity.class);
        intent.putExtra("Bg", model.getBg());
        intent.putExtra("WorkoutName",model.getWorkoutName());
        intent.putExtra("WorkoutTime",model.getWorkoutTime());
        intent.putExtra("Color" ,model.getColor());
        intent.putExtra("Difficulty",model.getDifficulty());
        intent.putExtra("Table", model.getTableName());
        intent.putExtra("Points",model.getPoints());
        startActivity(intent);
    }

}