package petexplorer.utils;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UserLocationDTO {
    @NotNull(message = "Latitudinea este obligatorie")
    @Min(value = -90, message = "Latitudinea trebuie să fie minim -90")
    @Max(value = 90, message = "Latitudinea trebuie să fie maxim 90")
    private Double latitude;

    @NotNull(message = "Longitudinea este obligatorie")
    @Min(value = -180, message = "Longitudinea trebuie să fie minim -180")
    @Max(value = 180, message = "Longitudinea trebuie să fie maxim 180")
    private Double longitude;

    @NotNull(message = "Raza de notificare este obligatorie")
    @Positive(message = "Raza trebuie să fie un număr pozitiv")
    private Double notificationRadius;

    public UserLocationDTO() {}

    public UserLocationDTO(Double latitude, Double longitude, Double notificationRadius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.notificationRadius = notificationRadius;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getNotificationRadius() {
        return notificationRadius;
    }

    public void setNotificationRadius(Double notificationRadius) {
        this.notificationRadius = notificationRadius;
    }

    public String toString() {
        return "UserLocationDto{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", radius=" + notificationRadius +
                '}';
    }

}
