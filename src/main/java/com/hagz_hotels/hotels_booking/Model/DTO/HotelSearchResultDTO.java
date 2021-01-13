package com.hagz_hotels.hotels_booking.Model.DTO;

public class HotelSearchResultDTO {
    Integer hotelId;
    String hotelName;
    Float hotelRate;
    Integer imageId;
    Float minPrice;
    Float maxPrice;
    Float distance;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Float getHotelRate() {
        return hotelRate;
    }

    public void setHotelRate(Float hotelRate) {
        this.hotelRate = hotelRate;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "HotelSearchResultDTO{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", hotelRate=" + hotelRate +
                ", imageId=" + imageId +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", distance=" + distance +
                '}';
    }
}
