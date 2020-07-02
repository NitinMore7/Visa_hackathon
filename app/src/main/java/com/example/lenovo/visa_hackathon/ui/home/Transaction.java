package com.example.lenovo.visa_hackathon.ui.home;

public class Transaction {
    private String transAmt;
    private String transDate;
    private String transTime;
    private String transPlace;

    public Transaction(String transAmt, String transDate, String transTime, String transPlace){
        this.transAmt=transAmt;
        this.transDate=transDate;
        this.transPlace=transPlace;
        this.transTime=transTime;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public String getTransDate() {
        return transDate;
    }

    public String getTransTime() {
        return transTime;
    }

    public String getTransPlace() {
        return transPlace;
    }
}