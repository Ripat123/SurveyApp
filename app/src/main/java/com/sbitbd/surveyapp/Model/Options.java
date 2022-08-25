package com.sbitbd.surveyapp.Model;

public class Options {
    private String value;
    private String referTo;

    public Options(String value, String referTo) {
        this.value = value;
        this.referTo = referTo;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getReferTo() {
        return referTo;
    }

    public void setReferTo(String referTo) {
        this.referTo = referTo;
    }
}
