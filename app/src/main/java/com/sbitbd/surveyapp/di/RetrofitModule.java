package com.sbitbd.surveyapp.di;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    public static final String GETSURVEY = "getSurvey";
    public static final String TIMESTAMP = "timestamp";
    private String BaseUrl = "https://example-response.herokuapp.com/";

    @Provides
    @Singleton
    public Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public RetrofitService getRetrofitService(Retrofit retrofit){
        return retrofit.create(RetrofitService.class);
    }

    public static String getTimestamp() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }
}
