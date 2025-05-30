package petexplorer.utils;

import jakarta.persistence.criteria.CriteriaBuilder;

public class SearchResultDTO {

    private double latitude;
    private double longitude;
    private String title;
    private String phone;
    private boolean nonStop;
    private String type;
    private Integer idLocation;

    public SearchResultDTO() {}

    public SearchResultDTO(double latitude, double longitude, String title, String phone, boolean nonStop, String type, Integer id) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.phone = phone;
        this.nonStop = nonStop;
        this.type = type;
        this.idLocation = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isNonStop() {
        return nonStop;
    }

    public void setNonStop(boolean nonStop) {
        this.nonStop = nonStop;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "SearchResultDTO{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                ", nonStop=" + nonStop +
                ", type='" + type + '\'' +
                ", idLocation=" + idLocation +
                '}';
    }
}
