package com.sbitbd.surveyapp.di;

import android.app.Application;

public class SurveyApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .retrofitModule(new RetrofitModule())
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
