package com.example.asg02.model;

public class Cinema {
    private int id;
    private String name;
    private String province;
    private String district;
    private String commune;
    private String detailAddress;
    private int managerId;

    public Cinema(String name, String province, String district, String commune, String detailAddress, int managerId) {
        this.name = name;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.detailAddress = detailAddress;
        id = hashCode();
        this.managerId = managerId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
