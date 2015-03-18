package CtrLayer;
import modelLayer.Product;
import DBLayer.ProductDB;

public class ProductCtr {

	private Product products;
	private ProductDB proDB;
	public ProductCtr(Product products, ProductDB proDB) {
		super();
		this.products = products;
		this.proDB = proDB;
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
	 * @return the proDB
	 */
	public ProductDB getProDB() {
		return proDB;
	}
	/**
	 * @param proDB the proDB to set
	 */
	public void setProDB(ProductDB proDB) {
		this.proDB = proDB;
	}
	
	
}
