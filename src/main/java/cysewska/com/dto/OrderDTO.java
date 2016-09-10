package cysewska.com.dto;

import cysewska.com.enums.TypeOfPayment;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by Ola on 2016-09-07.
 */
public class OrderDTO {
    String departmentName;
    String branchName;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "departmentName='" + departmentName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", orderName='" + orderName + '\'' +
                ", descriptionName='" + descriptionName + '\'' +
                ", dateOfSubmit=" + dateOfSubmit +
                ", dateOfExecution=" + dateOfExecution +
                '}';
    }

    String orderName;
    String descriptionName;
    Date dateOfSubmit;
    Date dateOfExecution;


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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getDescriptionName() {
        return descriptionName;
    }

    public void setDescriptionName(String descriptionName) {
        this.descriptionName = descriptionName;
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

    public OrderDTO(String departmentName, String branchName, String orderName, String descriptionName, Date dateOfSubmit, Date dateOfExecution) {

        this.departmentName = departmentName;
        this.branchName = branchName;
        this.orderName = orderName;
        this.descriptionName = descriptionName;
        this.dateOfSubmit = dateOfSubmit;
        this.dateOfExecution = dateOfExecution;
    }
}
