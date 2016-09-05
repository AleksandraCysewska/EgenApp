package cysewska.com.entities;

import cysewska.com.enums.TypeOfNip;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Ola on 2016-08-30.
 */

@Getter
@Setter
@Entity(name = "DEPARTMENT")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "DEPARTMENT_ID")
    Long id;
    @Column(name = "DEPARTMENT_NAME")
    String name;
    @Column(name = "TYPE_OF_NIP")
    TypeOfNip typeOfNip;
    @Column(name = "NIP")
    String nip;
    @Column(name = "COUNTRY")
    String country;
    @Column(name = "CITY")
    String city;
    @Column(name = "ADDRESS")
    String address;
    @Column(name = "ZIP")
    String zip;
    @Column(name = "EMAIL")
    String email;
    @Column(name = "TELEPHONE")
    String telephone;

    @ManyToOne
    @JoinColumn(name="BRANCH_ID")
    BranchEntity branchEntity;

    @OneToMany(mappedBy="departmentEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<OrderEntity> orders;

}
