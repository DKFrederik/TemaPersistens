package guiLayer;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGui extends JFrame{
	private final JButton btnCustomer = new JButton("Customer");

	public static void main (String[] args){
		MainGui mainGui = new MainGui();
	}
	
	public MainGui() {
		setTitle("Main");
		setVisible(true);
		setPreferredSize(new Dimension(450, 300));
		pack();
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerGui cgui = new CustomerGui();
			}
		});
		btnCustomer.setBounds(138, 37, 148, 36);
		panel.add(btnCustomer);
		
		
		JButton btnProduct = new JButton("Product");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductGui pgui = new ProductGui();
			}
		});
		btnProduct.setBounds(138, 97, 148, 36);
		panel.add(btnProduct);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderGui ogui = new OrderGui();
			}
		});
		btnOrder.setBounds(138, 164, 148, 36);
		panel.add(btnOrder);
		// TODO Auto-generated constructor stub
	}
}
