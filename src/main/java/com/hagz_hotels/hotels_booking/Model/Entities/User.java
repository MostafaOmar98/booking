package com.hagz_hotels.hotels_booking.Model.Entities;

public class User {
    public enum Type{
        CLIENT, ADMIN
    }
    private Integer userId;
    private String username;
    private String email;
    private String phone;
    private Type type;
    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Type getType() {
        return type;
    }

    public String getTypeName() {
        return type.name();
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = Type.valueOf(type);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
