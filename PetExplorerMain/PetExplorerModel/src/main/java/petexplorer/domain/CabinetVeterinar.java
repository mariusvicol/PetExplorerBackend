package petexplorer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.io.Serializable;

@jakarta.persistence.Entity
@Table(name = "cabinete_veterinare")
public class CabinetVeterinar extends Entity<Integer> implements Serializable {

    @Column(nullable = false)
    private Float latitudine;

    @Column(nullable = false)
    private Float longitudine;

    @Column(nullable = false)
    private String numeCabinet;

    @Column(nullable = true)
    private String nrTelefon;

    @Column(nullable = false)
    private Boolean nonStop;

    public CabinetVeterinar() {
    }

    public CabinetVeterinar(Float latitudine, Float longitudine, String numeCabinet, String nrTelefon, Boolean nonStop) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.numeCabinet = numeCabinet;
        this.nrTelefon = nrTelefon;
        this.nonStop = nonStop;
    }

    public Float getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(Float latitudine) {
        this.latitudine = latitudine;
    }

    public Float getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(Float longitudine) {
        this.longitudine = longitudine;
    }

    public String getNumeCabinet() {
        return numeCabinet;
    }

    public void setNumeCabinet(String numeCabinet) {
        this.numeCabinet = numeCabinet;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public Boolean getNonStop() {
        return nonStop;
    }

    public void setNonStop(Boolean nonStop) {
        this.nonStop = nonStop;
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
        return "CabinetVeterinar{" +
                "id=" + id +
                ", latitudine=" + latitudine +
                ", longitudine=" + longitudine +
                ", numeCabinet='" + numeCabinet + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                ", nonStop=" + nonStop +
                '}';
    }
}
