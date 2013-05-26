package mapping;

import java.util.HashSet;
import java.util.Set;

public class Shipper {
	
	int shipperID;
	String companyName;
	Set<Order> orders  =  new HashSet<Order>(0);
	
	public Shipper(int shipperID, String companyName) {
		super();
		this.shipperID = shipperID;
		this.companyName = companyName;
	}
	
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}


	public Shipper(){}

	public Shipper(int shipperID) {
		this.shipperID = shipperID;
	}

	public int getShipperID() {
		return shipperID;
	}

	public void setShipperID(int shipperID) {
		this.shipperID = shipperID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

}
