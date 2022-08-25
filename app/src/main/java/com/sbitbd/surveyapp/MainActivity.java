package com.sbitbd.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.color.DynamicColors;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.sbitbd.surveyapp.Survey.survey;
import com.sbitbd.surveyapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button startbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DynamicColors.applyToActivityIfAvailable(this);
        FirebaseAnalytics.getInstance(this);
        initView();
    }

    private void initView(){
        try {
            startbtn = binding.surveybtn;

            startbtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, survey.class)));
        }catch (Exception e){e.printStackTrace();
        }
    }
}