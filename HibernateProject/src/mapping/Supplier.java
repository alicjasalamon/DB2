package mapping;

import java.util.HashSet;
import java.util.Set;

public class Supplier {
	
	int supplierID;
	String companyName;
	Set<Product> products  =  new HashSet<Product>(0);
	
	public Supplier(){}

	public Supplier(int supplierID, String companyName, Set<Product> products) {
		super();
		this.supplierID = supplierID;
		this.companyName = companyName;
		this.products = products;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
