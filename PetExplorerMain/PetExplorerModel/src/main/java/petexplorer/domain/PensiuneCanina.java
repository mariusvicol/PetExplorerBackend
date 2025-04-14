package petexplorer.domain;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "pensiuni_canine")

public class PensiuneCanina extends Entity<Integer> implements Serializable {

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

    public PensiuneCanina() {}

    public PensiuneCanina(Float latitudine, Float longitudine, String numePensiune, String nrTel, Boolean nonStop) {
        this.latitude = latitudine;
        this.longitude = longitudine;
        this.name = numePensiune;
        this.nrTel = nrTel;
        this.non_stop = nonStop;
    }

    public PensiuneCanina(Integer id, Float latitudine, Float longitudine, String numePensiune, String nrTel, Boolean nonStop) {
        this.setId(id);

        this.latitude = latitudine;
        this.longitude = longitudine;
        this.name = numePensiune;
        this.nrTel = nrTel;
        this.non_stop = nonStop;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public Boolean getNon_stop() {
        return non_stop;
    }

    public void setNon_stop(Boolean non_stop) {
        this.non_stop = non_stop;
    }

    @Override
    public String toString() {
        return "PensiuneCanina{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", nrTel='" + nrTel + '\'' +
                ", non_stop=" + non_stop +
                '}';
    }
}
