package cysewska.com.dto;

/**
 * Created by Ola on 2016-09-05.
 */
public class ContractorDTO {
    String branchName;
    String departmentName;
    String nip;
    String country;
    String city;
    String address;
    String zip;
    String email;
    String telephone;

    public ContractorDTO(String branchName, String departmentName, String nip, String country, String city, String address, String zip, String email, String telephone) {
        this.branchName = branchName;
        this.departmentName = departmentName;
        this.nip = nip;
        this.country = country;
        this.city = city;
        this.address = address;
        this.zip = zip;
        this.email = email;
        this.telephone = telephone;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    @Override
    public String toString() {
        return "ContractorDTO{" +
                "branchName='" + branchName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", nip='" + nip + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
