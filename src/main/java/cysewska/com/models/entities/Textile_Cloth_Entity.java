package cysewska.com.models.entities;

import javax.persistence.*;

/**
 * Created by cysewskaa on 2016-09-05.
 */

@Entity
@Table(name = "TEXTILE_CLOTH")
public class Textile_Cloth_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "TEXTILE_CLOTH_ID")
    Long id;
    @Column(name = "TEXTILE_CLOTH_QUANTITIES")
    Integer textileClothQuantities;

    @ManyToOne
    @JoinColumn(name="CLOTH_ID")
    ClothEntity clothEntity;

    @Override
    public String toString() {
        return "Textile_Cloth_Entity{" +
                "id=" + id +
                ", textileClothQuantities=" + textileClothQuantities +

                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTextileClothQuantities() {
        return textileClothQuantities;
    }

    public void setTextileClothQuantities(Integer textileClothQuantities) {
        this.textileClothQuantities = textileClothQuantities;
    }

    public ClothEntity getClothEntity() {
        return clothEntity;
    }

    public void setClothEntity(ClothEntity clothEntity) {
        this.clothEntity = clothEntity;
    }

    public TextileEntity getTextileEntity() {
        return textileEntity;
    }

    public void setTextileEntity(TextileEntity textileEntity) {
        this.textileEntity = textileEntity;
    }

    @ManyToOne
    @JoinColumn(name="TEXTILE_ID")
    TextileEntity textileEntity;
}