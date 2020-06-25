
package com.example.lenovo.visa_hackathon.ui.merchlocator.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Merchpojo {

    @SerializedName("merchantLocatorServiceResponse")
    @Expose
    private MerchantLocatorServiceResponse merchantLocatorServiceResponse;

    public MerchantLocatorServiceResponse getMerchantLocatorServiceResponse() {
        return merchantLocatorServiceResponse;
    }

    public void setMerchantLocatorServiceResponse(MerchantLocatorServiceResponse merchantLocatorServiceResponse) {
        this.merchantLocatorServiceResponse = merchantLocatorServiceResponse;
    }

}
