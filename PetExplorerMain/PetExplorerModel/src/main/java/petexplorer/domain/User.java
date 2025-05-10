package petexplorer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.io.Serializable;

@jakarta.persistence.Entity
@Table(name = "users")
public class User extends Entity<Integer> implements Serializable {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nume;

    @Column(name = "nr_telefon")
    private String nrTelefon;

    public User() {
    }

    public User(String email, String password, String name, String nrTelefon) {
        this.email = email;
        this.password = password;
        this.nume = name;
        this.nrTelefon = nrTelefon;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNume() {
        return this.nume;
    }

    public void setNume(String name) {
        this.nume = name;
    }

    @JsonProperty("nrTelefon")
    public String getNrTelefon() {
        return nrTelefon;
    }

    @JsonProperty("nrTelefon")
    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +  // Afișăm și id-ul moștenit din Entity
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + nume + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                '}';
    }
}
