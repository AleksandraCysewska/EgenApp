package cysewska.com.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by cysewskaa on 2016-09-05.
 */

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



    @ManyToOne
    @JoinColumn(name="MODEL_ID")
    ModelEntity modelEntity;


    @Override
    public String toString() {
        return "ClothEntity{" +
                "id=" + id +
                ", clothNamePL='" + clothNamePL + '\'' +
                ", clothNameNO='" + clothNameNO + '\'' +
                ", clothNameENG='" + clothNameENG + '\'' +
                ", clothImage=" + Arrays.toString(clothImage) +
                ", priceEuro=" + priceEuro +
                ", pricePl=" + pricePl +
                ", modelEntity=" + modelEntity +
                ", textile_cloths=" + textile_cloths +
                '}';
    }

  /*  @ManyToMany(targetEntity=cysewska.com.entities.OrderEntity.class)
    Set<OrderEntity> orderEntity;
*/

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name="ORDER_CLOTH",
            joinColumns={@JoinColumn(name="CLOTH_ID")},
            inverseJoinColumns={@JoinColumn(name= "ORDER_ID")})
    Set<OrderEntity> orders;



    @OneToMany(mappedBy="clothEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<Textile_Cloth_Entity> textile_cloths;
}
