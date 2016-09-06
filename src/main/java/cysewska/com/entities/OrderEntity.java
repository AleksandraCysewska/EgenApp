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

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", dateOfSubmit=" + dateOfSubmit +
                ", dateOfExecution=" + dateOfExecution +
                ", typeOfPayment=" + typeOfPayment +
                ", termOfPayment=" + termOfPayment +
                ", weightNetto=" + weightNetto +
                ", weightBrutto=" + weightBrutto +
                ", quantityOfPallet=" + quantityOfPallet +
                ", invoices=" + invoices +
                ", clothEntity=" + cloths +
                '}';
    }

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

    @ManyToMany(mappedBy="orders")
    Set<ClothEntity>  cloths;



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
