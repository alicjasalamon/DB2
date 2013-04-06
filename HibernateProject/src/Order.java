
public class Order {

	int orderID;
	Employee employeeID;
	Customer customerID;
	int shipVia;
	
	public Order() {}
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public Employee getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Employee employeeID) {
		this.employeeID = employeeID;
	}
	public Customer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Customer customerID) {
		this.customerID = customerID;
	}
	public int getShipVia() {
		return shipVia;
	}
	public void setShipVia(int shipVia) {
		this.shipVia = shipVia;
	}
	
	
	public Order(Employee employeeID, Customer customerID) {
		super();
		this.employeeID = employeeID;
		this.customerID = customerID;
		this.shipVia = 1;
	}
	


}
