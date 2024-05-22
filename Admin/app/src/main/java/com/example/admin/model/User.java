package com.example.admin.model;

public class User extends Account {
    private String name;
    private String birthDate;
    private String sex;
    private String phone;
    private String region;
    private String favouriteCinema;
    private long point;
    private String image;

    public User() {
    }

    public User(String email, String password, String name, String birthDate
            , String sex, String phone, String region, String favouriteCinema, long point, String image) {
        super(email, password);
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.phone = phone;
        this.region = region;
        this.point = point;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFavouriteCinema() {
        return favouriteCinema;
    }

    public void setFavouriteCinema(String favouriteCinema) {
        this.favouriteCinema = favouriteCinema;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}