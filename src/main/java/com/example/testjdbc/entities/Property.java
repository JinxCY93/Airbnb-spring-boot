
package com.example.testjdbc.entities;

public class Property {
    private int id;
    private String address;
    private int numRooms;
    private int price;
    private Boolean allowSmoking;
    private int maxGuestNum;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumRooms() {
        return this.numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Boolean isAllowSmoking() {
        return this.allowSmoking;
    }

    public Boolean getAllowSmoking() {
        return this.allowSmoking;
    }

    public void setAllowSmoking(Boolean allowSmoking) {
        this.allowSmoking = allowSmoking;
    }

    public int getMaxGuestNum() {
        return this.maxGuestNum;
    }

    public void setMaxGuestNum(int maxGuestNum) {
        this.maxGuestNum = maxGuestNum;
    }

}