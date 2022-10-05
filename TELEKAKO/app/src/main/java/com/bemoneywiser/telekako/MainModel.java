package com.bemoneywiser.telekako;

public class MainModel {

    String Goal,Amount,StartingAmt,Date,Currency,Percentage,Currency2,Progress;

    MainModel(){

    }

    public String getCurrency2() {
        return Currency;
    }

    public void setCurrency2(String currency2) {
        Currency = currency2;
    }

    public String getProgress() {
        return Percentage;
    }

    public void setProgress(String progress) {
        Percentage = progress;
    }

    public MainModel(String goal, String amount, String startingAmt, String date, String currency, String percentage, String currency2, String progress) {
        Goal = goal;
        Amount = amount;
        StartingAmt = startingAmt;
        Date = date;
        Currency = currency;
        Percentage = percentage;
        Currency2 = currency2;
        Progress = progress;
    }

    public String getGoal() {
        return Goal;
    }

    public void setGoal(String goal) {
        Goal = goal;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getStartingAmt() {
        return StartingAmt;
    }

    public void setStartingAmt(String startingAmt) {
        StartingAmt = startingAmt;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getPercentage() {
        return Percentage;
    }

    public void setPercentage(String percentage) {
        Percentage = percentage;
    }

}
