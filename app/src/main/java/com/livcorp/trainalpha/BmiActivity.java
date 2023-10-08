package com.livcorp.trainalpha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.livcorp.trainalpha.Helpers.DBHelper;
import com.livcorp.trainalpha.Models.UserModel;
import com.livcorp.trainalpha.databinding.ActivityBmiBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class BmiActivity extends AppCompatActivity {

    ActivityBmiBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBmiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        String id = FirebaseAuth.getInstance().getUid();
        String ID = auth.getCurrentUser().getUid();
        DBHelper helper = new DBHelper(this);

        boolean EditDetails = getIntent().getBooleanExtra("EditDetails",false);

        binding.btnMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BmiActivity.this, "Male", Toast.LENGTH_SHORT).show();
                sex = "Male";
            }
        });
        binding.btnFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BmiActivity.this, "Female", Toast.LENGTH_SHORT).show();
                sex = "Female";
            }
        });

        binding.tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.Weight.getText().toString().isEmpty()) {
                    binding.Weight.setError("Please enter your weight in kg");
                    return;
                }
                if (binding.Height.getText().toString().isEmpty()) {
                    binding.Height.setError("Please enter your height in cm");
                    return;
                }
                if (binding.Age.getText().toString().isEmpty()) {
                    binding.Age.setError("Please enter your age");
                }
                if(EditDetails){
                    HashMap<String, Object> obj = new HashMap<>();
                    obj.put("weight",binding.Weight.getText().toString());
                    obj.put("height",binding.Height.getText().toString());
                    obj.put("age",binding.Age.getText().toString());
                    obj.put("sex",sex);
                    database.getReference()
                            .child("Users")
                            .child(id)
                            .child("Info")
                            .child("Physical")
                            .updateChildren(obj);
                }
                else{
                    UserModel model = new UserModel(binding.Weight.getText().toString(), binding.Height.getText().toString(), binding.Age.getText().toString(), sex);
                    database.getReference()
                            .child("Users")
                            .child(id)
                            .child("Info")
                            .child("Physical")
                            .setValue(model);
                    helper.insertData(ID,0,0,0,0);
                }

                Intent intent = new Intent(BmiActivity.this, SplashActivity.class);
                startActivity(intent);
            }
        });

    }
}