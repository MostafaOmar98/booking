package com.hagz_hotels.hotels_booking.Model.Entities;

public class RoomFacility {
    private Integer roomFacilityId;
    private String name;
    private Integer roomId;

    public Integer getRoomFacilityId() {
        return roomFacilityId;
    }

    public void setRoomFacilityId(Integer roomFacilityId) {
        this.roomFacilityId = roomFacilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
