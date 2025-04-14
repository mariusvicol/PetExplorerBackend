package petexplorer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.io.Serializable;

@jakarta.persistence.Entity
@Table(name = "saloane")
public class Salon extends Entity<Integer> implements Serializable {

    @Column(nullable = false)
    private Float latitude;

    @Column(nullable = false)
    private Float longitude;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String nrTel;

    @Column(nullable = true)
    private Boolean non_stop;

    public Salon () {}

    public Salon(Float latitudine, Float longitudine, String numeSalon, String nrTelefon, Boolean nonStop) {
        this.latitude = latitudine;
        this.longitude = longitudine;
        this.name = numeSalon;
        this.nrTel = nrTelefon;
        this.non_stop = nonStop;
    }

    public Salon(Integer id, Float latitudine, Float longitudine, String numeSalon, String nrTelefon, Boolean nonStop) {
        this.setId(id);

        this.latitude = latitudine;
        this.longitude = longitudine;
        this.name = numeSalon;
        this.nrTel = nrTelefon;
        this.non_stop = nonStop;
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

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNon_stop() {
        return non_stop;
    }

    public void setNon_stop(Boolean non_stop) {
        this.non_stop = non_stop;
    }

    @Override
    public String toString() {
        return "Salon{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", nrTel='" + nrTel + '\'' +
                ", non_stop=" + non_stop +
                '}';
    }
}
