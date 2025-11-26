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

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private String nume;

    @Column(name = "nr_telefon")
    private String nrTelefon;

    @Column(name = "google_id", unique = true)
    private String googleId;

    @Column(name = "auth_provider")
    private String authProvider;

    @Column(name = "totp_secret")
    private String totpSecret;

    @Column(name = "totp_enabled")
    private Boolean totpEnabled = false;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(String email, String password, String name, String nrTelefon) {
        this.email = email;
        this.password = password;
        this.nume = name;
        this.nrTelefon = nrTelefon;
        this.authProvider = "EMAIL";
        this.role = "USER";
    }

    public User(String email, String name, String googleId) {
        this.email = email;
        this.nume = name;
        this.googleId = googleId;
        this.authProvider = "GOOGLE";
        this.password = null;
        this.role = "USER";
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

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public String getTotpSecret() {
        return totpSecret;
    }

    public void setTotpSecret(String totpSecret) {
        this.totpSecret = totpSecret;
    }


    public Boolean getTotpEnabled() {
        return totpEnabled != null ? totpEnabled : false;
    }

    public void setTotpEnabled(Boolean totpEnabled) {
        this.totpEnabled = totpEnabled;
    }

    public String getRole() { return this.role; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +  // Afișăm și id-ul moștenit din Entity
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + nume + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                ", googleId='" + googleId + '\'' +
                ", authProvider='" + authProvider + '\'' +
                ", totpEnabled=" + totpEnabled +
                ", role='" + role + '\'' +
                '}';
    }
}