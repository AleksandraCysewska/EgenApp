package cysewska.com.models.entities;

import javax.persistence.*;
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
    String note;
    @Column( name = "DATE_OF_SUBMIT")
    Date dateOfSubmit;
    @Column(name = "DATE_OF_EXECUTION")
    Date dateOfExecution;


    @ManyToOne
    @JoinColumn(name="DEPARTMENT_ID")
    DepartmentEntity departmentEntity;


    @OneToMany(mappedBy="orderEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<InvoiceEntity> invoices;

    @ManyToMany(mappedBy="orders")
    Set<ClothEntity>  cloths;


    public OrderEntity() {
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

    public OrderEntity(Long id, String name, String note, Date dateOfSubmit, Date dateOfExecution, DepartmentEntity departmentEntity, Set<InvoiceEntity> invoices, Set<ClothEntity> cloths) {

        this.id = id;
        this.name = name;
        this.note = note;
        this.dateOfSubmit = dateOfSubmit;
        this.dateOfExecution = dateOfExecution;
        this.departmentEntity = departmentEntity;
        this.invoices = invoices;
        this.cloths = cloths;
    }
}
