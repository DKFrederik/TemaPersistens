package DBLayer;
import modelLayer.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DBCustomerTest {

	private DBCustomer cus;
	
	@Before
	public void setUp() throws Exception {
		cus = new DBCustomer();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void testSearchCustomer() {
		Customer customer = cus.findCustomer("22164410");
		if(customer !=null){
			System.out.println(customer.getFname());
			assertEquals("Claus",customer.getFname());
		}
		
		else {
			fail("Customer not found!");
		}
	}
	
	@Test
	public void testUpdateCustomer() {
		modelLayer.Customer customer = new Customer("Peter", "Rathmann", "Prinsensgade 41", "9000", "11223344", "blabla@ucn.dk", 'p');
		assertEquals(0, cus.updateCustomer(customer));
		customer = new Customer("testName", "testLastName", "testAddress", "testZip", "00000000", "testEmail", 'p');
		assertEquals(1, cus.deleteCustomer("customer"));
	}
	
	@Test
	public void testDeleteCustomer() {
		assertEquals(1, cus.deleteCustomer("22164410"));
		assertEquals(-1, cus.deleteCustomer("testPhone"));
	}
	
	@Test
	public void testInsertCustomer() {
		modelLayer.Customer customer = new Customer("Peter", "Rothmann", "Prinsensgade 41", "9000", "11223344", "blabla@ucn.dk", 'p');
		assertEquals(0, cus.insertCustomer(customer));
		customer = new Customer("Peter", "Rathmann", "Prinsensgade 41", "9000", "10101010", "blabla@ucn.dk", 'p');
		assertEquals(0, cus.insertCustomer(customer));
	}
	


}
