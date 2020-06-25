
package com.example.lenovo.visa_hackathon.ui.merchlocator.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchIndicators {

    @SerializedName("merchantCountryCode")
    @Expose
    private String merchantCountryCode;
    @SerializedName("merchantName")
    @Expose
    private String merchantName;

    public String getMerchantCountryCode() {
        return merchantCountryCode;
    }

    public void setMerchantCountryCode(String merchantCountryCode) {
        this.merchantCountryCode = merchantCountryCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

}
