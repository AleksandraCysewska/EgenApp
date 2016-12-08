package cysewska.com.models.entities;

import lombok.NonNull;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Ola on 2016-08-30.
 */


@Entity
@Table(name = "DEPARTMENT")
public class DepartmentEntity {
    @Id
    @NonNull
    @Column(name = "DEPARTMENT_ID")
    Integer id;
    @Column(name = "DEPARTMENT_NAME", unique = true)
    @NonNull
    @Size(min=1, message = "Nazwa musi być unikalna i zawierać minimum jeden znak.")
    String name;
    @Column(name = "TYPE_OF_NIP")
    String typeOfNip;
    @Pattern(regexp = "^[0-9]{1,45}", message = "NIP musi zawierać same cyfry do maksymalnej długości 45 znaków.")
    @Column(name = "NIP", unique = true)
    String nip;
    @Column(name = "COUNTRY")
    @Pattern(regexp = ".{3,45}", message = "Kraj musi zawierać co najmniej trzy znaki.")
    String country;
    @Size(min=2, message="Miasto musi zawierać co najmniej dwa znaki.")
    @Column(name = "CITY")
    String city;
    @Column(name = "ADDRESS")
    @Size(min=2, message="Adres musi zawierać co najmniej dwa znaki.")
    String address;
    @Column(name = "ZIP")
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "Kod pocztowy musi mieć postać **-***, zawierający same cyfry/")
    String zip;
    @Email(message = "Email musi być prawidłowy.")
    @Column(name = "EMAIL")
    String email;
    @Pattern(regexp = "^[0-9]{3,45}", message = "Telefon musi zawierać co najmniej trzy cyfry.")
    @Column(name = "TELEPHONE")
    String telephone;

    @ManyToOne
    @JoinColumn(name="BRANCH_ID")
    BranchEntity branchEntity;


    @OneToMany(mappedBy="departmentEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<OrderEntity> orders;
    public DepartmentEntity()
    {}
    public DepartmentEntity(Integer id, String name, String typeOfNip, String nip, String country, String city, String address, String zip, String email, String telephone, BranchEntity branchEntity) {
        this.id = id;
        this.name = name;
        this.typeOfNip = typeOfNip;
        this.nip = nip;
        this.country = country;
        this.city = city;
        this.address = address;
        this.zip = zip;
        this.email = email;
        this.telephone = telephone;
        this.branchEntity = branchEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfNip() {
        return typeOfNip;
    }

    public void setTypeOfNip(String typeOfNip) {
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
