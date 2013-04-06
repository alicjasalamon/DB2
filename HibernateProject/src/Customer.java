
public class Customer {
	
	String customerID;
	String companyName;
	
	public Customer() {}
	
	public Customer(String customerID ,String companyName) {
		super();
		this.customerID = customerID;
		this.companyName = companyName;
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
