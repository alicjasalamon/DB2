package mapping;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	
	String customerID;
	String companyName;
	String country;
	
	Set<Order> orders  =  new HashSet<Order>(0);
	
	public Customer() {}
	
	public Customer(String customerID, String companyName, String country, Set<Order> orders) {
		super();
		this.customerID = customerID;
		this.companyName = companyName;
		this.country = country;
		this.orders = orders;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<Order> getOrders() {
		return orders;
	}


	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}


	public Customer(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
