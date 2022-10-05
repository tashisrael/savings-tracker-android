package com.bemoneywiser.telekako;

public class User {
    public String Fname,Lname,Email,PhoneNum,Pass,cPass,count1,code1;
   //public String Goal,Amount,StartingAmt,Date,Currency,Percentage;

    public User(){

    }
    public User(String Fname,String Lname,String Email,String PhoneNum,String Pass,String cPass,String count1,String code1){

        this.Fname = Fname;
        this.Lname = Lname;
        this.Email = Email;
        this.PhoneNum = PhoneNum;
        this.Pass = Pass;
        this.cPass = cPass;
        this.count1=count1;
        this.code1=code1;
    }

}

