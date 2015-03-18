package CtrLayer;
import modelLayer.SalesOrder;

public class OrderCtr {
	
	private ProductCtr proCtr;
	private CustomerCtr custCtr;
	private SalesOrder salesOrders;

	public OrderCtr(ProductCtr proCtr, CustomerCtr custCtr,
			SalesOrder salesOrders) {
		super();
		this.proCtr = proCtr;
		this.custCtr = custCtr;
		this.salesOrders = salesOrders;
	}

}
