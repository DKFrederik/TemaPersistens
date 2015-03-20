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

	// get one customer with attribute which matche the phoneno parameter 
	public Customer findCustomer(String phoneno) {
		String wClause = "  phoneno = '" + phoneno + "'";
		return searchWhere(wClause);
	}

	// find one customer having the fname
	public Customer searchCustomerFname(String attValue) {
		String wClause = "fname like '%" + attValue + "%'";
		System.out.println("SearchCustomer " + wClause);
		return searchWhere(wClause);
	}

	// find one customer having the lname
	public Customer searchCustomerLname(String attValue) {
		String wClause = "lname = '" + attValue + "'";
		System.out.println("SearchCustomer " + wClause);
		return searchWhere(wClause);
	}

	
	// Insert
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

	//Update
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

	
	//Deletes a customer tuble from the database which matches the phoneno 
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

	//searches the database for a customer using the parameter in the quiry-String 
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

	// builds a query string to be executet. 
	private String buildQuery(String wClause) {
		String query = "SELECT fname, lname, address, zipcode, phoneno, email, type FROM customer";

		if (wClause.length() > 0)
			query += " WHERE " + wClause;

		return query;
	}

	// builds a customer object from the resultset and returns it.
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
