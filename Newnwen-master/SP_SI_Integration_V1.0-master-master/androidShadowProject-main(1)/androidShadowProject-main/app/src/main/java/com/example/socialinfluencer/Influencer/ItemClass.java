package com.example.socialinfluencer.Influencer;

import android.content.Context;

import java.util.ArrayList;

public class ItemClass {

         static final int card = 0;
         static final int card1= 1;

        private int viewType;

        ArrayList<String> aName;
        ArrayList<String> aInfluencer;

        ArrayList<String> aPrice;
        ArrayList<String> aTime;
        Context context;

        public ItemClass(int viewType, ArrayList<String> cName, ArrayList<String> cInfluencer,  ArrayList<String> cPrice, ArrayList<String> cTime)
        { this.viewType = viewType;
            this.aName = cName;
            this.aInfluencer= cInfluencer;
            this.aPrice = cPrice;
            this.aTime = cTime;
           // this.context = context;
        }

    public ItemClass(int card, String campaign_acceptance) {
    }


    public static int getCard() {
        return card;
    }

    public static int getCard1() {
        return card1;
    }

    public ArrayList<String> getaName() {
        return aName;
    }

    public void setaName(ArrayList<String> aName) {
        this.aName = aName;
    }

    public ArrayList<String> getaInfluencer() {
        return aInfluencer;
    }

    public void setaInfluencer(ArrayList<String> aInfluencer) {
        this.aInfluencer = aInfluencer;
    }




    public ArrayList<String> getaPrice() {
        return aPrice;
    }

    public void setaPrice(ArrayList<String> aPrice) {
        this.aPrice = aPrice;
    }

    public ArrayList<String> getaTime() {
        return aTime;
    }

    public void setaTime(ArrayList<String> aTime) {
        this.aTime = aTime;
    }

    public android.content.Context getContext() {
        return context;
    }

    public void setContext(android.content.Context context) {
        this.context = context;
    }

    public ArrayList<String> getbName() {
        return bName;
    }

    public void setbName(ArrayList<String> bName) {
        this.bName = bName;
    }

    public ArrayList<String> getbInfluencer() {
        return bInfluencer;
    }

    public void setbInfluencer(ArrayList<String> bInfluencer) {
        this.bInfluencer = bInfluencer;
    }




    // Variables for the item of second layout
        ArrayList<String> bName;
        ArrayList<String> bInfluencer;
        Context Context;

    public ItemClass(int viewType, ArrayList<String> dName, ArrayList<String> dInfluencer)
    {this.viewType = viewType;
        this.bName = dName;
          this.bInfluencer=dInfluencer;
       // this.Context =Context;
    }

    public int getViewType() { return viewType; }

    public void setViewType(int viewType)
    {
        this.viewType = viewType;
    }
}

