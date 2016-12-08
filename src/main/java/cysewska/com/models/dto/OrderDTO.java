package cysewska.com.models.dto;

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
    String dateOfSubmit;
    String dateOfExecution;


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

    public String getDateOfSubmit() {
        return dateOfSubmit;
    }

    public void setDateOfSubmit(String dateOfSubmit) {
        this.dateOfSubmit = dateOfSubmit;
    }

    public String getDateOfExecution() {
        return dateOfExecution;
    }

    public void setDateOfExecution(String dateOfExecution) {
        this.dateOfExecution = dateOfExecution;
    }

    public OrderDTO(String departmentName, String branchName, String orderName, String descriptionName, String dateOfSubmit, String dateOfExecution) {

        this.departmentName = departmentName;
        this.branchName = branchName;
        this.orderName = orderName;
        this.descriptionName = descriptionName;
        this.dateOfSubmit = dateOfSubmit;
        this.dateOfExecution = dateOfExecution;
    }
}
