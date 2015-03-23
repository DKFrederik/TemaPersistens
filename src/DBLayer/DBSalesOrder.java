package DBLayer;

import modelLayer.*;

import java.sql.*;

/**
 * @Author 	Frederik, Nichlas, Claus og Peter
 * @date	20-03-2015
 * DBSalesOrder has the purpose of establishing a connection and execute several queries to the database, such
 * as search, insert, delete and update. For this project only insert is implemented.
 */

public class DBSalesOrder {
	private Connection con;
	
	public DBSalesOrder() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	/**
	 * 
	 * @param sale the sales object that is to be inserted. 
	 * @return
	 */
	public int insertSalesOrder(SalesOrder sale) {

		int rc = -1;
		String query = "INSERT INTO SalesOrder(invoiceId, customerId, date, deliveryStatus, deliveryDate)  VALUES('"
				+ sale.getInvoice().getInvoiceNo()
				+ "','"
				+ sale.getCustomer().getId()
				+ "','"
				+ sale.getDate()
				+ "','"
				+ sale.getDeliveryStatus()
				+ "','"
				+ sale.getDeliveryDate() + "')";

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
	
	/**
	 * Finds the max id of the salesOrder table.
	 * @return the max id used in the salesOrder table. 
	 */
	public int findMaxId() {
		int id = 0;
		ResultSet rs;
		String query = "SELECT MAX(id) FROM salesOrder";
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rs = stmt.executeQuery(query);
			stmt.close();
			
			id = rs.getInt("id");
		}
		catch (Exception ex) {
			System.out.println("search failed");
		}
		return id;
	}

}