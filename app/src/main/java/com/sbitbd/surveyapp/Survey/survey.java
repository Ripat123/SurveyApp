package com.sbitbd.surveyapp.Survey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;
import com.sbitbd.surveyapp.databinding.ActivitySurveyBinding;
import com.sbitbd.surveyapp.di.SurveyApplication;

import javax.inject.Inject;

public class survey extends AppCompatActivity {

    private ActivitySurveyBinding binding;
    @Inject
    surveyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySurveyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DynamicColors.applyToActivityIfAvailable(this);
        initView();
    }

    private void initView(){
        try {
            ((SurveyApplication) getApplicationContext()).getAppComponent().inject(this);

            viewModel.liveData().observe(this,surveyList -> {
                Toast.makeText(this, surveyList.getSurveyData().get(1).getReferTo(), Toast.LENGTH_SHORT).show();
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}