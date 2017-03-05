package com.ogg.erp.model;

public class Order
{
	private String orderId;

	private String customerName;

	private String customerTel;

	private String customerPhone;

	private String orderDate;

	private String planDeliveryDate;

	private String actualDeliveryDate;

	private float deposit;

	private float carriage;

	private float incidentals;

	private String shipAddress;

	private String status;

	private float kickback;

	private String commonts;

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	public String getCustomerTel()
	{
		return customerTel;
	}

	public void setCustomerTel(String customerTel)
	{
		this.customerTel = customerTel;
	}

	public String getCustomerPhone()
	{
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone)
	{
		this.customerPhone = customerPhone;
	}

	public String getOrderDate()
	{
		return orderDate;
	}

	public void setOrderDate(String orderDate)
	{
		this.orderDate = orderDate;
	}

	public String getPlanDeliveryDate()
	{
		return planDeliveryDate;
	}

	public void setPlanDeliveryDate(String planDeliveryDate)
	{
		this.planDeliveryDate = planDeliveryDate;
	}

	public String getActualDeliveryDate()
	{
		return actualDeliveryDate;
	}

	public void setActualDeliveryDate(String actualDeliveryDate)
	{
		this.actualDeliveryDate = actualDeliveryDate;
	}

	public float getDeposit()
	{
		return deposit;
	}

	public void setDeposit(float deposit)
	{
		this.deposit = deposit;
	}

	public float getCarriage()
	{
		return carriage;
	}

	public void setCarriage(float carriage)
	{
		this.carriage = carriage;
	}

	public float getIncidentals()
	{
		return incidentals;
	}

	public void setIncidentals(float incidentals)
	{
		this.incidentals = incidentals;
	}

	public String getShipAddress()
	{
		return shipAddress;
	}

	public void setShipAddress(String shipAddress)
	{
		this.shipAddress = shipAddress;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public float getKickback()
	{
		return kickback;
	}

	public void setKickback(float kickback)
	{
		this.kickback = kickback;
	}

	public String getCommonts()
	{
		return commonts;
	}

	public void setCommonts(String commonts)
	{
		this.commonts = commonts;
	}

	@Override
	public String toString()
	{
		return "Order [orderId=" + orderId + ", customerName=" + customerName + ", customerTel=" + customerTel
		        + ", customerPhone=" + customerPhone + ", orderDate=" + orderDate + ", planDeliveryDate="
		        + planDeliveryDate + ", actualDeliveryDate=" + actualDeliveryDate + ", deposit=" + deposit
		        + ", carriage=" + carriage + ", incidentals=" + incidentals + ", shipAddress=" + shipAddress
		        + ", status=" + status + ", kickback=" + kickback + ", commonts=" + commonts + "]";
	}

}