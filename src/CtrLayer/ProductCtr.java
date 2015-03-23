package CtrLayer;
import modelLayer.Product;
import modelLayer.Supplier;
import DBLayer.*;

/**
 * @Author 	Frederik, Nichlas, Claus og Peter
 * @date	20-03-2015
 * ProductCtr has the purpose of communicating with the DBProduct
 */

public class ProductCtr {

	private DBProduct dBPro;
	private DBSupplier dBSup;
	
	public ProductCtr() {
		this.dBPro = new DBProduct();
		dBSup = new DBSupplier();
	}
	
	/**
	 * Prompts the ProductDB to return an object using information from the database
	 * @param name 
	 * @return a Product object with matching name String
	 */
	public Product getProduct(String name) {
		return dBPro.findProduct(name, false);
	}
	/**
	 * Prompts the ProductDB to update the database
	 * @param A Product object
	 * @return 
	 */
	public int updateProduct(Product product, String name) {
		return dBPro.updateProduct(product, name);
	}
	/**
	 * Prompts the ProductDB to delete a tubble in Customer matching the param phoneno
	 * @param the name attribute of the Product
	 * @return 
	 */
	public int deleteProduct(String name) {
		return dBPro.deleteProduct(name);
	}
	/**
	 * Prompts the ProductDB to insert a Product objet to the database
	 * @param A Product object
	 * @return
	 */
	public int insertProduct(Product product) {
		return dBPro.insertProduct(product);
	}
	
	public Supplier findSupplier(String supplier){
		System.out.println("ProCtr");
		return dBSup.findSupplier(supplier);
	}
	
}
