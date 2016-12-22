package cysewska.com.models.entities;

/**
 * Created by cysewskaa on 2016-09-05.
 */


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@Table(name = "MODELS")
public class ModelEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(name = "MODEL_ID")
    Long id;
    @Size(min = 2, message = "Nazwa musi być unikalna i zawierać minimum jeden znak.")
    @Column(name = "MODEL_NAME", unique = true)
    String model;

    public ModelEntity(Long id, String model) {
        this.id = id;
        this.model = model;

    }

    @Override
    public String toString() {
        return "ModelEntity{" +
                "id=" + id +
                ", model=" + model +

                '}';
    }

    public ModelEntity() {
    }

    @OneToMany(mappedBy="modelEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<ClothEntity> cloths;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Set<ClothEntity> getCloths() {
        return cloths;
    }

    public void setCloths(Set<ClothEntity> cloths) {
        this.cloths = cloths;
    }
}