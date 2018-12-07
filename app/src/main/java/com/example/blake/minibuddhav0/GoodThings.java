package com.example.blake.minibuddhav0;

import java.util.Date;

public class GoodThings {

    public String thingOne, thingTwo, thingThree;
    public Date date;
    public int counter;

    public GoodThings(String thingOne, String thingTwo, String thingThree, Date date){
        this.thingOne = thingOne;
        this.thingTwo = thingTwo;
        this.thingThree = thingThree;
        this.date = date;
        this.counter = counter;
    }

    public GoodThings(){
        this.thingOne = "";
        this.thingTwo = "";
        this.thingThree = "";
        this.date = new Date();
        this.counter = -1;
    }

    public String getThingOne() {
        return this.thingOne;
    }
    public String getThingTwo() {
        return this.thingTwo;
    }
    public String getThingThree() {
        return this.thingThree;
    }
    public Date getDate(){
        return this.date;
    }
    public int getCounter(){
        return counter;
    }
}
