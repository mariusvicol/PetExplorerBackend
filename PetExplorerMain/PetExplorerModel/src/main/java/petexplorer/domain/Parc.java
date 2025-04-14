package petexplorer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.io.Serializable;


@jakarta.persistence.Entity
@Table(name = "parcuri")

public class Parc extends Entity<Integer> implements Serializable {

    @Column(nullable = false)
    private Float latitudine;

    @Column(nullable = false)
    private Float longitudine;

    @Column(nullable = false)
    private String nume;

    @Column(nullable = false)
    private Boolean non_stop;

    public Parc() {
    }

    public Parc(Float latitudine, Boolean non_stop, String nume, Float longitudine) {
        this.latitudine = latitudine;
        this.non_stop = non_stop;
        this.nume = nume;
        this.longitudine = longitudine;
    }

    public Float getLatitudine() {
        return latitudine;
    }

    public Boolean getNon_stop() {
        return non_stop;
    }

    public String getNume() {
        return nume;
    }

    public Float getLongitudine() {
        return longitudine;
    }

    public void setLatitudine(Float latitudine) {
        this.latitudine = latitudine;
    }

    public void setLongitudine(Float longitudine) {
        this.longitudine = longitudine;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setNon_stop(Boolean non_stop) {
        this.non_stop = non_stop;
    }

    @Override
    public String toString() {
        return "Parc{" +
                "latitudine=" + latitudine +
                ", longitudine=" + longitudine +
                ", nume='" + nume + '\'' +
                ", non_stop=" + non_stop +
                '}';
    }
}
