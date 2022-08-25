package com.sbitbd.surveyapp.Model;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SurveyData {
    private String id;
    private String question;
    private String type;
    @Nullable
    private ArrayList<Options> options;
    @Nullable
    private String referTo;
    private Boolean required;

    public SurveyData(String id, String question, String type, @Nullable ArrayList<Options> options,
                      @Nullable String referTo, Boolean required) {
        this.id = id;
        this.question = question;
        this.type = type;
        this.options = options;
        this.referTo = referTo;
        this.required = required;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Nullable
    public ArrayList<Options> getOptions() {
        return options;
    }

    public void setOptions(@Nullable ArrayList<Options> options) {
        this.options = options;
    }

    @Nullable
    public String getReferTo() {
        return referTo;
    }

    public void setReferTo(@Nullable String referTo) {
        this.referTo = referTo;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }
}
