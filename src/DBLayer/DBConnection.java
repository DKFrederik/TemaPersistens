package DBLayer;

import java.sql.*;

/**
 * @Author Kis Boisen Hansen
 * @Version 2006.10.02 - revised marts 2009 FEN
 * @version 2010.10.07 - new connection 
 * @version 2011.11.02 - new methods to handle transaction
 * This class is used to create the connection to the database
 * It is implemented as a singleton. The constructor is private to ensure that only
 * one object of the class is generated
 * Version for Sql Server 2014 the database i located on kraka.ucn.dk
 */

public class DBConnection
{   //Constants used to get access to the database
	//SQL Server
   private static final String  driver = "jdbc:sqlserver://kraka.ucn.dk:1433";
   //private static final String  driver = "jdbc:sqlserver://localhost:1433";
   private static final String  databaseName = ";databaseName=dmab0914_2Sem_4";
   // SQL Server
   //
   private static String  userName = ";user=dmab0914_2Sem_4";
   //private static String  userName = ";user=sa";
   //private static String password = ";password=masterkey";
   private static String password = ";password=IsAllowed";
    
    private DatabaseMetaData dma;
    private static Connection con;
    // an instance of the class is generetated
    private static DBConnection  instance = null;

    // the constructor is private to ensure that only one object of this class is created
    private DBConnection()
    {
    	String url = driver + databaseName + userName + password;

        try{
            //load af driver
            //SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Load af class ok");
          
        }
        catch(Exception e){
            System.out.println("Can not find the driver");
            System.out.println(e.getMessage());
        }//end catch
        try{
            //connection to the database
           
            con = DriverManager.getConnection(url);
            //set autocommit
            con.setAutoCommit(true);
            dma = con.getMetaData(); // get meta data
            System.out.println("Connection to " + dma.getURL());
            System.out.println("Driver " + dma.getDriverName());
            System.out.println("Database product name " + dma.getDatabaseProductName());
        }//end try
        catch(Exception e){

            System.out.println("Problems with the connection to the database");
            System.out.println(e.getMessage());
            System.out.println(url);
        }//end catch
    }//end  constructor
	   
  //closeDb: closes the connection to the database
    public static void closeConnection()
    {
       	try{
            con.close();
            System.out.println("The connection is closed");
        }
         catch (Exception e){
            System.out.println("Error trying to close the database " +  e.getMessage());
         }
    }//end closeDB
		
    //getDBcon: Get-method, returns the connection to the database
    public  Connection getDBcon()
    {
       return con;
    }
    //this method is used to get the instance of the connection
    public static DBConnection getInstance()
    {
        if (instance == null)
        {
          instance = new DBConnection();
        }
        return instance;
    }
    public static void startTransaction()
    { try{
        con.setAutoCommit(false);
        }
      catch(Exception e){
        System.out.println("fejl start transaction");
        System.out.println(e.getMessage());
      }
    }
    public static void commitTransaction()
    { try{
        con.setAutoCommit(true);
        }
      catch(Exception e){
        System.out.println("fejl commit transaction");
        System.out.println(e.getMessage());
      }
    }
    public static void rollbackTransaction()
    { try{
        con.rollback();
        con.setAutoCommit(true);
        }
      catch(Exception e){
        System.out.println("fejl rollback transaction");
        System.out.println(e.getMessage());
      }
    }
}//end DbConnection
