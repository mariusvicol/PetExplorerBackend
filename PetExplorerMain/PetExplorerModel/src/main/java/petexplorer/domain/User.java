package petexplorer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.io.Serializable;

@Table(name = "users")
public class User extends Entity<Integer> implements Serializable {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String nrTelefon;

    public User() {
    }

    public User(String email, String password, String name, String nrTelefon) {
        this.email = email;
        this.password = password;
        this.name = name;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +  // Afișăm și id-ul moștenit din Entity
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                '}';
    }
}
