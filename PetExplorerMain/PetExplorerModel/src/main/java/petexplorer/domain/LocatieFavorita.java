package petexplorer.domain;

import jakarta.persistence.*;
import petexplorer.domain.enums.LocationType;

import java.io.Serializable;

@jakarta.persistence.Entity
@Table ( name = "locatii" )
public class LocatieFavorita extends Entity<Integer> implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LocationType locationType;

    @Column(name = "idEn", nullable = false)
    private Integer entityId;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        return "LocatieFavorita{" +
                "user=" + user +
                ", locationType=" + locationType +
                ", entityId=" + entityId +
                '}';
    }
}
