package ctrLayer;
import modelLayer.Customer;
import modelLayer.Product;
import DBLayer.DBCustomer;
import DBLayer.ProductDB;

/**
 * @Author 	Frederik, Nichlas, Claus og Peter
 * @date	20-03-2015
 * ProductCtr has the purpose of communicating with the DBProduct
 */

public class ProductCtr {

	private ProductDB proDB;
	
	public ProductCtr() {
		this.proDB = new ProductDB();
	}
	
	/**
	 * Prompts the ProductDB to return an object using information from the database
	 * @param name 
	 * @return a Product object with matching name String
	 */
	public Product getProduct(String name) {
		return proDB.findProduct(name);
	}
	/**
	 * Prompts the ProductDB to update the database
	 * @param A Product object
	 * @return an int indicating whether the update was succesfull (1) or unsuccesful (0) 
	 */
	public int updateProduct(Product product) {
		return proDB.updateProduct(product);
	}
	/**
	 * Prompts the ProductDB to delete a tubble in Customer matching the param phoneno
	 * @param the name attribute of the Product
	 * @return an int indicating whether the delete was succesfull (1) or unsuccesful (0)
	 */
	public int deleteProduct(String name) {
		return proDB.deleteProduct(name);
	}
	/**
	 * Prompts the ProductDB to insert a Product objet to the database
	 * @param A Product object
	 * @return an int indicating whether the insert was succesfull (1) or unsuccesful (0) 
	 */
	public int insertProduct(Product product) {
		return proDB.insertProduct(product);
	}
	
}
