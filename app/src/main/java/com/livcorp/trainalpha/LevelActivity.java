package com.livcorp.trainalpha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.livcorp.trainalpha.Models.UserModel;
import com.livcorp.trainalpha.databinding.ActivityLevelBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LevelActivity extends AppCompatActivity {

    ActivityLevelBinding binding;
    String Level;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        String id = FirebaseAuth.getInstance().getUid();

        boolean EditDetails = getIntent().getBooleanExtra("EditDetails",false);

        binding.btnRookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Level = "Rookie";
                if(EditDetails){
                    updateToDatabase(Level,id);
                }
                else{
                    addDataToDatabase(Level, id);
                }
                Intent intent = new Intent(LevelActivity.this, BmiActivity.class);
                intent.putExtra("EditDetails",EditDetails);
                startActivity(intent);
            }
        });
        binding.btnInShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Level = "In Shape";
                if(EditDetails){
                    updateToDatabase(Level,id);
                }
                else{
                    addDataToDatabase(Level, id);
                }
                Intent intent = new Intent(LevelActivity.this, BmiActivity.class);
                intent.putExtra("EditDetails",EditDetails);
                startActivity(intent);
            }
        });
        binding.btnAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Level = "Advanced";
                if(EditDetails){
                    updateToDatabase(Level,id);
                }
                else{
                    addDataToDatabase(Level, id);
                }
                Intent intent = new Intent(LevelActivity.this, BmiActivity.class);
                intent.putExtra("EditDetails",EditDetails);
                startActivity(intent);
            }
        });

        binding.tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, BmiActivity.class);
                intent.putExtra("EditDetails",EditDetails);
                startActivity(intent);
            }
        });
    }

    public void addDataToDatabase(String Level, String id) {
        UserModel model = new UserModel(Level);
        database.getReference()
                .child("Users")
                .child(id)
                .child("Info")
                .setValue(model);
    }
    public void updateToDatabase(String Level, String id) {
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("level",Level);
        database.getReference()
                .child("Users")
                .child(id)
                .child("Info")
                .updateChildren(obj);
    }

}