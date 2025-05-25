package petexplorer.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Column(nullable = true)
    private String poza;

    @Column(nullable = false)
    private String nr_telefon;

    @Column(nullable = true)
    private LocalDateTime data_caz;

    @Column(nullable = false)
    private Integer id_user;

    @Column(nullable = false)
    private Boolean rezolvat;

    public AnimalPierdut(Float latitudine, Float longitudine, String nume_animal, String descriere, String tip_caz, String poza, String nr_telefon, LocalDateTime data_caz, Integer id_user, Boolean rezolvat) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.nume_animal = nume_animal;
        this.descriere = descriere;
        this.tip_caz = tip_caz;
        this.poza = poza;
        this.nr_telefon = nr_telefon;
        this.data_caz = data_caz;
        this.id_user = id_user;
        this.rezolvat = rezolvat;
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

    public LocalDateTime getData_caz() {
        return data_caz;
    }

    public Integer getId_user() {return id_user;}

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

    public void setData_caz(LocalDateTime data_caz) {
        this.data_caz = data_caz;
    }

    public void setId_user(Integer id_user) {this.id_user = id_user;}

    public Boolean getRezolvat() {return rezolvat;}

    public void setRezolvat(Boolean rezolvat) {this.rezolvat = rezolvat;}

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
                ", id_user=" + id_user +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AnimalPierdut animal = (AnimalPierdut) obj;
        return getId().equals(animal.getId());
    }

    @Override
    public int hashCode() {

        return getId().hashCode();
    }
}