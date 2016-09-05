package cysewska.com.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * Created by cysewskaa on 2016-09-05.
 */
@Getter
@Setter
@Entity
@Table(name = "CLOTH")
public class ClothEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "CLOTH_ID")
    Long id;
    @Column(name = "CLOTH_NAME_PL")
    String clothNamePL;
    @Column(name = "CLOTH_NAME_NO")
    String clothNameNO;
    @Column(name = "CLOTH_NAME_ENG")
    String clothNameENG;
    @Column(name = "CLOTH_IMAGE")
    Byte[] clothImage;
    @Column(name = "PRICE_EURO")
    Integer priceEuro;
    @Column(name = "PRICE_PL")
    Integer pricePl;

    @ManyToMany(mappedBy="cloths")
    Set<OrderEntity>  orders;

    @ManyToOne
    @JoinColumn(name="MODEL_ID")
    ModelEntity modelEntity;

    @OneToMany(mappedBy="clothEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<Textile_Cloth_Entity> textile_cloths;
}
