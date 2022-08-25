package com.sbitbd.surveyapp.Model;

import java.util.ArrayList;

public class SurveyList {
    private ArrayList<SurveyData> surveyData;

    public SurveyList(ArrayList<SurveyData> surveyData) {
        this.surveyData = surveyData;
    }

    public ArrayList<SurveyData> getSurveyData() {
        return surveyData;
    }

    public void setSurveyData(ArrayList<SurveyData> surveyData) {
        this.surveyData = surveyData;
    }
}
