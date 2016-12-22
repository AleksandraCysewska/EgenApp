package cysewska.com.models.entities;

import javax.persistence.*;

/**
 * Created by Ola on 2016-09-22.
 */
@Entity
@Table(name = "ORDER_CLOTH")
public class OrderClothEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(name = "ORDER_CLOTH_ID")
    Long id;
    @Column(name = "QUANTITY")
    Long quantity;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name="CLOTH_ID")
    ClothEntity clothEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public ClothEntity getClothEntity() {
        return clothEntity;
    }

    public void setClothEntity(ClothEntity clothEntity) {
        this.clothEntity = clothEntity;
    }

    public OrderClothEntity() {
    }

    public OrderClothEntity(Long id, Long quantity, OrderEntity orderEntity, ClothEntity clothEntity) {
        this.id = id;
        this.quantity = quantity;
        this.orderEntity = orderEntity;
        this.clothEntity = clothEntity;
    }
}
