package petexplorer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;

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
    private LocalDate data_caz;

    @Column(nullable = false)
    private String tip_caz;

    @Column(nullable = false)
    private String poza;

    @Column(nullable = true)
    private String nr_telefon;

    public AnimalPierdut() {
    }

    public AnimalPierdut(String poza, String nr_telefon, String tip_caz, LocalDate data_caz, String descriere, String nume_animal, Float longitudine, Float latitudine) {
        this.poza = poza;
        this.nr_telefon = nr_telefon;
        this.tip_caz = tip_caz;
        this.data_caz = data_caz;
        this.descriere = descriere;
        this.nume_animal = nume_animal;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
    }

    public Float getLatitudine() {
        return latitudine;
    }

    public String getNr_telefon() {
        return nr_telefon;
    }

    public String getPoza() {
        return poza;
    }

    public String getTip_caz() {
        return tip_caz;
    }

    public LocalDate getData_caz() {
        return data_caz;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getNume_animal() {
        return nume_animal;
    }

    public Float getLongitudine() {
        return longitudine;
    }

    public void setLatitudine(Float latitudine) {
        this.latitudine = latitudine;
    }

    public void setNr_telefon(String nr_telefon) {
        this.nr_telefon = nr_telefon;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }

    public void setTip_caz(String tip_caz) {
        this.tip_caz = tip_caz;
    }

    public void setData_caz(LocalDate data_caz) {
        this.data_caz = data_caz;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void setNume_animal(String nume_animal) {
        this.nume_animal = nume_animal;
    }

    public void setLongitudine(Float longitudine) {
        this.longitudine = longitudine;
    }

    @Override
    public String toString() {
        return "AnimalPierdut{" +
                "latitudine=" + latitudine +
                ", longitudine=" + longitudine +
                ", nume_animal='" + nume_animal + '\'' +
                ", descriere='" + descriere + '\'' +
                ", data_caz=" + data_caz +
                ", tip_caz='" + tip_caz + '\'' +
                ", poza='" + poza + '\'' +
                ", nr_telefon='" + nr_telefon + '\'' +
                '}';
    }
}
