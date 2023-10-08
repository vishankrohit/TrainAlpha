package com.livcorp.trainalpha;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.livcorp.trainalpha.Models.UserModel;
import com.livcorp.trainalpha.databinding.ActivitySettingsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity {

    ActivitySettingsBinding binding;
    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        String id = auth.getCurrentUser().getUid();
        String email = auth.getCurrentUser().getEmail();
        UserModel model = new UserModel();

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue().toString();
                String weight = snapshot.child("Info").child("Physical").child("weight").getValue().toString();
                String height = snapshot.child("Info").child("Physical").child("height").getValue().toString();
                String sex = snapshot.child("Info").child("Physical").child("sex").getValue().toString();
                String age = snapshot.child("Info").child("Physical").child("age").getValue().toString();

                double Weight = Double.parseDouble(weight);
                double Height = Double.parseDouble(height) / 100;
                double Bmi = Weight / (Height * Height);

                binding.tvName.setText(name);
                binding.tvEmail.setText(email);
                if (Bmi < 18) {
                    binding.tvBmi.setText("Under Weight");
                }
                if (Bmi > 25) {
                    binding.tvBmi.setText("Over Weight");
                } else {
                    binding.tvBmi.setText("Healthy");
                }

                model.setName(name);
                model.setWeight(weight);
                model.setHeight(height);
                model.setSex(sex);
                model.setAge(age);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.btnEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, LevelActivity.class);
                intent.putExtra("EditDetails",true);
                startActivity(intent);
            }
        });

        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT,"TrainAlpha");
                    String shareMessage = "https://play.google.com/store/apps/details?=" +BuildConfig.APPLICATION_ID+ "\n\n";
                    intent.putExtra(Intent.EXTRA_TEXT,shareMessage);
                    startActivity(Intent.createChooser(intent,"Share with"));
                }
                catch (Exception e){
                    Toast.makeText(SettingsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btnRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(SettingsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(SettingsActivity.this, SignUpOrLogInActivity.class);
                startActivity(intent);
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}