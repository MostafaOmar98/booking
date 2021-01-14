package com.hagz_hotels.hotels_booking.Business.DTO;

public class HotelSearchResultDTO {
    Integer hotelId;
    String hotelName;
    Float hotelRate;
    String imageLink;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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

}
