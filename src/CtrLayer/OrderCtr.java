package ctrLayer;
import DBLayer.*;
import modelLayer.*;


public class OrderCtr {
	private static final double DISCOUNT = 0.95;
	private CustomerCtr custCtr;
	private ProductCtr proCtr;
	private SalesOrder sale;
	private DBSalesOrder dBsale;
	private DBPartOrder dbPartOrder;

	public OrderCtr() 
	{
		this.dBsale = new DBSalesOrder();
		this.proCtr = new ProductCtr();
		this.custCtr = new CustomerCtr();
		this.dbPartOrder = new DBPartOrder();
	}
	
	public void createOrder()
	{
		sale = new SalesOrder();
	}

	public void addPartOrder(int nrOfItems, Product pro)
	{
		PartOrder pOrder = new PartOrder(pro,nrOfItems);
		sale.addPartOrder(pOrder);
	}
	
	public void addCustomer(String phoneno)
	{
		sale.setCustomer(custCtr.getCustomer(phoneno));
	}
	
	public void completeOrder()
	{
		int size = sale.getPartOrders().size();
		dBsale.insertSalesOrder(sale);
		
		int orderId = dBsale.findMaxId();
		
		for(int i = 0; i < size; i++)
		{
			dbPartOrder.insertPartOrder(sale.getPartOrders().get(i),orderId);
		}
	}
	
	public double getPrice()
	{
		double totalPrice = sale.getPrice();
		if(sale.getCustomer().getType() == 'p' && sale.getPrice() < 2500.00)
		{
			totalPrice += 45.00;
		}
		
		else if(sale.getCustomer().getType() == 'b' && sale.getPrice() > 1500.00)
		{
			totalPrice *= DISCOUNT;
		}
		
		return totalPrice;
	}
}
