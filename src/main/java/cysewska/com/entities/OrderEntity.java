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

@Getter
@Setter
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
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="DEPARTMENT_ID")
    DepartmentEntity departmentEntity;


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="orderEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<InvoiceEntity> invoices;

    @ManyToMany(cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name="ORDER_CLOTH",
            joinColumns={@JoinColumn(name="CLOTH_ID")},
            inverseJoinColumns={@JoinColumn(name= "ORDER_ID")})
    Set<ClothEntity> cloths;
}
