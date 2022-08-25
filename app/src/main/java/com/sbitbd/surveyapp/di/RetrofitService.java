package com.sbitbd.surveyapp.di;


import com.google.gson.JsonArray;
import com.sbitbd.surveyapp.Model.SurveyData;
import com.sbitbd.surveyapp.Model.SurveyList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RetrofitService {
    @GET(RetrofitModule.GETSURVEY)
    Call<ArrayList<SurveyData>> getApiData(@Header(RetrofitModule.TIMESTAMP) String header);
}
