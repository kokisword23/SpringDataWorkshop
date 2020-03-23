package softuni.workshop.data.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    private long id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false,unique = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
