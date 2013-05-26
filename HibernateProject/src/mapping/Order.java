package mapping;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Order {

	int orderID;
	Customer customerID;
	
	int employeeID;
    
	@Temporal(TemporalType.TIMESTAMP)
    private java.sql.Timestamp orderDate;
	
	@Temporal(TemporalType.TIMESTAMP)
    private java.sql.Timestamp requiredDate;
	
	@Temporal(TemporalType.TIMESTAMP)
    private java.sql.Timestamp shippedDate;

	Shipper shipVia;
	float freight;
	String shipName;
	String shipAddress;
	String shipCity;
	String shipRegion;
	String shipPostalCode;
	String shipCountry;
	
	Set<Order> orders  =  new HashSet<Order>(0);
	
	public Order() {}

	public Order(int orderID, Customer customerID, int employeeID,
			Timestamp orderDate, Timestamp requiredDate, Timestamp shippedDate,
			Shipper shipVia, float freight, String shipName,
			String shipAddress, String shipCity, String shipRegion,
			String shipPostalCode, String shipCountry, Set<Order> orders) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.employeeID = employeeID;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.shipVia = shipVia;
		this.freight = freight;
		this.shipName = shipName;
		this.shipAddress = shipAddress;
		this.shipCity = shipCity;
		this.shipRegion = shipRegion;
		this.shipPostalCode = shipPostalCode;
		this.shipCountry = shipCountry;
		this.orders = orders;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public int getOrderID() {
		return orderID;
	}


	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}


	public Customer getCustomerID() {
		return customerID;
	}


	public void setCustomerID(Customer customerID) {
		this.customerID = customerID;
	}


	public int getEmployeeID() {
		return employeeID;
	}


	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}


	public java.sql.Timestamp getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(java.sql.Timestamp orderDate) {
		this.orderDate = orderDate;
	}


	public java.sql.Timestamp getRequiredDate() {
		return requiredDate;
	}


	public void setRequiredDate(java.sql.Timestamp requiredDate) {
		this.requiredDate = requiredDate;
	}


	public java.sql.Timestamp getShippedDate() {
		return shippedDate;
	}


	public void setShippedDate(java.sql.Timestamp shippedDate) {
		this.shippedDate = shippedDate;
	}


	public Shipper getShipVia() {
		return shipVia;
	}


	public void setShipVia(Shipper shipVia) {
		this.shipVia = shipVia;
	}


	public float getFreight() {
		return freight;
	}


	public void setFreight(float freight) {
		this.freight = freight;
	}


	public String getShipName() {
		return shipName;
	}


	public void setShipName(String shipName) {
		this.shipName = shipName;
	}


	public String getShipAddress() {
		return shipAddress;
	}


	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}


	public String getShipCity() {
		return shipCity;
	}


	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}


	public String getShipRegion() {
		return shipRegion;
	}


	public void setShipRegion(String shipRegion) {
		this.shipRegion = shipRegion;
	}


	public String getShipPostalCode() {
		return shipPostalCode;
	}


	public void setShipPostalCode(String shipPostalCode) {
		this.shipPostalCode = shipPostalCode;
	}


	public String getShipCountry() {
		return shipCountry;
	}


	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}
	
	
}
