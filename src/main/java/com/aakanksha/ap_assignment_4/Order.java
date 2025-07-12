package com.aakanksha.ap_assignment_4;

import java.io.Serializable;

public class Order implements Serializable {

    public static int id = 1;
    private int orderID;
    private Item item;
    private String status;
    private boolean VIP;
    private String spReq;
    private String date;

    public Order(Item item, boolean VIP, String date) {
        this.item = item;
        this.status = "Pending";
        this.VIP = VIP;
        if(VIP) Database.getVIPOrders().add(this);
        else Database.getnonVIPOrders().add(this);
        this.spReq = "";
        this.date = date;
        this.orderID = id++;
    }

    public Order(Item item, boolean VIP, String spReq, String date) {
        this.item = item;
        this.status = "Pending";
        this.VIP = VIP;
        if(VIP) Database.getVIPOrders().add(this);
        else Database.getnonVIPOrders().add(this);
        this.spReq = spReq;
        this.date = date;
        this.orderID = id++;
    }

    public Item getItem() {
        return this.item;
    }
    public int getOrderID() {return this.orderID;}
    public void setItem(Item item) {
        this.item = item;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public boolean isVIP() {
        return this.VIP;
    }
    public void setVIP(boolean VIP) {
        this.VIP = VIP;
    }
    public String getSpReq() {
        return this.spReq;
    }
    public void setSpReq(String spReq) {
        this.spReq = spReq;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}