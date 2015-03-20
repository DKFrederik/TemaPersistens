package ctrLayer;
import modelLayer.SalesOrder;

public class OrderCtr {
	
	private CustomerCtr custCtr;
	private ProductCtr proCtr;
	private SalesOrder sale;

	public OrderCtr() {
		super();
		this.proCtr = new ProductCtr();
		this.custCtr = new CustomerCtr();
		this.sale = new SalesOrder();
	}
	

}
