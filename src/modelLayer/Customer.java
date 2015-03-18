package modelLayer;

public class Customer {
	private int id;
	private String fname;
	private String lname;
	private String address;
	private String zipcode;
	private String phoneNo;
	private String email;
	private char type;
	
	public Customer(int id, String fname, String lname, String address,
			String zipcode, String phoneNo, String email, char type) 
	{
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.zipcode = zipcode;
		this.phoneNo = phoneNo;
		this.email = email;
		this.type = type;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getFname() 
	{
		return fname;
	}
	
	public void setFname(String fname) 
	{
		this.fname = fname;
	}
	
	public String getLname() 
	{
		return lname;
	}
	
	public void setLname(String lname) 
	{
		this.lname = lname;
	}
	
	public String getAddress() 
	{
		return address;
	}
	
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public String getZipcode() 
	{
		return zipcode;
	}
	
	public void setZipcode(String zipcode) 
	{
		this.zipcode = zipcode;
	}
	
	public String getPhoneNo() 
	{
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) 
	{
		this.phoneNo = phoneNo;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public char getType() 
	{
		return type;
	}
	
	public void setType(char type) 
	{
		this.type = type;
	}
	
	
}
