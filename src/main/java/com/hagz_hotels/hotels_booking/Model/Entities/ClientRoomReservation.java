package com.hagz_hotels.hotels_booking.Model.Entities;

import java.time.*;

public class ClientRoomReservation {
    enum Status{
        PENDING, CONFIRMED, CHECKED_IN, CHECKED_OUT, CANCELED;
    }
    private Integer clientId;
    private Integer roomId;
    private LocalDateTime createdAt;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Float totalPrice;
    private Status status;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }
    public String getStatusName() {
        return status.name();
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }
}
