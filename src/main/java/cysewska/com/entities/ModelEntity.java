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

@Getter
@Setter
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

}