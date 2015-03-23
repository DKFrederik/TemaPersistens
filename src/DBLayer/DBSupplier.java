package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import modelLayer.Product;
import modelLayer.Supplier;

public class DBSupplier {
	
	private Connection con;
	
	public DBSupplier() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	public Supplier findSupplier(String name) {
		System.out.println("First");
		String wClause = "  name = '" + name + "'";
		return singleWhere(wClause);
	}

	private Supplier singleWhere(String wClause) {
		ResultSet results;
		Supplier supObj = new Supplier();
		System.out.println("Before query");
		String query = buildQuery(wClause);
		System.out.println(query);
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				supObj = buildSupplier(results);
				stmt.close();
				
			} else {
				supObj = null;
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return supObj;
	}

	private String buildQuery(String wClause) {
		String query = "SELECT name, address, country, phoneno, email FROM Supplier";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}
	
	private Supplier buildSupplier(ResultSet results) {
	
		Supplier sup = new Supplier();
		
		try {
			sup.setName(results.getString("name"));
			sup.setAddress(results.getString("address"));
			sup.setCountry(results.getString("country"));
			sup.setPhoneno(results.getString("phoneno"));
			sup.setEmail(results.getString("email"));
			
		}
		catch (Exception e) {
			System.out.println("Error in building supplier");
		}
		
		return sup;
	}
}
