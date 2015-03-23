package modelLayer;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Nichlas, Claus, Frederik, Peter
 *
 */

public class SalesOrder {

	private int id;
	private Date date;
	private Date deliveryDate;
	private char deliveryStatus;
	private ArrayList<PartOrder> partOrders;
    private Customer customer;
    private Invoice invoice;
	
	public SalesOrder(Date date, Date deliveryDate, char deliveryStatus, Customer customer, Invoice invoice) {
		this.date = date;
		this.deliveryDate = deliveryDate;
		this.deliveryStatus = deliveryStatus;
		this.customer = customer;
		this.invoice = invoice;
		partOrders = new ArrayList<PartOrder>();
	}
	
	public SalesOrder() {
		date = new Date();
		deliveryDate = new Date();
		partOrders = new ArrayList<PartOrder>();
	}

	public double getPrice()
	{
		double prize = 0;
		int size = partOrders.size();
		for(int i = 0; i < size; i++)
		{
			prize += partOrders.get(i).getItemPrice() * partOrders.get(i).getNrOfItems();
		}
		return prize;
	}
	
	public void addPartOrder(PartOrder partOrder)
	{
		partOrders.add(partOrder);
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the deliveryDate
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the deliveryStatus
	 */
	public char getDeliveryStatus() {
		return deliveryStatus;
	}

	/**
	 * @param deliveryStatus the deliveryStatus to set
	 */
	public void setDeliveryStatus(char deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	/**
	 * @return the partOrders
	 */
	public ArrayList<PartOrder> getPartOrders() {
		return partOrders;
	}

	/**
	 * @param partOrders the partOrders to set
	 */
	public void setPartOrders(ArrayList<PartOrder> partOrders) {
		this.partOrders = partOrders;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Invoice getInvoice() {
		return this.invoice;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
}
