package cysewska.com.models.dto;

import cysewska.com.models.enums.TypeOfPayment;

import java.util.Date;

/**
 * Created by Ola on 2016-09-10.
 */
public class InvoiceDTO {
    String departmentName;
    String branchName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    String name;
    String language;
    Date date;
    TypeOfPayment typeOfPayment;
    Date termOfPayment;
    Integer weightNetto;
    Integer weightBrutto;
    Integer quantityOfPallet;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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



    public InvoiceDTO(String departmentName, String branchName, String name, String language, Date date, TypeOfPayment typeOfPayment, Date termOfPayment, Integer weightNetto, Integer weightBrutto, Integer quantityOfPallet) {
        this.departmentName = departmentName;
        this.branchName = branchName;
        this.name = name;
        this.language = language;
        this.date = date;
        this.typeOfPayment = typeOfPayment;
        this.termOfPayment = termOfPayment;
        this.weightNetto = weightNetto;
        this.weightBrutto = weightBrutto;
        this.quantityOfPallet = quantityOfPallet;
    }
}
