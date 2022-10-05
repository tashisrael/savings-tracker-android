package com.bemoneywiser.telekako;

public class MysavingsModelClass {

    private String itemTitle;
    private String itemDesc;
    private String dateText;

    private String itemAmount;
    private String startingAmount;
    private String startingdate;
    private int progress;
    private String micurrency;
    private String miCurrency2;

    public MysavingsModelClass(String itemTitle, String itemDesc, String dateText, String itemAmount,String startingAmount, String startingdate,int progress,String micurrency,String miCurrency2) {
        this.itemTitle = itemTitle;
        this.itemDesc = itemDesc;
        this.dateText = dateText;
        this.itemAmount = itemAmount;
        this.startingAmount=startingAmount;
        this.startingdate=startingdate;
        this.progress=progress;
        this.micurrency=micurrency;
        this.miCurrency2=miCurrency2;
    }

    public String getItemTitle() {

        return itemTitle;
    }

    public String getItemDesc() {

        return itemDesc;
    }

    public String getDateText() {

        return dateText;
    }

    public String getItemAmount() {

        return itemAmount;
    }
    public String getStartingAmount(){

        return startingAmount;
    }
    public String getStartingdate(){

        return startingdate;
    }
   public int getProgress()
   {
        return progress;
    }

    public String getMicurrency(){
        return micurrency;
    }
    public String getMiCurrency2(){
        return miCurrency2;
    }
}
