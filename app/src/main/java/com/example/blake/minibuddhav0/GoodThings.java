package com.example.blake.minibuddhav0;

public class GoodThings {

    public String thingOne, thingTwo, thingThree;

    public GoodThings(String thingOne, String thingTwo, String thingThree){
        this.thingOne = thingOne;
        this.thingTwo = thingTwo;
        this.thingThree = thingThree;
    }

    public GoodThings(){
        this.thingOne = "";
        this.thingTwo = "";
        this.thingThree = "";
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
}
