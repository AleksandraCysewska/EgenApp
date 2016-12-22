package cysewska.com.models.entities;

import cysewska.com.models.enums.TypeOfPayment;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ola on 2016-08-30.
 */


@Entity
@Table(name = "INVOICE")
public class InvoiceEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(name = "INVOICE_ID")
    Long id;
    @Column(name = "INVOICE_NAME")
    String name;
    @Column(name = "LANGUAGE_OF_INVOICE")
    String language;
    @Column(name = "TYPE_OF_PAYMENT")
    String typeOfPayment;
    @Column(name = "TERM_OF_PAYMENT")
    String termOfPayment;
    @Column(name = "WEIGHT_NETTO")
    Integer weightNetto;
    @Column(name = "WEIGHT_BRUTTO")
    Integer weightBrutto;
    @Column(name = "QUANTITY_OF_PALLET")
    Integer quantityOfPallet;
    @Column(name = "DATE_OF_EXHIBIT")
    String date;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    OrderEntity orderEntity;

    public InvoiceEntity() {
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public String getTermOfPayment() {
        return termOfPayment;
    }

    public void setTermOfPayment(String termOfPayment) {
        this.termOfPayment = termOfPayment;
    }

    public Integer getWeightNetto() {
        return weightNetto;
    }

    public void setWeightNetto(Integer weightNetto) {
        this.weightNetto = weightNetto;
    }

    public Integer getWeightBrutto() {
        return weightBrutto;
    }

    public void setWeightBrutto(Integer weightBrutto) {
        this.weightBrutto = weightBrutto;
    }

    public Integer getQuantityOfPallet() {
        return quantityOfPallet;
    }

    public void setQuantityOfPallet(Integer quantityOfPallet) {
        this.quantityOfPallet = quantityOfPallet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public InvoiceEntity(Long id, String name, String language, String typeOfPayment, String termOfPayment, Integer weightNetto, Integer weightBrutto, Integer quantityOfPallet, String date, OrderEntity orderEntity) {

        this.id = id;
        this.name = name;
        this.language = language;
        this.typeOfPayment = typeOfPayment;
        this.termOfPayment = termOfPayment;
        this.weightNetto = weightNetto;
        this.weightBrutto = weightBrutto;
        this.quantityOfPallet = quantityOfPallet;
        this.date = date;
        this.orderEntity = orderEntity;
    }
}
