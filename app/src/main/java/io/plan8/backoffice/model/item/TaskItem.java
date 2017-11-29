package io.plan8.backoffice.model.item;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class TaskItem implements BaseModel {
    private String customerName;
    private String customerPhoneNumber;
    private String customerAddress;
    private String reservationDate;
    private String reservationTime;
    private String reservationEndTime;
    private String productionName;
    private String customerRequest;
    private String productionDescription;
    private String status;
    private String closeStatus;

    public TaskItem() {
    }

    public TaskItem(String customerName, String customerPhoneNumber, String customerAddress, String reservationDate, String reservationTime, String reservationEndTime, String productionName, String customerRequest, String productionDescription, String status, String closeStatus) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAddress = customerAddress;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.reservationEndTime = reservationEndTime;
        this.productionName = productionName;
        this.customerRequest = customerRequest;
        this.productionDescription = productionDescription;
        this.status = status;
        this.closeStatus = closeStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getReservationEndTime() {
        return reservationEndTime;
    }

    public void setReservationEndTime(String reservationEndTime) {
        this.reservationEndTime = reservationEndTime;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public String getCustomerRequest() {
        return customerRequest;
    }

    public void setCustomerRequest(String customerRequest) {
        this.customerRequest = customerRequest;
    }

    public String getProductionDescription() {
        return productionDescription;
    }

    public void setProductionDescription(String productionDescription) {
        this.productionDescription = productionDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCloseStatus() {
        return closeStatus;
    }

    public void setCloseStatus(String closeStatus) {
        this.closeStatus = closeStatus;
    }
}
