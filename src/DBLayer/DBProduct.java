package DBLayer;

import modelLayer.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * DBProduct.java
 * 
 * @author Peter, Frederik, Claus og Nichlas.
 * @version 20.03.2015
 *
 */
public class DBProduct {
	private Connection con;

	/**
	 * Constructor for DBProduct() class. 
	 */
	public DBProduct() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	/**
	 * 
	 * @param retriveAssociation Determines if associations should be retrieved or not. 
	 * @return An ArrayList of Product objects.
	 */
	public ArrayList<Product> getAllProducts(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	/**
	 * 
	 * @param name The name of the product that you wish to find.
	 * @param retriveAssociation Determines if associations should be retrieved or not. 
	 * @return A Product Object if found, null if not.
	 */
	public Product findProduct(String name, boolean retriveAssociation) {
		String wClause = "  name = '" + name + "'";
		return singleWhere(wClause, retriveAssociation);
	}
	
	/**
	 * 
	 * @param pro The product that is to be inserted
	 * @return 
	 * @throws Exception 
	 */
	public int insertProduct(Product pro) throws Exception {

		int rc = -1;
		String query = "INSERT INTO Product(supplier, name, puchasePrice, salesPrice, rentPrice, countryOfOrigin, minStock, stock, size, colour, type, description, fabric, calibre)  VALUES('"
				//+ pro.getSupplier()
				//+ "','"
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

	/**
	 * 
	 * @param cus The customer object that is to be updated in the database.
	 * @return
	 */
	public int updateProduct(Product pro, String oldName) {
		Product proObj = pro;
		int rc = -1;

		String query = "UPDATE Product SET " 
				//+ "supplier ='" + proObj.getSupplier().getName() + "', " 
				+ "name ='" + proObj.getName() + "', " 
				+ "purchasePrice ='" + proObj.getPurchasePrice() + "', " 
				+ "salesPrice ='" + proObj.getSalesPrice() + "', " 
				+ "rentPrice ='" + proObj.getRentPrice() + "', " 
				+ "countryOfOrigin ='" + proObj.getCountryOfOrigin() + "', " 
				+ "minStock ='" + proObj.getMinStock() + "', "
				+ "stock ='" + proObj.getStock() + "' ";
		if(proObj instanceof Clothing) {
			query += "size ='" + ((Clothing) proObj).getSize() + "', "
					+ "colour ='" + ((Clothing) proObj).getColour() + "', ";
		}
		else if(proObj instanceof Equipment) {
			query += "type ='" + ((Equipment) proObj).getType() + "', "
					+ "description ='" + ((Equipment) proObj).getDesc() + "', ";
		}
		else if(proObj instanceof GunReplica) {
			query += "fabric ='" + ((GunReplica) proObj).getFabric() + "', "
					+ "calibre ='" + ((GunReplica) proObj).getCalibre() + "' ";
		}
		query += "WHERE name = '" + oldName + "'";
		System.out.println("Update query:" + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		
		catch (Exception ex) {
			System.out.println("Update exception in product db: " + ex);
		}
		return (rc);
	}

	public int deleteProduct(String proName) {
		int rc = -1;

		String query = "DELETE FROM Product WHERE name = '" + proName + "'";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("Delete exception in product db: " + ex);
		}
		return (rc);
	}

	/**
	 * 
	 * @param wClause where clause for the query.
	 * @param retrieveAssociation whether or not to retrieve associations.
	 * @return An ArrayList of products.
	 */
	private ArrayList<Product> miscWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Product> list = new ArrayList<Product>();

		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Product proObj = new Product();
				proObj = buildProduct(results);
				list.add(proObj);
			}// end while
			stmt.close();
			
			// Get suppliers around here

		}
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	private Product singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Product proObj = new Product();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				proObj = buildProduct(results);
				stmt.close();
				
				// get supplier should be implemented around here.
				
			} else {
				proObj = null;
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return proObj;
	}

	/**
	 * Builds a sql query
	 * @param wClause where clause.
	 * @return the build sql query.
	 */
	private String buildQuery(String wClause) {
		String query = "SELECT supplier, name, purchasePrice, salesPrice, rentPrice, countryOfOrigin, minStock, stock, size, colour, type, description, fabric, calibre FROM Product";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	/**
	 * Builds a product from a ResultSet.
	 * 
	 * @param results the results returned by the DMBS
	 * @return a product
	 */
	private Product buildProduct(ResultSet results)
	{
	
	try{
		if(results.getString("size") == null)
		{
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
		return null;
        
	}
	
	/**
	 * 
	 * @param results the ResultSet returned by the DMBS.
	 * @return a GunReplica object. 
	 */
	private GunReplica makeGunReplica(ResultSet results) {
		//DBSupplier dbSup = new DBSupplier();
		
		GunReplica gun = new GunReplica();
		try {
			gun.setName(results.getString("name"));
			//gun.setSupplier(dbSup.findSupplier(results.getString("supplier")));
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
	/**
	 * 
	 * @param results the ResultSet returned by the DMBS.
	 * @return a Equipment object. 
	 */
	private Equipment makeEquipment(ResultSet results) {
		//DBSupplier dbSup = new DBSupplier();
		Equipment eq = new Equipment();
		
		try {
			eq.setName(results.getString("name"));
			//eq.setSupplier(dbSup.findSupplier(results.getString("supplier")));
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
	
	/**
	 * 
	 * @param results the ResultSet returned by the DMBS.	 
	 * @return a Clothing object. 
	 */
	private Clothing makeClothing(ResultSet results) {
		//DBSupplier dbSup = new DBSupplier();
		Clothing c = new Clothing(); 
		
		try {
			c.setName(results.getString("name"));
			//c.setSupplier(dbSup.findSupplier(results.getString("supplier")));
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
	
}
