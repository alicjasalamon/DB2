package mapping;

import java.util.HashSet;
import java.util.Set;

public class Product {
	
	int productID;
	Supplier supplierID;
	Set<OrderDetail> orderdetails  =  new HashSet<OrderDetail>(0);

	public Product(){}

	public Product(int productID) {
		super();
		this.productID = productID;
	}
	
	public Product(int productID, Supplier supplierID,
			Set<OrderDetail> orderdetails) {
		super();
		this.productID = productID;
		this.supplierID = supplierID;
		this.orderdetails = orderdetails;
	}

	public Set<OrderDetail> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(Set<OrderDetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public Supplier getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(Supplier supplierID) {
		this.supplierID = supplierID;
	}
	
	
	

}
