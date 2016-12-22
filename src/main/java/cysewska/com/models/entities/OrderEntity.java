package cysewska.com.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by Ola on 2016-09-04.
 */


@Entity
@Table(name = "ORDERS")
public class OrderEntity {
    @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ORDER_ID")
    Long id;
    @Column(name = "ORDER_NAME")
    String name;
    @Column(name = "NOTE")
    @Size(min =1, message = "Pole z datą realizacji zamówienia nie może być puste. ")

    String note;
    @Size(min =1, message = "Pole z datą utworzenia zamówienia nie może być puste. ")
    @Column( name = "DATE_OF_SUBMIT")
    String dateOfSubmit;
    @Column(name = "DATE_OF_EXECUTION")
    @Size(min =1, message = "Pole z datą realizacji zamówienia nie może być puste. ")

    String dateOfExecution;


    @ManyToOne
    @JoinColumn(name="DEPARTMENT_ID")
    DepartmentEntity departmentEntity;


    @OneToMany(mappedBy="orderEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<InvoiceEntity> invoices;

    @OneToMany(mappedBy="orderEntity",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<OrderClothEntity>  orderClothEntities;


    public OrderEntity() {
    }

    public OrderEntity(Long id, String name, String note, String dateOfSubmit, String dateOfExecution, DepartmentEntity departmentEntity) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.dateOfSubmit = dateOfSubmit;
        this.dateOfExecution = dateOfExecution;
        this.departmentEntity = departmentEntity;

    }

    public Set<OrderClothEntity> getOrderClothEntities() {
        return orderClothEntities;
    }

    public void setOrderClothEntities(Set<OrderClothEntity> orderClothEntities) {
        this.orderClothEntities = orderClothEntities;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDateOfSubmit() {
        return dateOfSubmit;
    }

    public void setDateOfSubmit(String daStringteOfSubmit) {
        this.dateOfSubmit = dateOfSubmit;
    }

    public String getDateOfExecution() {
        return dateOfExecution;
    }

    public void setDateOfExecution(String dateOfExecution) {
        this.dateOfExecution = dateOfExecution;
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




}
