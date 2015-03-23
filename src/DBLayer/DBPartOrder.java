package DBLayer;

import modelLayer.*;

import java.sql.*;

/**
 * @Author 	Frederik, Nichlas, Claus og Peter
 * @date	20-03-2015
 * DBPartOrder has the purpose of establishing a connection and execute several queries to the database, such
 * as search, insert, delete and update. For this project only insert is implemented.
 */

public class DBPartOrder {
	private Connection con;
	
	public DBPartOrder() 
	{
		con = DBConnection.getInstance().getDBcon();
	}
	
	/**
	 * 
	 * @param partOrder The PartOrder that is to be inserted
	 * @param id the id of the Order it belongs to. 
	 * @return
	 */
	public int insertPartOrder(PartOrder partOrder, int id) {

		int rc = -1;
		String query = "INSERT INTO PartOrder(orderId, productId, noOfItems, itemPrice)  VALUES("
				+ id
				+ ","
				+ partOrder.getProducts().getId()
				+ ","
				+ partOrder.getNrOfItems()
				+ ","
				+ partOrder.getItemPrice() + ")";

		System.out.println("insert : " + query);
		
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("SalesOrder is not inserted correct");
		}
		return (rc);
	}

}