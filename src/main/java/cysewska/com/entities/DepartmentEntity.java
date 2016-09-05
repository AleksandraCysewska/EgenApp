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

    public TypeOfNip getTypeOfNip() {
        return typeOfNip;
    }

    public void setTypeOfNip(TypeOfNip typeOfNip) {
        this.typeOfNip = typeOfNip;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public BranchEntity getBranchEntity() {
        return branchEntity;
    }

    public void setBranchEntity(BranchEntity branchEntity) {
        this.branchEntity = branchEntity;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }
}
