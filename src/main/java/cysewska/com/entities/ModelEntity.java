package cysewska.com.entities;

/**
 * Created by cysewskaa on 2016-09-05.
 */

import cysewska.com.enums.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "MODEL")
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "MODEL_ID")
    Long id;
    @Column(name = "MODEL_NAME")
    Model model;

    @OneToMany(mappedBy="modelEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<ClothEntity> cloths;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Set<ClothEntity> getCloths() {
        return cloths;
    }

    public void setCloths(Set<ClothEntity> cloths) {
        this.cloths = cloths;
    }
}