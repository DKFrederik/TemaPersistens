package modelLayer;
import java.sql.Date;
import java.util.ArrayList;

public class Invoice {

	private int invoiceNo;
	private Date paymentDate;
	private double amount;
	private ArrayList<SalesOrder> salesOrders;
	
	public Invoice(int invoiceNo, Date paymentDate, double amount) {
		this.invoiceNo = invoiceNo;
		this.paymentDate = paymentDate;
		this.amount = amount;
		salesOrders = new ArrayList<SalesOrder>();
	}

	public Invoice(SalesOrder sale, double amount) {
		java.sql.Date d1 = new java.sql.Date(1234);
		System.out.println(d1.getTime());
		salesOrders = new ArrayList<SalesOrder>();
		salesOrders.add(sale);
		this.amount = amount;
	}
	/**
	 * @return the invoiceNo
	 */
	public int getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the salesOrders
	 */
	public ArrayList<SalesOrder> getSalesOrders() {
		return salesOrders;
	}

	/**
	 * @param salesOrders the salesOrders to set
	 */
	public void setSalesOrders(ArrayList<SalesOrder> salesOrders) {
		this.salesOrders = salesOrders;
	}
	
	
	

}
