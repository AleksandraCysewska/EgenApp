package cysewska.com.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by cysewskaa on 2016-09-05.
 */

@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name="TEXTILE_ID")
    TextileEntity textileEntity;
}