package com.hagz_hotels.hotels_booking.Model.Entities;

public class HotelImage {
    private Integer hotelId;
    private Integer imageId;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return "" + hotelId + "/" + imageId + ".png";
    }
}
