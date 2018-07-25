

public class Order {
	private String pizzaType, quantity, tableNo, orderTaken;

	public String getPizzaType() {
		return pizzaType;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getTableNo() {
		return tableNo;
	}

	public String getOrderTaken() {
		return orderTaken;
	}

	public void setPizzaType(String pizzaType) {
		this.pizzaType = pizzaType;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public void setOrderTakenBy(String orderTaken) {
		this.orderTaken = orderTaken;
	}
}
