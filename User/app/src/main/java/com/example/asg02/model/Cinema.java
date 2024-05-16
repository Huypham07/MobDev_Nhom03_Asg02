package com.example.asg02.model;

import java.io.Serializable;
import java.util.List;

public class Cinema implements Serializable {
    private int id;
    private String name;
    private String manager;
    private String province;
    private String district;
    private String commune;
    private String detailAddress;

    public Cinema() {
    }

    public Cinema(int id, String name, String manager, String province, String district, String commune, String detailAddress) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.detailAddress = detailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
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
}
