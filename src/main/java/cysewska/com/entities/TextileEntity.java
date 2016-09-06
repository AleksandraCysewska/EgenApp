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

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    public Integer getTextileQuantity() {
        return textileQuantity;
    }

    public void setTextileQuantity(Integer textileQuantity) {
        this.textileQuantity = textileQuantity;
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
