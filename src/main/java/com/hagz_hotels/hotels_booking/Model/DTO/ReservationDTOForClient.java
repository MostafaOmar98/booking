package com.hagz_hotels.hotels_booking.Model.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationDTOForClient {
    private Integer reservationId;

    private Integer roomId;

    private LocalDate checkIn;
    private LocalDate checkOut;
    private Float totalPrice;
    private LocalDateTime createdAt;
    private String status;

    private Boolean reviewable;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Boolean getReviewable() {
        return reviewable;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isReviewable() {
        return reviewable;
    }

    public void setReviewable(Boolean reviewable) {
        this.reviewable = reviewable;
    }
}
