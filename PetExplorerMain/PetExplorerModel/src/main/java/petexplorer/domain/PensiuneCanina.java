package petexplorer.domain;

import java.io.Serializable;

public class PensiuneCanina extends Entity<Integer> implements Serializable {

    private Float latitudine;
    private Float longitudine;
    private String numePensiune;
    private String nrTel;
    private Boolean nonStop;

    public PensiuneCanina() {}

    public PensiuneCanina(Float latitudine, Float longitudine, String numePensiune, String nrTel, Boolean nonStop) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.numePensiune = numePensiune;
        this.nrTel = nrTel;
        this.nonStop = nonStop;
    }

    public PensiuneCanina(Integer id, Float latitudine, Float longitudine, String numePensiune, String nrTel, Boolean nonStop) {
        this.setId(id);

        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.numePensiune = numePensiune;
        this.nrTel = nrTel;
        this.nonStop = nonStop;
    }

    public Boolean getNonStop() {
        return nonStop;
    }

    public void setNonStop(Boolean nonStop) {
        this.nonStop = nonStop;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public String getNumePensiune() {
        return numePensiune;
    }

    public void setNumePensiune(String numePensiune) {
        this.numePensiune = numePensiune;
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

    @Override
    public String toString() {
        return "PensiuniCanine{" +
                "latitudine=" + latitudine +
                ", longitudine=" + longitudine +
                ", numePensiune='" + numePensiune + '\'' +
                ", nrTel='" + nrTel + '\'' +
                ", nonStop=" + nonStop +
                '}';
    }
}
