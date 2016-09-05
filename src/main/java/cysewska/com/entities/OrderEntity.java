package cysewska.com.entities;

import cysewska.com.enums.TypeOfPayment;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Ola on 2016-09-04.
 */


@Entity
@Table(name = "ORDER")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ORDER_ID")
    Long id;
    @Column(name = "ORDER_NAME")
    String name;
    @Column(name = "NOTE")
    String note;
    @Column( name = "DATE_OF_SUBMIT")
    Date dateOfSubmit;
    @Column(name = "DATE_OF_EXECUTION")
    Date dateOfExecution;
    @Column(name = "TYPE_OF_PAYMENT")
    TypeOfPayment typeOfPayment;
    @Column(name = "TERM_OF_PAYMENT")
    Date termOfPayment;
    @Column(name = "WEIGHT_NETTO")
    Integer weightNetto;
    @Column(name = "WEIGHT_BRUTTO")
    Integer weightBrutto;
    @Column(name = "QUANTITY_OF_PALLET")
    Integer quantityOfPallet;

    @ManyToOne
    @JoinColumn(name="DEPARTMENT_ID")
    DepartmentEntity departmentEntity;


    @OneToMany(mappedBy="orderEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<InvoiceEntity> invoices;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="ORDER_CLOTH",
            joinColumns={@JoinColumn(name="CLOTH_ID")},
            inverseJoinColumns={@JoinColumn(name= "ORDER_ID")})
    Set<ClothEntity> cloths;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDateOfSubmit() {
        return dateOfSubmit;
    }

    public void setDateOfSubmit(Date dateOfSubmit) {
        this.dateOfSubmit = dateOfSubmit;
    }

    public Date getDateOfExecution() {
        return dateOfExecution;
    }

    public void setDateOfExecution(Date dateOfExecution) {
        this.dateOfExecution = dateOfExecution;
    }

    public TypeOfPayment getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(TypeOfPayment typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public Date getTermOfPayment() {
        return termOfPayment;
    }

    public void setTermOfPayment(Date termOfPayment) {
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

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    public Set<InvoiceEntity> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<InvoiceEntity> invoices) {
        this.invoices = invoices;
    }

    public Set<ClothEntity> getCloths() {
        return cloths;
    }

    public void setCloths(Set<ClothEntity> cloths) {
        this.cloths = cloths;
    }
}
