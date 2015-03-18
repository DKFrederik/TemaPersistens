package modelLayer;

public class PartOrder {

	private int orderId;
	private Product products;
	private int nrOfItems;
	private double itemPrice;
	
	public PartOrder(int orderId, Product products, int nrOfItems,
			double itemPrice) {
		super();
		this.orderId = orderId;
		this.products = products;
		this.nrOfItems = nrOfItems;
		this.itemPrice = itemPrice;
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the products
	 */
	public Product getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(Product products) {
		this.products = products;
	}

	/**
	 * @return the nrOfItems
	 */
	public int getNrOfItems() {
		return nrOfItems;
	}

	/**
	 * @param nrOfItems the nrOfItems to set
	 */
	public void setNrOfItems(int nrOfItems) {
		this.nrOfItems = nrOfItems;
	}

	/**
	 * @return the itemPrice
	 */
	public double getItemPrice() {
		return itemPrice;
	}

	/**
	 * @param itemPrice the itemPrice to set
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
}
