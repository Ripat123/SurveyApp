package com.sbitbd.surveyapp.Survey;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sbitbd.surveyapp.Model.SurveyData;
import com.sbitbd.surveyapp.Model.SurveyList;
import com.sbitbd.surveyapp.di.RetrofitModule;
import com.sbitbd.surveyapp.di.RetrofitService;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class surveyViewModel extends ViewModel {

    private MutableLiveData<SurveyList> listMutableLiveData;
    @Inject
    RetrofitService retrofitService;

    @Inject
    public surveyViewModel(){

    }

    protected LiveData<SurveyList> liveData(){
        if (listMutableLiveData == null)
            listMutableLiveData = new MutableLiveData<>();
        surveyData();
        return listMutableLiveData;
    }

    protected void surveyData(){
        Call<ArrayList<SurveyData>> call = retrofitService.getApiData(RetrofitModule.getTimestamp());
        call.enqueue(new Callback<ArrayList<SurveyData>>() {
            @Override
            public void onResponse(Call<ArrayList<SurveyData>> call, Response<ArrayList<SurveyData>> response) {

                SurveyList surveyList = new SurveyList(response.body());
                Log.d("ttt",response.body().get(0).getOptions().get(0).getValue());
                listMutableLiveData.setValue(surveyList);
            }

            @Override
            public void onFailure(Call<ArrayList<SurveyData>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
