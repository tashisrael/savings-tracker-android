package com.bemoneywiser.telekako;

public class User1 {
    private String Email1,Goal,Amount,StartingAmt,Date,Currency,Percentage;

    public User1(){

    }
    public User1(String Email1,String Goal,String Amount,String StartingAmt,String Date,String Currency,String Percentage){

        this.Email1 = Email1;
        this.Goal = Goal;
        this.Amount = Amount;
        this.StartingAmt = StartingAmt;
        this.Date = Date;
        this.Currency = Currency;
        this.Percentage = Percentage;
    }

    public String getEmail1() {
        return Email1;
    }

    public void setEmail1(String email1) {
        Email1 = email1;
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
