package DBLayer;
import modelLayer.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * DBCustomer.java
 * @author kbh
 * @version 5. oktober 2006, 2011.02.16
 *
 */
public class DBCustomer {
    private  Connection con;
    /** Creates a new instance of DBCustomer */
    public DBCustomer() {
      con = DBConnection.getInstance().getDBcon();
    }
    
  //Implements the methods from the interface
    // get all customers
    public ArrayList<Customer> getAllCustomers(boolean retriveAssociation)
    {
        return miscWhere("", retriveAssociation);
    }
    //get one customer having the phoneno
    public Customer findCustomer(String phoneno, boolean retriveAssociation)
    {   String wClause = "  phoneno = '" + phoneno + "'";
        return singleWhere(wClause, retriveAssociation);
    }
    //find one customer having the fname
    public Customer searchCustomerFname(String attValue, boolean retriveAssociation)
    {
        String wClause = "fname like '%" + attValue + "%'";
        System.out.println("SearchCustomer " + wClause);
        return singleWhere(wClause, retriveAssociation);
    }
      //find one customer having the lname
    public Customer searchCustomerLname(String attValue, boolean retriveAssociation)
    {
        String wClause = "lname = '" + attValue + "'";
        System.out.println("SearchCustomer " + wClause);
        return singleWhere(wClause, retriveAssociation);
    }
   
     //insert a new customer
    
    public int insertCustomer(Customer cus) throws Exception
    {  //call to get the next phoneno number
    
       int rc = -1;
	   String query="INSERT INTO Customer(fname, lname, address, zipcode, phoneno, email, type)  VALUES('"+
	  		cus.getFname()  + "','"  +
		    cus.getLname()  + "','"  +
            cus.getAddress() + "','"  +
            cus.getZipcode() + "','"  +
            cus.getPhoneNo() + "','"  +
            cus.getEmail() + "','"  +
            cus.getType() + "')";

       System.out.println("insert : " + query);
      try{ // insert new Customer
          Statement stmt = con.createStatement();
          stmt.setQueryTimeout(5);
     	  rc = stmt.executeUpdate(query);
          stmt.close();
      }//end try
       catch(SQLException ex){
          System.out.println("Customer ikke oprettet");
          throw new Exception ("Customer is not inserted correct");
       }
       return(rc);
    }

	public int updateCustomer(Customer cus)
	{
		Customer cusObj  = cus;
		int rc=-1;

		String query="UPDATE customer SET "+
		 	  "fname ='"+ cusObj.getFname()+"', "+
		 	  "lname ='"+ cusObj.getLname() + "', " +
                          "address ='"+ cusObj.getAddress() + "', " +
                          "zipcode ='"+ cusObj.getZipcode() + "' " +
                          "phoneno ='"+ cusObj.getPhoneNo() + "' " +
                          "email ='"+ cusObj.getEmail() + "' " +
                          "type ='"+ cusObj.getType() + "' " +
		          " WHERE phoneNo = '"+ cusObj.getPhoneNo() + "'";
                System.out.println("Update query:" + query);
  		try{ // update customer
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	 	rc = stmt.executeUpdate(query);

	 	 	stmt.close();
		}//slut try
	 	catch(Exception ex){
	 	 	System.out.println("Update exception in customer db: "+ex);
	  	}
		return(rc);
	}
	
	public int delete(String phoneNo)
	{
               int rc=-1;
	  
	  	String query="DELETE FROM customer WHERE phoneno = '" +
				phoneNo + "'";
                System.out.println(query);
	  	try{ // delete from customer
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	rc = stmt.executeUpdate(query);
	 	  	stmt.close();
  		}//slut try	
   	        catch(Exception ex){
	 	  	System.out.println("Delete exception in customer db: "+ex);
   	        }
		return(rc);
	}
	
	//private methods
    //michWere is used whenever we want to select more than one customer
	
	 
	private ArrayList<Customer> miscWhere(String wClause, boolean retrieveAssociation)
	{
            ResultSet results;
	    ArrayList<Customer> list = new ArrayList<Customer>();	
		
	    String query =  buildQuery(wClause);
  
            try{ // read the customer from the database
		Statement stmt = con.createStatement();
	 	stmt.setQueryTimeout(5);
	 	results = stmt.executeQuery(query);
	 	
	
		while( results.next() ){
	     	 Customer cusObj = new Customer();
		 cusObj = buildCustomer(results);	
                 list.add(cusObj);	
		}//end while
                 stmt.close();     
                 
                 
                 /*if(retrieveAssociation)
                 {   //The supervisor and department is to be build as well
                     for(Customer cusObj : list){
                        String superphoneno = cusObj.getSupervisor().getSsn();
                        Customer superEmp = singleWhere(" phoneno = '" + superphoneno + "'",false);
                        cusObj.setSupervisor(superEmp);
                        System.out.println("Supervisor is seleceted");
                       // here the department has to be selected as well
                     }
                 }//end if   
*/			
                 
		}//slut try	
	 	catch(Exception e){
	 		System.out.println("Query exception - select: "+e);
			e.printStackTrace();
	 	}
		return list;
	}
	
	//Singelwhere is used when we only select one customer 	
	private Customer singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		Customer cusObj = new Customer();
                
	        String query =  buildQuery(wClause);
                System.out.println(query);
		try{ // read the customer from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() ){
                            cusObj = buildCustomer(results);
                            //assocaition is to be build
                            stmt.close();
                            
                            
                            /*if(retrieveAssociation)
                            {   //The supervisor and department is to be build as well
                                String superphoneno = cusObj.getSupervisor().getSsn();
                                Customer superEmp = singleWhere(" phoneno = '" + superphoneno + "'",false);
                                cusObj.setSupervisor(superEmp);
                                System.out.println("Supervisor is seleceted");
                               // here the department has to be selected as well
*/                           
                            //}
                            
                            
			}
            else{ //no customer was found
                cusObj = null;
                        }
		}//end try	
	 	catch(Exception e){
	 		System.out.println("Query exception: "+e);
	 	}
		return cusObj;
	}
	//method to build the query
	private String buildQuery(String wClause)
	{
	    String query="SELECT fname, lname, address, zipcode, phoneno, email, type FROM customer";
		
		if (wClause.length()>0)
			query=query+" WHERE "+ wClause;
			
		return query;
	}
	//method to build an customer object
	private Customer buildCustomer(ResultSet results)
      {   Customer cusObj = new Customer();
          try{ // the columns from the table customers are used
                cusObj.setFname(results.getString("fname"));
                cusObj.setLname(results.getString("lname"));
                cusObj.setAddress(results.getString("address"));
                cusObj.setZipcode(results.getString("zipcode"));
                cusObj.setPhoneNo(results.getString("phoneno"));
                cusObj.setEmail(results.getString("email"));
                cusObj.setType(results.getString("type"));
          }
         catch(Exception e)
         {
             System.out.println("error in building the customer object");
         }
         return cusObj;
      }

	
	public Customer searchCustomerPhoneNo(String phoneno, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}
 
}  
    

