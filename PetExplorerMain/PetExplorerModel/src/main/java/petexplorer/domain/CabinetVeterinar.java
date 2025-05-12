package petexplorer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@jakarta.persistence.Entity(name = "CabinetVeterinar")
@Table(name = "cabinete_veterinare")
public class CabinetVeterinar extends Entity<Integer> implements Serializable {

    @Column(nullable = false)
    private Float latitudine;

    @Column(nullable = false)
    private Float longitudine;

    @Column(nullable = false)
    private String nume_cabinet;

    @Column(nullable = true, name = "numar_telefon")
    private String nrTelefon;

    @Column(nullable = false)
    private Boolean non_stop;

    public CabinetVeterinar() {
    }

    public CabinetVeterinar(Float latitudine, Float longitudine, String numeCabinet, String nrTelefon, Boolean nonStop) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.nume_cabinet = numeCabinet;
        this.nrTelefon = nrTelefon;
        this.non_stop = nonStop;
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
        return nume_cabinet;
    }

    public void setNumeCabinet(String numeCabinet) {
        this.nume_cabinet = numeCabinet;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public Boolean getNonStop() {
        return non_stop;
    }

    public void setNonStop(Boolean nonStop) {
        this.non_stop = nonStop;
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
                ", numeCabinet='" + nume_cabinet + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                ", nonStop=" + non_stop +
                '}';
    }
}
