package cysewska.com.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ola on 2016-08-30.
 */


@Entity
@Table(name = "INVOICE")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "INVOICE_ID")
    Long id;
    @Column(name = "INVOICE_NAME")
    String name;
    @Column(name = "LANGUAGE_OF_INVOICE")
    String language;
    @Column(name = "DATE_OF_EXHIBIT")
    Date date;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    OrderEntity orderEntity;


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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
