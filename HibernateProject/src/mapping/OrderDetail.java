package mapping;

public class OrderDetail {
	
	int odID;
	
	Order orderID;
	Product productID;
	float unitPrice;
	int quantity;
	float discount;
	
	public OrderDetail(int odID, Order orderID, Product productID,
			float unitPrice, int quantity, float discount) {
		super();
		this.odID = odID;
		this.orderID = orderID;
		this.productID = productID;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.discount = discount;
	}
	
	public OrderDetail(){}

	public int getOdID() {
		return odID;
	}

	public void setOdID(int odID) {
		this.odID = odID;
	}

	public Order getOrderID() {
		return orderID;
	}

	public void setOrderID(Order orderID) {
		this.orderID = orderID;
	}

	public Product getProductID() {
		return productID;
	}

	public void setProductID(Product productID) {
		this.productID = productID;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

}
