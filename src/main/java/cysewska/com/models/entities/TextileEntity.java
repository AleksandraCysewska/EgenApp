package cysewska.com.models.entities;

import cysewska.com.models.enums.Colors;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cysewskaa on 2016-09-05.
 */



@Entity
@Table(name = "TEXTILE")
public class TextileEntity {
    @Id
  //  @GeneratedValue (strategy = GenerationType.IDENTITY, generator = "TEXTILE_SEQ")
  //  @SequenceGenerator ( name = "TEXTILE_SEQ" , sequenceName = "TEXTILE_SEQ")
    @Column(name = "TEXTILE_ID")
    Long id;
    @Column(name = "TEXTILE_NAME")
    String name;
    @Column(name = "TEXTILE_COLOR")
    String colors;
    @Column(name ="TEXTILE_QUANTITY" )
    Integer textileQuantity;
    @Column(name ="TEXTILE_THICKNESS" )
    Integer textileThickness;

    public TextileEntity(Long id, String name, String colors, Integer textileQuantity, Integer textileThickness) {
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

    @Override
    public String toString() {
        return "TextileEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", colors=" + colors +
                ", textileQuantity=" + textileQuantity +
                ", textileThickness=" + textileThickness +

                '}';
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

    public Integer getTextileQuantity() {
        return textileQuantity;
    }

    public void setTextileQuantity(Integer textileQuantity) {
        this.textileQuantity = textileQuantity;
    }

    public TextileEntity() {
    }

    public Integer getTextileThickness() {
        return textileThickness;
    }

    public void setTextileThickness(Integer textileThickness) {
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
