package modelLayer;

public class GunReplica extends Product {
	
	private String fabric;
	private String calibre;

	/**
	 * @param id product id.
	 * @param name product name.
	 * @param supplier name of the supplier.
	 * @param purchasePrice the price of the product.
	 * @param salesPrice what the product retails for.
	 * @param rentPrice price of renting.
	 * @param originCountry Country where made.
	 * @param stock the amount stock
	 * @param minStock the minimumstock of the product
	 * @param fabric maker of the gun
	 * @param calibre the guns caliber
	 */
	public GunReplica(int id, int stock, int minStock, String name, String originCountry,
			double purchasePrice, double rentPrice, double salesPrice,
			Supplier supplier, String fabric, String calibre) 
	{
		super(id, stock, minStock, name, originCountry, purchasePrice, rentPrice,
				salesPrice);
		this.fabric = fabric;
		this.calibre = calibre;
	}
	
	public GunReplica()
	{
		super();
	}

	public String getFabric() 
	{
		return fabric;
	}

	public void setFabric(String fabric) 
	{
		this.fabric = fabric;
	}

	public String getCalibre() 
	{
		return calibre;
	}

	public void setCalibre(String calibre) 
	{
		this.calibre = calibre;
	}
}
