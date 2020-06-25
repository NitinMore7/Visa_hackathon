
package com.example.lenovo.visa_hackathon.ui.merchlocator.Pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MerchantLocatorServiceResponse {

    @SerializedName("response")
    @Expose
    private List<Response> response = null;
    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("status")
    @Expose
    private Status status;


    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
