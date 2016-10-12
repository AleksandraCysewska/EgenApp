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
    String date;
    String typeOfPayment;
    String termOfPayment;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public String getTermOfPayment() {
        return termOfPayment;
    }

    public void setTermOfPayment(String termOfPayment) {
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



    public InvoiceDTO(String departmentName, String branchName, String name, String language, String date, String typeOfPayment, String termOfPayment, Integer weightNetto, Integer weightBrutto, Integer quantityOfPallet) {
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
