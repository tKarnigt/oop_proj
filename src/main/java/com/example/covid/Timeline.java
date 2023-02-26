package com.example.covid;

public class Timeline {
    //Field
    private int no;
    private String place;
    private String date;

    //Constructor
    Timeline(){}
    Timeline(int no, String place, String date){
        this.no = no;
        this.place = place;
        this.date = date;
    }

    //Access Method
    public void setNo(int no) {
        this.no = no;
    }
    public int getNo() {
        return no;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getPlace() {
        return place;
    }
}
