package CtrLayer;
import modelLayer.Customer;
import DBLayer.CustomerDB;

public class CustomerCtr {
	
	private Customer pik;
	private CustomerDB custDB;
	public CustomerCtr(Customer customer, CustomerDB custDB) {
		super();
		this.customer = customer;
		this.custDB = custDB;
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @return the custDB
	 */
	public CustomerDB getCustDB() {
		return custDB;
	}
	/**
	 * @param custDB the custDB to set
	 */
	public void setCustDB(CustomerDB custDB) {
		this.custDB = custDB;
	}
	
	
}
