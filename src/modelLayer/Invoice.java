package modelLayer;
import java.sql.Date;
import java.util.ArrayList;

public class Invoice {

	private int invoiceNo;
	private Date paymentDate;
	private int amount;
	private ArrayList<SalesOrder> salesOrders;
	
	public Invoice(int invoiceNo, Date paymentDate, int amount) {
		super();
		this.invoiceNo = invoiceNo;
		this.paymentDate = paymentDate;
		this.amount = amount;
		
		salesOrders = new ArrayList<SalesOrder>();
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
	public int getAmount() {
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
