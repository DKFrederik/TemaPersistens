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
	public void testSearchCustomerPhoneNo() {
		Customer customer = cus.findCustomer("22164410", false);
		if(customer !=null){
			System.out.println(customer.getFname());
			assertEquals("Claus",customer.getFname());
		}
		
		else {
			fail("Customer not found!");
		}
	}

}
