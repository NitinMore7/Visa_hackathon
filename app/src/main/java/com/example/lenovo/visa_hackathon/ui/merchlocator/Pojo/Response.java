
package com.example.lenovo.visa_hackathon.ui.merchlocator.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("responseValues")
    @Expose
    private ResponseValues responseValues;
    @SerializedName("matchIndicators")
    @Expose
    private MatchIndicators matchIndicators;
    @SerializedName("matchScore")
    @Expose
    private String matchScore;

    public ResponseValues getResponseValues() {
        return responseValues;
    }

    public void setResponseValues(ResponseValues responseValues) {
        this.responseValues = responseValues;
    }

    public MatchIndicators getMatchIndicators() {
        return matchIndicators;
    }

    public void setMatchIndicators(MatchIndicators matchIndicators) {
        this.matchIndicators = matchIndicators;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

}
