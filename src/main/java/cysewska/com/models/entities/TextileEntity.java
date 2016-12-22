package cysewska.com.models.entities;

import cysewska.com.models.enums.Colors;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by cysewskaa on 2016-09-05.
 */



@Entity
@Table(name = "TEXTILE")
public class TextileEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "TEXTILE_ID")
    Long id;
    @Column(name = "TEXTILE_NAME")
    String name;
    @Column(name = "TEXTILE_COLOR")
    String colors;
    @Column(name ="TEXTILE_QUANTITY" )
    Double textileQuantity;
    @Column(name ="TEXTILE_THICKNESS" )
    Double textileThickness;

    public TextileEntity(Long id, String name, String colors, Double textileQuantity, Double textileThickness) {
        this.id = id;
        this.name = name;
        this.colors = colors;
        this.textileQuantity = textileQuantity;
        this.textileThickness = textileThickness;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public Double getTextileQuantity() {
        return textileQuantity;
    }

    public void setTextileQuantity(Double textileQuantity) {
        this.textileQuantity = textileQuantity;
    }

    public TextileEntity() {
    }

    public Double getTextileThickness() {
        return textileThickness;
    }

    public void setTextileThickness(Double textileThickness) {
        this.textileThickness = textileThickness;
    }

    public Set<Textile_Cloth_Entity> getTextile_cloths() {
        return textile_cloths;
    }

    public void setTextile_cloths(Set<Textile_Cloth_Entity> textile_cloths) {
        this.textile_cloths = textile_cloths;
    }

    @OneToMany(mappedBy="textileEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<Textile_Cloth_Entity> textile_cloths;


}
