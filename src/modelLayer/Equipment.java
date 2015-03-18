package modelLayer;

/**
 * @author Nichlas, Claus, Frederik, Peter
 * @version 0.1
 *
 */
public class Equipment extends Product {
	
	private String type;
	private String desc;

	/**

	 * @param id product id.
	 * @param name product name.
	 * @param supplier name of the supplier.
	 * @param purchasePrice the price of the product.
	 * @param salesPrice what the product retails for.
	 * @param rentPrice price of renting.
	 * @param originCountry Country where made.
	 * @param minStock the minimumstock of the product.
	 * @param stock the amount stock
	 * @param type the type of equipment.
	 * @param desc a description of the equipment.
	 */
	public Equipment(int id, int stock, int minStock, String name, String originCountry,
			double purchasePrice, double rentPrice, double salesPrice,
			Supplier supplier, String type, String desc) 
	{
		super(id, stock, minStock, name, originCountry, purchasePrice, rentPrice,
				salesPrice, supplier);
		this.type = type;
		this.desc = desc;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public String getDesc() 
	{
		return desc;
	}

	public void setDesc(String desc) 
	{
		this.desc = desc;
	}
	
}