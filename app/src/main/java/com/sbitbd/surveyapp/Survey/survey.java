package com.sbitbd.surveyapp.Survey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.sbitbd.surveyapp.Model.Options;
import com.sbitbd.surveyapp.Model.SurveyData;
import com.sbitbd.surveyapp.Model.SurveyList;
import com.sbitbd.surveyapp.R;
import com.sbitbd.surveyapp.databinding.ActivitySurveyBinding;
import com.sbitbd.surveyapp.di.SurveyApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class survey extends AppCompatActivity {

    private ActivitySurveyBinding binding;
    @Inject
    surveyViewModel viewModel;
    private ConstraintLayout layout1,layout2,layout3,layout4,layout5,layout6,parent_lay;
    private TextView question;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5;
    private Button nextbtn,prevbtn,cam_open;
    private EditText ans1,ans2;
    private CheckBox checkBox1,checkBox2,checkBox3;
    private AutoCompleteTextView autoCompleteTextView;
    private ImageView imageView;
    private List<String> strings = new ArrayList<>();
    private MaterialCardView close_card;
    private LinearProgressIndicator progressIndicator;
    private int count,referIndex,currentIndex=0;
    private List<String> list_count = new ArrayList<>();
    private ProgressDialog progressDialog;
    private SurveyList surveyList;
    private RadioGroup radioGroup;
    private boolean radioCheck = false;
    private String q1;

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
            progressDialog = ProgressDialog.show(survey.this,"","Loading",false,false);
            layout1 = binding.firstLay;
            layout2 = binding.secondLay;
            layout3 = binding.thirdLay;
            layout4 = binding.fourthLay;
            layout5 = binding.fifthLay;
            layout6 = binding.sixthLay;
            parent_lay = binding.parentLay;
            question = binding.q1;
            radioButton1 = binding.c1;
            radioButton2 = binding.c2;
            radioButton3 = binding.c3;
            radioButton4 = binding.c4;
            radioButton5 = binding.c5;
            radioGroup = binding.multipleRadio;
            nextbtn = binding.nextbtn;
            prevbtn = binding.prevbtn;
            cam_open = binding.openCam;
            ans1 = binding.answer;
            ans2 = binding.numIn;
            autoCompleteTextView = binding.ansType;
            imageView = binding.img;
            checkBox1 = binding.checkbox1;
            checkBox2 = binding.checkbox2;
            checkBox3 = binding.checkbox3;
            close_card = binding.closeCard;
            progressIndicator = binding.progressIndicator;
            viewModel.liveData().observe(this, this::setData);
            nextbtn.setOnClickListener(v -> {
                nextForm();
            });
//            prevbtn.setOnClickListener(v -> {
//                if (list_count.size() > 1 && count > -1){
//                    list_count.remove(count);
//                    count--;
//                    previousLay(list_count.get(count));
//                }
//            });
            close_card.setOnClickListener(v -> finish());
            cam_open.setOnClickListener(v -> {
                
            });
            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                radioCheck = true;
                switch (checkedId){
                    case R.id.c1:
                        q1 = radioButton1.getText().toString();
                        referIndex=0;
                        break;
                    case R.id.c2:
                        q1 = radioButton2.getText().toString();
                        referIndex=1;
                        break;
                    case R.id.c3:
                        q1 = radioButton3.getText().toString();
                        referIndex=2;
                        break;
                    case R.id.c4:
                        q1 = radioButton4.getText().toString();
                        referIndex=3;
                        break;
                    case R.id.c5:
                        q1 = radioButton5.getText().toString();
                        referIndex=4;
                        break;
                }
            });
            autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> referIndex = position);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setData(SurveyList surveyList){
        try {
            if (surveyList != null) {
                this.surveyList = surveyList;
                count=0;
                list_count.add(surveyList.getSurveyData().get(0).getId());
                question.setText(surveyList.getSurveyData().get(0).getQuestion());
                radioButton1.setText(surveyList.getSurveyData().get(0).getOptions().get(0).getValue());
                radioButton2.setText(surveyList.getSurveyData().get(0).getOptions().get(1).getValue());
                radioButton3.setText(surveyList.getSurveyData().get(0).getOptions().get(2).getValue());
                radioButton4.setText(surveyList.getSurveyData().get(0).getOptions().get(3).getValue());
                radioButton5.setText(surveyList.getSurveyData().get(0).getOptions().get(4).getValue());
                checkBox1.setText(surveyList.getSurveyData().get(3).getOptions().get(0).getValue());
                checkBox2.setText(surveyList.getSurveyData().get(3).getOptions().get(1).getValue());
                checkBox3.setText(surveyList.getSurveyData().get(3).getOptions().get(2).getValue());
                for (Options options :
                        surveyList.getSurveyData().get(2).getOptions()) {
                    strings.add(options.getValue());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(survey.this, android.R.layout
                        .simple_dropdown_item_1line,strings);
                autoCompleteTextView.setAdapter(adapter);
                progressDialog.dismiss();
            }else {
                progressDialog.dismiss();
                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void nextForm(){
        try {
            SurveyData surveyData = surveyList.getSurveyData().get(currentIndex);
            switch (surveyData.getId()){
                case "1":
                    Q1(surveyData);
                    break;
                case "2":
                    Q2(surveyData);
                    break;
                case "3":
                    Q3(surveyData);
                    break;
                case "4":
                    Q4(surveyData);
                    break;
                case "5":
                    Q5(surveyData);
                    break;
                case "6":

                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void Q1(SurveyData surveyData){
        try {
            if (surveyData.getRequired()){
                if (radioCheck){
                    prevbtn.setEnabled(true);
                    nextLay(surveyData.getOptions().get(referIndex).getReferTo());
                }else Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show();
            }else {
                prevbtn.setEnabled(true);
                nextLay(surveyData.getOptions().get(referIndex).getReferTo());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void Q2(SurveyData surveyData){
        try {
            if (surveyData.getRequired()){
                if (!ans1.getText().toString().trim().equals("")){
                    nextLay(surveyData.getReferTo());
                }else ans1.setError("Required!");
            }else {
                nextLay(surveyData.getReferTo());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void Q3(SurveyData surveyData){
        try {
            if (surveyData.getRequired()){
                if (!autoCompleteTextView.getText().toString().trim().equals("")){
                    nextLay(surveyData.getOptions().get(referIndex).getReferTo());
                }else Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show();
            }else {
                nextLay(surveyData.getOptions().get(referIndex).getReferTo());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void Q4(SurveyData surveyData){
        try {
            if (surveyData.getRequired()){
                if (checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked()){
                    nextLay("6");
                }else Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show();
            }else {
                nextLay("6");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void Q5(SurveyData surveyData){
        try {
            if (surveyData.getRequired()){
                if (!ans2.getText().toString().trim().equals("")){
                    nextLay(surveyData.getReferTo());
                }else ans2.setError("Required!");
            }else {
                nextLay(surveyData.getReferTo());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void nextLay(String id){
        try {
            count++;
            switch (id){
                case "2":
                    addLay(layout2);
                    currentIndex = viewModel.getIndex(id);
                    question.setText(surveyList.getSurveyData().get(currentIndex).getQuestion());
                    list_count.add(id);
                    break;
                case "3":
                    addLay(layout3);
                    currentIndex = viewModel.getIndex(id);
                    question.setText(surveyList.getSurveyData().get(currentIndex).getQuestion());
                    list_count.add(id);
                    break;
                case "4":
                    addLay(layout4);
                    currentIndex = viewModel.getIndex(id);
                    question.setText(surveyList.getSurveyData().get(currentIndex).getQuestion());
                    list_count.add(id);
                    break;
                case "5":
                    addLay(layout5);
                    currentIndex = viewModel.getIndex(id);
                    question.setText(surveyList.getSurveyData().get(currentIndex).getQuestion());
                    list_count.add(id);
                    break;
                case "6":
                    addLay(layout6);
                    currentIndex = viewModel.getIndex(id);
                    question.setText(surveyList.getSurveyData().get(currentIndex).getQuestion());
                    list_count.add(id);
                    break;
                default:
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void previousLay(String id){
        try {
            switch (id){
                case "2":
                    addLay(layout2);
                    currentIndex = viewModel.getIndex(id);
                    question.setText(surveyList.getSurveyData().get(currentIndex).getQuestion());
                    break;
                case "3":
                    addLay(layout3);
                    currentIndex = viewModel.getIndex(id);
                    question.setText(surveyList.getSurveyData().get(currentIndex).getQuestion());
                    break;
                case "4":
                    addLay(layout4);
                    currentIndex = viewModel.getIndex(id);
                    question.setText(surveyList.getSurveyData().get(currentIndex).getQuestion());
                    break;
                case "5":
                    addLay(layout5);
                    currentIndex = viewModel.getIndex(id);
                    question.setText(surveyList.getSurveyData().get(currentIndex).getQuestion());
                    break;
                case "6":
                    addLay(layout6);
                    currentIndex = viewModel.getIndex(id);
                    question.setText(surveyList.getSurveyData().get(currentIndex).getQuestion());
                    break;
                default:
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addLay(View view){
        parent_lay.removeAllViewsInLayout();
        view.setVisibility(View.VISIBLE);
        parent_lay.addView(view);
    }

}