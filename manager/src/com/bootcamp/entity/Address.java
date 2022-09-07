package com.bootcamp.entity;

public class Address {
    public long Id;
    public long ProvinceId;
    public String Address1;
    public String Address2;

    public Address(long id, long provinceId, String address1, String address2) {
        Id = id;
        ProvinceId = provinceId;
        Address1 = address1;
        Address2 = address2;
    }

    public Address() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(long provinceId) {
        ProvinceId = provinceId;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }
}
