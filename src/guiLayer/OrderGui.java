package guiLayer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import modelLayer.PartOrder;
import modelLayer.SalesOrder;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import CtrLayer.*;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class OrderGui extends JFrame {
	private JTextField textProName;
	private JTextField textAmount;
	private JTextField textProPrice;
	private JScrollPane scrollPane;
	private JTable table;
	private JTable table_1;
	private JButton btnComplete;
	
	private DefaultTableModel model;
	
	private OrderCtr ordCtr;
	private ProductCtr proCtr;
	private CustomerCtr cusCtr;
	private JButton addCustomer;
	private JTextField textCusPhone;
	private JLabel lblNewLabel;
	private JTextField textFName;
	private JTextField textLName;
	private JLabel lblFirstName;
	private JButton BtnRemove;
	
	public OrderGui() {
		setTitle("Main");
		setVisible(true);
		setPreferredSize(new Dimension(500, 500));
		
		ordCtr = new OrderCtr();
		proCtr = new ProductCtr();
		cusCtr = new CustomerCtr();
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Name", "Amount", "Item Price"
				});

		table = new JTable();
		table.setModel(model);
		
		ordCtr.createOrder();
		getContentPane().setLayout(null);
		
		textProName = new JTextField();
		textProName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) 
			{
				if(proCtr.getProduct(textProName.getText()) != null) {
				}
				else {
					textProName.setText("");
				}
			}
		});
		textProName.setBounds(19, 242, 116, 22);
		getContentPane().add(textProName);
		textProName.setColumns(10);
		
		textAmount = new JTextField();
		textAmount.setBounds(146, 242, 116, 22);
		getContentPane().add(textAmount);
		textAmount.setColumns(10);
		
		JLabel lblProductName = new JLabel("Product name");
		lblProductName.setBounds(19, 226, 90, 16);
		getContentPane().add(lblProductName);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(146, 226, 56, 16);
		getContentPane().add(lblAmount);
		
		textProPrice = new JTextField();
		textProPrice.setEditable(false);
		textProPrice.setBounds(408, 295, 116, 22);
		getContentPane().add(textProPrice);
		textProPrice.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(408, 277, 56, 16);
		getContentPane().add(lblPrice);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPartOrder();
				updateTable();
					
			}
		});
		btnAdd.setBounds(19, 277, 116, 25);
		getContentPane().add(btnAdd);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(274, 46, 250, 218);
		getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		
		btnComplete = new JButton("Complete order");
		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordCtr.completeOrder();
				dispose();
			}
		});
		btnComplete.setBounds(19, 333, 123, 25);
		getContentPane().add(btnComplete);
		
		addCustomer = new JButton("Add Customer");
		addCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomer();
			}
		});
		addCustomer.setBounds(19, 138, 115, 25);
		getContentPane().add(addCustomer);
		
		textCusPhone = new JTextField();
		textCusPhone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					Integer.parseInt(textCusPhone.getText());
				}
				catch (NumberFormatException ne) {
					textCusPhone.setText("");
				}
			}
		});
		textCusPhone.setBounds(19, 59, 116, 22);
		getContentPane().add(textCusPhone);
		textCusPhone.setColumns(10);
		
		lblNewLabel = new JLabel("Customer phone");
		lblNewLabel.setBounds(19, 46, 101, 16);
		getContentPane().add(lblNewLabel);
		
		textFName = new JTextField();
		textFName.setBounds(19, 105, 116, 22);
		getContentPane().add(textFName);
		textFName.setColumns(10);
		
		textLName = new JTextField();
		textLName.setColumns(10);
		textLName.setBounds(146, 105, 116, 22);
		getContentPane().add(textLName);
		
		lblFirstName = new JLabel("Customer name");
		lblFirstName.setBounds(19, 87, 128, 16);
		getContentPane().add(lblFirstName);
		
		BtnRemove = new JButton("Remove");
		BtnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		BtnRemove.setBounds(146, 277, 116, 25);
		getContentPane().add(BtnRemove);
		
	}
	
	private void updateTable() {
		SalesOrder saleo = ordCtr.getOrder();
		model.setRowCount(0);
		System.out.println(saleo.getPartOrders().size());
		for(int i = 0; i<saleo.getPartOrders().size(); i++) 
		{
			Vector row = new Vector();
			row.add(saleo.getPartOrders().get(i).getProducts().getName());
			row.add(saleo.getPartOrders().get(i).getNrOfItems());
			row.add(saleo.getPartOrders().get(i).getItemPrice());
			model.addRow(row);
		}
	}
	
	private void addPartOrder() 
	{
		ordCtr.addPartOrder(Integer.parseInt(textAmount.getText()), proCtr.getProduct(textProName.getText()));
		textProPrice.setText(Double.toString(ordCtr.getPrice()));
	}
	
	private void addCustomer() 
	{
		ordCtr.getOrder().setCustomer(cusCtr.getCustomer(textCusPhone.getText()));
		textFName.setText(cusCtr.getCustomer(textCusPhone.getText()).getFname());
		textLName.setText(cusCtr.getCustomer(textCusPhone.getText()).getLname());
	}
}
