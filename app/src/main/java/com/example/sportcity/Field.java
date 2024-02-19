package com.example.sportcity;

public class Field {
    private int id;
    private String title;
    private String address;
    private String openingHours;
    private String phone;
    private String type;
    private String cost;
    private int sportId;
    private int favStatus;
    private String img;
    private double latitude;
    private double longitude;

    public Field(int id, String title, String address, String openingHours, String phone, String type,
                 String cost, int sportId, int favStatus, String img, double latitude, double longitude) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.openingHours = openingHours;
        this.phone = phone;
        this.type = type;
        this.cost = cost;
        this.sportId = sportId;
        this.favStatus = favStatus;
        this.img = img;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public int getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(int favStatus) {
        this.favStatus = favStatus;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
