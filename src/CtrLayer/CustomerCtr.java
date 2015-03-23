package CtrLayer;
import modelLayer.*;
import DBLayer.*;

/**
 * @Author 	Frederik, Nichlas, Claus og Peter
 * @date	20-03-2015
 * CustomerCtr has the purpose of communicating with the DBCustomer
 */

public class CustomerCtr {
	
	private DBCustomer custDB;
	
	public CustomerCtr() {
		this.custDB = new DBCustomer();
	}
	
	/**
	 * Prompts the CustomerDB to return an object using information from the database
	 * @param phoneno 
	 * @return a Customer object with matching phoneNo String
	 */
	public Customer getCustomer(String phoneno) {
		return custDB.findCustomer(phoneno);
	}
	/**
	 * Prompts the CustomerDB to update the database
	 * @param A customer object
	 * @return an int indicating whether the update was succesfull (1) or unsuccesful (0) 
	 */
	public int updateCustomer(Customer customer) {
		return custDB.updateCustomer(customer);
	}
	/**
	 * Prompts the CustomerDB to delete a tubble in Customer matching the param phoneno
	 * @param the phoneno attribute of the customer
	 * @return an int indicating whether the delete was succesfull (1) or unsuccesful (0)
	 */
	public int deleteCustomer(String phoneno) {
		return custDB.deleteCustomer(phoneno);
	}
	/**
	 * Prompts the CustomerDB to insert a Customer objet to the database
	 * @param A customer object
	 * @return an int indicating whether the insert was succesfull (1) or unsuccesful (0) 
	 */
	public int insertCustomer(Customer customer) {
		return custDB.insertCustomer(customer);
	}
	
	
}
