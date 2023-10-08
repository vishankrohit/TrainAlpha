package com.livcorp.trainalpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.livcorp.trainalpha.databinding.ActivitySignUpOrLogInBinding;

public class SignUpOrLogInActivity extends AppCompatActivity {

    ActivitySignUpOrLogInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivitySignUpOrLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpOrLogInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        binding.tvLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpOrLogInActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}