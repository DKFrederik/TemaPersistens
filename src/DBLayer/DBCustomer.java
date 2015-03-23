package DBLayer;

import modelLayer.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * @Author 	Frederik, Nichlas, Claus og Peter
 * @date	20-03-2015
 * DBCustomer has the purpose of establishing a connection and execute several queries to the database, such
 * as search, insert, delete and update.
 */

public class DBCustomer {
	private Connection con;
	
	public DBCustomer() {
		con = DBConnection.getInstance().getDBcon();
	}

	/**
	 * 
	 * @param phoneno the Customer objects phone no.
	 * @return the Customer matching the phone no.
	 */
	public Customer findCustomer(String phoneno) {
		String wClause = "  phoneno = '" + phoneno + "'";
		return searchWhere(wClause);
	}
	
	/**
	 * 
	 * @param cus a Customer object to be inserted.
	 * @return 
	 */
	public int insertCustomer(Customer cus) {

		int rc = -1;
		String query = "INSERT INTO Customer(fname, lname, address, zipcode, phoneno, email, type)  VALUES('"
				+ cus.getFname()
				+ "','"
				+ cus.getLname()
				+ "','"
				+ cus.getAddress()
				+ "','"
				+ cus.getZipcode()
				+ "','"
				+ cus.getPhoneNo()
				+ "','"
				+ cus.getEmail()
				+ "','"
				+ cus.getType() + "')";

		System.out.println("insert : " + query);
		
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("Customer is not inserted correct");
		}
		return (rc);
	}

	/**
	 * 
	 * @param cus The customer object that is to be updated in the database.
	 * @return
	 */
	public int updateCustomer(Customer cus) {
		Customer cusObj = cus;
		int rc = -1;

		String query = "UPDATE customer SET " + "fname ='" + cusObj.getFname()
				+ "', " + "lname ='" + cusObj.getLname() + "', " + "address ='"
				+ cusObj.getAddress() + "', " + "zipcode ='"
				+ cusObj.getZipcode() + "' " + "phoneno ='"
				+ cusObj.getPhoneNo() + "' " + "email ='" + cusObj.getEmail()
				+ "' " + "type ='" + cusObj.getType() + "' "
				+ " WHERE phoneNo = '" + cusObj.getPhoneNo() + "'";
		System.out.println("Update query:" + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		
		catch (Exception ex) {
			System.out.println("Update exception in customer db: " + ex);
		}
		return (rc);
	}

	
	/**
	 * 
	 * @param phoneNo The phone number of the customer that is to be removed.
	 * @return
	 */
	public int deleteCustomer(String phoneNo) {
		int rc = -1;

		String query = "DELETE FROM customer WHERE phoneno = '" + phoneNo + "'";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("Delete exception in customer db: " + ex);
		}
		return (rc);
	}

	/**
	 * 
	 * @param wClause where clause for sql query
	 * @return a Customer object
	 */
	private Customer searchWhere(String wClause) {
		ResultSet results;
		Customer cusObj = new Customer();

		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) 
			{
				cusObj = buildCustomer(results);
				stmt.close();
			} 
			else 
			{
				cusObj = null;
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return cusObj;
	}

	/**
	 * 
	 * @param wClause where clause for SQL query.
	 * @return A String formatted as a query.
	 */
	private String buildQuery(String wClause) {
		String query = "SELECT fname, lname, address, zipcode, phoneno, email, type FROM customer";

		if (wClause.length() > 0)
			query += " WHERE " + wClause;

		return query;
	}

	/**
	 * 
	 * @param results ResultSet used for building the customer
	 * @return The build customer object
	 */
	private Customer buildCustomer(ResultSet results) {
		Customer cusObj = new Customer();
		try 
		{
			cusObj.setFname(results.getString("fname"));
			cusObj.setLname(results.getString("lname"));
			cusObj.setAddress(results.getString("address"));
			cusObj.setZipcode(results.getString("zipcode"));
			cusObj.setPhoneNo(results.getString("phoneno"));
			cusObj.setEmail(results.getString("email"));
			cusObj.setType(results.getString("type"));
		} catch (Exception e) {
			System.out.println("error in building the customer object");
		}
		return cusObj;
	}
}
