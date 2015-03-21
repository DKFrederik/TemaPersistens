package modelLayer;

/**
 * Clothing is a specialised product.
 * @author Nichlas, Peter, Claus, Frederik
 * @version 0.1
 */
public class Clothing extends Product {
	
	private String size;
	private String colour;
	
	/**
	 * @param id product id.
	 * @param name product name.
	 * @param supplier name of the supplier.
	 * @param purchasePrice the price of the product.
	 * @param salesPrice what the product retails for.
	 * @param rentPrice price of renting.
	 * @param originCountry Country where made.
	 * @param minStock the minimumstock of the product
	 * @param stock the amount stock
	 * @param size the clothes size, small, large.. 
	 * @param colour the colour of the clothes
	 */
	public Clothing(int id, int stock, int minStock, String name, String originCountry,
			double purchasePrice, double rentPrice, double salesPrice,
			Supplier supplier, String size, String colour) 
	{
		super(id, stock, minStock, name, originCountry, purchasePrice, rentPrice,
				salesPrice, supplier);
		this.size = size;
		this.colour = colour;
	}
	
	public Clothing() {
		
	}
	public String getSize() 
	{
		return size;
	}
	
	public void setSize(String size) 
	{
		this.size = size;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}

}
