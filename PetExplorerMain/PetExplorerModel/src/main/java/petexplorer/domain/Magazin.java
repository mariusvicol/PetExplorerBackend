package petexplorer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import java.io.Serializable;

@Table(name = "magazine_veterinare")
public class Magazin extends Entity<Integer> implements Serializable {
    @Column(nullable = false)
    private Float longitudine;
    @Column(nullable = false)
    private Float latitudine;
    @Column(nullable = false)
    private String nume;
    @Column(nullable = false)
    private Boolean non_stop;

    public Magazin() {}

    public Magazin(Float latitudine, Float longitudine, String nume, Boolean non_stop) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.nume = nume;
        this.non_stop = non_stop;
    }

    public Float getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(Float longitudine) {
        this.longitudine = longitudine;
    }

    public Float getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(Float latitudine) {
        this.latitudine = latitudine;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Boolean getNon_stop() {
        return non_stop;
    }

    public void setNon_stop(Boolean non_stop) {
        this.non_stop = non_stop;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Magazin { ID = "+getId().toString()+", Longitudine = "+ getLongitudine().toString() + ", Latitudine = "+ getLatitudine().toString()+ ", Nume = "+ getNume() + ", Non_stop = "+ getNon_stop().toString() + "}";
    }
}
