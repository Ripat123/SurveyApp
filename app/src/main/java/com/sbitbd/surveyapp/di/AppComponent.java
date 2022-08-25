package com.sbitbd.surveyapp.di;

import com.sbitbd.surveyapp.Survey.survey;
import com.sbitbd.surveyapp.Survey.surveyViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface AppComponent {

    surveyViewModel getModel();
    void inject(survey survey);
}
