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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClothNamePL() {
        return clothNamePL;
    }

    public void setClothNamePL(String clothNamePL) {
        this.clothNamePL = clothNamePL;
    }

    public String getClothNameNO() {
        return clothNameNO;
    }

    public void setClothNameNO(String clothNameNO) {
        this.clothNameNO = clothNameNO;
    }

    public String getClothNameENG() {
        return clothNameENG;
    }

    public void setClothNameENG(String clothNameENG) {
        this.clothNameENG = clothNameENG;
    }

    public Byte[] getClothImage() {
        return clothImage;
    }

    public void setClothImage(Byte[] clothImage) {
        this.clothImage = clothImage;
    }

    public Integer getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(Integer priceEuro) {
        this.priceEuro = priceEuro;
    }

    public Integer getPricePl() {
        return pricePl;
    }

    public void setPricePl(Integer pricePl) {
        this.pricePl = pricePl;
    }

    public ModelEntity getModelEntity() {
        return modelEntity;
    }

    public void setModelEntity(ModelEntity modelEntity) {
        this.modelEntity = modelEntity;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    public Set<Textile_Cloth_Entity> getTextile_cloths() {
        return textile_cloths;
    }

    public void setTextile_cloths(Set<Textile_Cloth_Entity> textile_cloths) {
        this.textile_cloths = textile_cloths;
    }

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
