package petexplorer.domain;

import java.io.Serializable;

public class Salon extends Entity<Integer> implements Serializable {
    private Float latitudine;
    private Float longitudine;
    private String numeSalon;
    private String nrTelefon;
    private Boolean nonStop;

    public Salon () {}

    public Salon(Float latitudine, Float longitudine, String numeSalon, String nrTelefon, Boolean nonStop) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.numeSalon = numeSalon;
        this.nrTelefon = nrTelefon;
        this.nonStop = nonStop;
    }

    public Salon(Integer id, Float latitudine, Float longitudine, String numeSalon, String nrTelefon, Boolean nonStop) {
        this.setId(id);

        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.numeSalon = numeSalon;
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

    public String getNumeSalon() {
        return numeSalon;
    }

    public void setNumeSalon(String numeSalon) {
        this.numeSalon = numeSalon;
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
    public String toString() {
        return "Salon{" +
                "latitudine=" + latitudine +
                ", longitudine=" + longitudine +
                ", numeSalon='" + numeSalon + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                ", nonStop=" + nonStop +
                '}';
    }
}
