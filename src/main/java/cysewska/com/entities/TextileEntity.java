package cysewska.com.entities;

import cysewska.com.enums.Colors;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by cysewskaa on 2016-09-05.
 */


@Getter
@Setter
@Entity
@Table(name = "TEXTILE")
public class TextileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "TEXTILE_ID")
    Long id;
    @Column(name = "TEXTILE_NAME")
    String name;
    @Column(name = "TEXTILE_COLOR")
    Colors colors;
    @Column(name ="TEXTILE_QUANTITY" )
    Integer textileQuantity;
    @Column(name ="TEXTILE_THICKNESS" )
    Integer textileThickness;



    @OneToMany(mappedBy="textileEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<Textile_Cloth_Entity> textile_cloths;
}
