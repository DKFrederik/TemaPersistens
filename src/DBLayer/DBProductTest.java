package DBLayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import modelLayer.*;

public class DBProductTest {

	DBProduct proDB;
	
	@Before
	public void setUp() throws Exception {
		proDB = new DBProduct();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSearchProduct() {
		Product product = proDB.findProduct("Colt", false);
		if(product !=null){
			System.out.println(product.getId());
			if(product instanceof GunReplica) {
				;
			}
		else {
			fail("Customer not found!");
		}
			
		}
	}
}

