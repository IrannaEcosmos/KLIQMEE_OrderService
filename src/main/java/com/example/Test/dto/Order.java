package com.example.Test.dto;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "orders")
public class Order {

    @Id
    private String id; // MongoDB uses String IDs by default

    private String event_id;
    private String event_date;
    private String userId;
    private String purchasername;
    private Double totalamount;
    private Double discountedamount;
    private Double coupondiscount;
    private String qrcode_id;
    private String qr_date;
    private String payment_mode;
    private String purchase_date;
    private String payment_status;
    private Double service_charges;
    private Integer noofchildren;
    private Integer birthyear;
    private String location;

    private List<String> imagearray;

    private Boolean isused;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Field("items") // Optional: to explicitly map the field name in the MongoDB document
    private List<Item> items;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPurchaser_name() {
		return purchasername;
	}

	public void setPurchaser_name(String purchaser_name) {
		this.purchasername = purchaser_name;
	}

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public Double getDiscounted_amount() {
		return discountedamount;
	}

	public void setDiscounted_amount(Double discounted_amount) {
		this.discountedamount = discounted_amount;
	}

	public Double getCoupon_discount() {
		return coupondiscount;
	}

	public void setCoupon_discount(Double coupon_discount) {
		this.coupondiscount = coupon_discount;
	}

	public String getQrcode_id() {
		return qrcode_id;
	}

	public void setQrcode_id(String qrcode_id) {
		this.qrcode_id = qrcode_id;
	}

	public String getQr_date() {
		return qr_date;
	}

	public void setQr_date(String qr_date) {
		this.qr_date = qr_date;
	}

	public String getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}

	public String getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public Double getService_charges() {
		return service_charges;
	}

	public void setService_charges(Double service_charges) {
		this.service_charges = service_charges;
	}

	public Integer getNoofchildren() {
		return noofchildren;
	}

	public void setNoofchildren(Integer noofchildren) {
		this.noofchildren = noofchildren;
	}

	public Integer getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(Integer birthyear) {
		this.birthyear = birthyear;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getImageArray() {
		return imagearray;
	}

	public void setImageArray(List<String> imageArray) {
		this.imagearray = imageArray;
	}

	public Boolean getIsUsed() {
		return isused;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isused = isUsed;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

    // Getters and Setters

    
}
