package com.hagz_hotels.hotels_booking.Model.Entities;

public class Hotel {
    private Integer hotelId;
    private String name;
    private Float latitude;
    private Float longitude;
    private String address;
    private String phone;
    private Integer adminId;

    public Hotel(Integer hotelId, Integer adminId) {
        this.hotelId = hotelId;
    }

    public Hotel(Integer hotelId, String name, Float latitude, Float longitude, String address, String phone, Integer adminId) {
        this.hotelId = hotelId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.phone = phone;
        this.adminId = adminId;
    }

    public Hotel() {

    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
