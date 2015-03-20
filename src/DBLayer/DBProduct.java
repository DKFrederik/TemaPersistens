package DBLayer;

import modelLayer.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * DBProduct.java
 * 
 * @author kbh
 * @version 5. oktober 2006, 2011.02.16
 *
 */
public class DBProduct {
	private Connection con;

	/** Creates a new instance of DBProduct */
	public DBProduct() {
		con = DBConnection.getInstance().getDBcon();
	}

	// Implements the methods from the interface
	// get all products
	public ArrayList<Product> getAllProducts(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	// get one product having the phoneno
	public Product findProduct(String name, boolean retriveAssociation) {
		String wClause = "  name = '" + name + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	// find one product having the fname
	public Product searchProductFname(String attValue,
			boolean retriveAssociation) {
		String wClause = "fname like '%" + attValue + "%'";
		System.out.println("SearchProduct " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

	// find one product having the lname
	public Product searchProductLname(String attValue,
			boolean retriveAssociation) {
		String wClause = "lname = '" + attValue + "'";
		System.out.println("SearchProduct " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

	// insert a new product

	public int insertProduct(Product pro) throws Exception { // call to get the
																// next phoneno
																// number

		int rc = -1;
		String query = "INSERT INTO Product(supplier, name, puchasePrice, salesPrice, rentPrice, countryOfOrigin, minStock, stock, size, colour, type, description, fabric, calibre)  VALUES('"
				+ pro.getSupplier()
				+ "','"
				+ pro.getName()
				+ "','"
				+ pro.getPurchasePrice()
				+ "','"
				+ pro.getSalesPrice()
				+ "','"
				+ pro.getRentPrice()
				+ "','"
				+ pro.getCountryOfOrigin()
				+ "','"
				+ pro.getMinStock() + "','" + pro.getStock() + "','";
		if (pro instanceof Clothing) {
			Clothing c = (Clothing) pro;
			query += c.getSize() + "','" + c.getColour() + "','" + "null"
					+ "','" + "null" + "','" + "null" + "','" + "null" + "'";
		} else if (pro instanceof Equipment) {
			Equipment e = (Equipment) pro;
			query += "null" + "','" + "null" + "','" + e.getType() + "','"
					+ e.getDesc() + "','" + "null" + "','" + "null" + "'";

		} else if (pro instanceof GunReplica) {
			GunReplica g = (GunReplica) pro;
			query += "null" + "','" + "null" + "','" + "null" + "','" + "null"
					+ "','" + g.getFabric() + "','" + g.getCalibre();
		}

		query += "')";

		System.out.println("insert : " + query);
		try { // insert new Product
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("Product ikke oprettet");
			throw new Exception("Product is not inserted correct");
		}
		return (rc);
	}

	public int updateProduct(Product cus) {
		Product proObj = cus;
		int rc = -1;

		String query = "UPDATE customer SET " + "fname ='" + proObj.getFname()
				+ "', " + "lname ='" + proObj.getLname() + "', " + "address ='"
				+ proObj.getAddress() + "', " + "zipcode ='"
				+ proObj.getZipcode() + "' " + "phoneno ='"
				+ proObj.getPhoneNo() + "' " + "email ='" + proObj.getEmail()
				+ "' " + "type ='" + proObj.getType() + "' "
				+ " WHERE phoneNo = '" + proObj.getPhoneNo() + "'";
		System.out.println("Update query:" + query);
		try { // update customer
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// slut try
		catch (Exception ex) {
			System.out.println("Update exception in customer db: " + ex);
		}
		return (rc);
	}

	public int delete(String phoneNo) {
		int rc = -1;

		String query = "DELETE FROM customer WHERE phoneno = '" + phoneNo + "'";
		System.out.println(query);
		try { // delete from customer
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// slut try
		catch (Exception ex) {
			System.out.println("Delete exception in customer db: " + ex);
		}
		return (rc);
	}

	// private methods
	// michWere is used whenever we want to select more than one product

	private ArrayList<Product> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Product> list = new ArrayList<Product>();

		String query = buildQuery(wClause);

		try { // read the customer from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Product proObj = new Product();
				proObj = buildProduct(results);
				list.add(proObj);
			}// end while
			stmt.close();

			/*
			 * if(retrieveAssociation) { //The supervisor and department is to
			 * be build as well for(Product proObj : list){ String superphoneno
			 * = proObj.getSupervisor().getSsn(); Product superEmp =
			 * singleWhere(" phoneno = '" + superphoneno + "'",false);
			 * proObj.setSupervisor(superEmp);
			 * System.out.println("Supervisor is seleceted"); // here the
			 * department has to be selected as well } }//end if
			 */

		}// slut try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	// Singelwhere is used when we only select one product
	private Product singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Product proObj = new Product();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { // read the product from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				proObj = buildProduct(results);
				// assocaition is to be build
				stmt.close();

				/*
				 * if(retrieveAssociation) { //The supervisor and department is
				 * to be build as well String supplierName =
				 * proObj.getSupplier().getName(); Product superEmp =
				 * singleWhere(" phoneno = '" + superphoneno + "'",false);
				 * proObj.setSupervisor(superEmp);
				 * System.out.println("Supervisor is seleceted"); // here the
				 * department has to be selected as well
				 * 
				 * }
				 */

			} else { // no product was found
				proObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return proObj;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT name, purchasePrice, salesPrice, rentPrice, countryOfOrigin, minStock, stock, size, colour, type, description, fabric, calibre FROM Product";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an product object
	private Product buildProduct(ResultSet results)
	{
	
	try{ // the columns from the table customers are used
		if(results.getString("size") == null)
		{
			System.out.println("size = null");
			if(results.getString("type") == null)
			{
				return makeGunReplica(results);
			}
			else
			{
				return makeEquipment(results);
			}
        }

		else
		{
			return makeClothing(results);
		}
          
        }
        catch(Exception e)
        {
             System.out.println("error in building the product object.");
        }
		return null; //Should not happen
        
	}

	private GunReplica makeGunReplica(ResultSet results) {
		
		GunReplica gun = new GunReplica();
		try {
			gun.setName(results.getString("name"));
			//gun.setSupplier(results.getString("supplier"));
			gun.setPurchasePrice(Double.parseDouble(results.getString("purchasePrice")));
			gun.setSalesPrice(Double.parseDouble(results.getString("salesPrice")));
			gun.setRentPrice(Double.parseDouble(results.getString("rentPrice")));
			gun.setCountryOfOrigin(results.getString("countryOfOrigin"));
			gun.setMinStock(Integer.parseInt(results.getString("minStock")));
			gun.setStock(Integer.parseInt(results.getString("stock")));
			gun.setFabric(results.getString("fabric"));
			gun.setCalibre(results.getString("calibre"));
		}
		catch (Exception e) {
			System.out.println("Error in building GunReplica object");
		}
		
		return gun;
	}
	
	private Equipment makeEquipment(ResultSet results) {
		
		Equipment eq = new Equipment();
		
		try {
			eq.setName(results.getString("name"));
			//gun.setSupplier(results.getString("supplier"));
			eq.setPurchasePrice(Double.parseDouble(results.getString("purchasePrice")));
			eq.setSalesPrice(Double.parseDouble(results.getString("salesPrice")));
			eq.setRentPrice(Double.parseDouble(results.getString("rentPrice")));
			eq.setCountryOfOrigin(results.getString("countryOfOrigin"));
			eq.setMinStock(Integer.parseInt(results.getString("minStock")));
			eq.setStock(Integer.parseInt(results.getString("stock")));
			eq.setType(results.getString("type"));
			eq.setDesc(results.getString("description"));
		}
		catch (Exception e) {
			System.out.println("Error in building Equipment object");
		}
		
		return eq;
	}
	
	private Clothing makeClothing(ResultSet results) {
		
		Clothing c = new Clothing(); 
		
		try {
			c.setName(results.getString("name"));
			//c.setSupplier(results.getString("supplier"));
			c.setPurchasePrice(Double.parseDouble(results.getString("purchasePrice")));
			c.setSalesPrice(Double.parseDouble(results.getString("salesPrice")));
			c.setRentPrice(Double.parseDouble(results.getString("rentPrice")));
			c.setCountryOfOrigin(results.getString("countryOfOrigin"));
			c.setMinStock(Integer.parseInt(results.getString("minStock")));
			c.setStock(Integer.parseInt(results.getString("stock")));
			c.setColour(results.getString("colour"));
			c.setSize(results.getString("size"));
		}
		catch (Exception e) {
			System.out.println("Error in building clothing object");
		}
		
		return c;
	}
	
	public Product searchProductPhoneNo(String phoneno, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

}
