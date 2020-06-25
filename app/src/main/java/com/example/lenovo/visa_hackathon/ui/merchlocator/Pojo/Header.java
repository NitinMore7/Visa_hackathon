
package com.example.lenovo.visa_hackathon.ui.merchlocator.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Header {

    @SerializedName("responseMessageId")
    @Expose
    private String responseMessageId;
    @SerializedName("startIndex")
    @Expose
    private String startIndex;
    @SerializedName("numRecordsMatched")
    @Expose
    private Integer numRecordsMatched;
    @SerializedName("numRecordsReturned")
    @Expose
    private Integer numRecordsReturned;
    @SerializedName("requestMessageId")
    @Expose
    private String requestMessageId;
    @SerializedName("messageDateTime")
    @Expose
    private String messageDateTime;
    @SerializedName("endIndex")
    @Expose
    private String endIndex;

    public String getResponseMessageId() {
        return responseMessageId;
    }

    public void setResponseMessageId(String responseMessageId) {
        this.responseMessageId = responseMessageId;
    }

    public String getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getNumRecordsMatched() {
        return numRecordsMatched;
    }

    public void setNumRecordsMatched(Integer numRecordsMatched) {
        this.numRecordsMatched = numRecordsMatched;
    }

    public Integer getNumRecordsReturned() {
        return numRecordsReturned;
    }

    public void setNumRecordsReturned(Integer numRecordsReturned) {
        this.numRecordsReturned = numRecordsReturned;
    }

    public String getRequestMessageId() {
        return requestMessageId;
    }

    public void setRequestMessageId(String requestMessageId) {
        this.requestMessageId = requestMessageId;
    }

    public String getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(String messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    public String getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(String endIndex) {
        this.endIndex = endIndex;
    }

}
