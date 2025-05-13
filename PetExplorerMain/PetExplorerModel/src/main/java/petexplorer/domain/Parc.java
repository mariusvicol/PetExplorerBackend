package petexplorer.domain;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "parcuri")

public class Parc extends Entity<Integer> implements Serializable {

    @Column(nullable = false)
    private Float latitudine;

    @Column(nullable = false)
    private Float longitudine;

    @Column(nullable = false)
    private String nume;

    @Column(nullable = false, name = "non_stop")
    private Boolean nonStop;

    public Parc(Float latitudine, Float longitudine, String nume, Boolean non_stop) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.nume = nume;
        this.nonStop = non_stop;
    }

    public Parc() {}

    public Float getLatitudine() {
        return latitudine;
    }

    public Float getLongitudine() {
        return longitudine;
    }

    public String getNume() {
        return nume;
    }

    public Boolean getNonStop() {
        return nonStop;
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

    public void setNonStop(Boolean non_stop) {
        this.nonStop = non_stop;
    }

    @Override
    public String toString() {
        return "Parc{" +
                "latitudine=" + latitudine +
                ", longitudine=" + longitudine +
                ", nume='" + nume + '\'' +
                ", non_stop=" + nonStop +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Parc parc = (Parc) obj;
        return getId().equals(parc.getId());
    }

    @Override
    public int hashCode() {

        return getId().hashCode();
    }
}