package petexplorer.domain;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "animale_pierdute")

public class AnimalPierdut extends Entity<Integer> implements Serializable {

    @Column(nullable = false)
    private Float latitudine;

    @Column(nullable = false)
    private Float longitudine;

    @Column(nullable = false)
    private String nume_animal;

    @Column(nullable = false)
    private String descriere;

    @Column(nullable = false)
    private String tip_caz;

    @Column(nullable = false)
    private String poza;

    @Column(nullable = false)
    private String nr_telefon;

    @Column(nullable = true)
    private LocalDate data_caz;

    public AnimalPierdut(Float latitudine, Float longitudine, String nume_animal, String descriere, String tip_caz, String poza, String nr_telefon, LocalDate data_caz) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.nume_animal = nume_animal;
        this.descriere = descriere;
        this.tip_caz = tip_caz;
        this.poza = poza;
        this.nr_telefon = nr_telefon;
        this.data_caz = data_caz;
    }

    public AnimalPierdut() {

    }

    public Float getLatitudine() {
        return latitudine;
    }

    public Float getLongitudine() {
        return longitudine;
    }

    public String getNume_animal() {
        return nume_animal;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getTip_caz() {
        return tip_caz;
    }

    public String getPoza() {
        return poza;
    }

    public String getNr_telefon() {
        return nr_telefon;
    }

    public LocalDate getData_caz() {
        return data_caz;
    }

    public void setLatitudine(Float latitudine) {
        this.latitudine = latitudine;
    }

    public void setLongitudine(Float longitudine) {
        this.longitudine = longitudine;
    }

    public void setNume_animal(String nume_animal) {
        this.nume_animal = nume_animal;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void setTip_caz(String tip_caz) {
        this.tip_caz = tip_caz;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }

    public void setNr_telefon(String nr_telefon) {
        this.nr_telefon = nr_telefon;
    }

    public void setData_caz(LocalDate data_caz) {
        this.data_caz = data_caz;
    }

    @Override
    public String toString() {
        return "AnimalPierdut{" +
                "latitudine=" + latitudine +
                ", longitudine=" + longitudine +
                ", nume_animal='" + nume_animal + '\'' +
                ", descriere='" + descriere + '\'' +
                ", tip_caz='" + tip_caz + '\'' +
                ", poza='" + poza + '\'' +
                ", nr_telefon='" + nr_telefon + '\'' +
                ", data_caz=" + data_caz +
                '}';
    }
}
