package modelLayer;
 /** 
  * Product class.
  * Details a product. 
  * @author Nichlas, Peter, Claus, Frederik.
  * @version 0.1
  *
  */

public class Product {
	
	private int id;
	private int minStock;
	private int stock;
	private String name;
	private String countryOfOrigin;
	private double purchasePrice;
	private double rentPrice;
	private double salesPrice;
	private Supplier supplier;
	
	public Product() {
		
	}
	
	
	/**
	 * @param id product id.
	 * @param name product name.
	 * @param supplier name of the supplier.
	 * @param purchasePrice the price of the product.
	 * @param salesPrice what the product retails for.
	 * @param rentPrice price of renting.
	 * @param originCountry Country where made.
	 * @param minStock the minimumstock of the product
	 */
	
	public Product(int id, int stock, int minStock, String name, String originCountry,
			double purchasePrice, double rentPrice, double salesPrice,
			Supplier supplier) 
	{
		this.id = id;
		this.stock = stock;
		this.minStock = minStock;
		this.name = name;
		this.countryOfOrigin = originCountry;
		this.purchasePrice = purchasePrice;
		this.rentPrice = rentPrice;
		this.salesPrice = salesPrice;
		this.supplier = supplier;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public int getMinStock() 
	{
		return minStock;
	}
	
	public void setMinStock(int minStock) 
	{
		this.minStock = minStock;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getCountryOfOrigin() 
	{
		return countryOfOrigin;
	}
	
	public void setCountryOfOrigin(String countryOfOrigin) 
	{
		this.countryOfOrigin = countryOfOrigin;
	}
	
	public double getPurchasePrice() 
	{
		return purchasePrice;
	}
	
	public void setPurchasePrice(double purchasePrice) 
	{
		this.purchasePrice = purchasePrice;
	}
	
	public double getRentPrice() 
	{
		return rentPrice;
	}
	
	public void setRentPrice(double rentPrice) 
	{
		this.rentPrice = rentPrice;
	}
	
	public double getSalesPrice() 
	{
		return salesPrice;
	}
	
	public void setSalesPrice(double salesPrice) 
	{
		this.salesPrice = salesPrice;
	}
	
	public Supplier getSupplier() 
	{
		return supplier;
	}
	
	public void setSupplier(Supplier supplier) 
	{
		this.supplier = supplier;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

}
