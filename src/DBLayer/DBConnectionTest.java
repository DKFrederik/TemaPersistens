package DBLayer;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBConnectionTest {

	@Test
	public void test() {
		DBConnection dbCon = DBConnection.getInstance();
		if(dbCon != null)
		{
			System.out.println("Conecction to DB is ok");
		}
		else{
		    fail("Can not connect to the DB");
		}
	}

}
