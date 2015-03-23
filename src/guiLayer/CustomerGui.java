package guiLayer;

import CtrLayer.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;

import modelLayer.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.TextEvent;

import javax.swing.JLabel;

public class CustomerGui extends JFrame {
	private JTextField textFname;
	private JTextField textLname;
	private JTextField textAddress;
	private JTextField textZipCode;
	private JTextField textPhoneNo;
	private JTextField textEmail;
	private JTextField textType;
	private CustomerCtr cusCtr;
	private JLabel failMsg;
	
	public CustomerGui() {
		setTitle("Customer");
		setVisible(true);
		setPreferredSize(new Dimension(500, 500));
		pack();
		
		cusCtr = new CustomerCtr();
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 433, 248);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		failMsg = new JLabel("");
		failMsg.setBounds(12, 148, 56, 16);
		panel.add(failMsg);
		
		textFname = new JTextField();
		textFname.setBounds(12, 29, 116, 22);
		panel.add(textFname);
		textFname.setColumns(10);
		
		textLname = new JTextField();
		textLname.setColumns(10);
		textLname.setBounds(140, 29, 116, 22);
		panel.add(textLname);
		
		textAddress = new JTextField();
		textAddress.setColumns(10);
		textAddress.setBounds(278, 29, 116, 22);
		panel.add(textAddress);
		
		textZipCode = new JTextField();
		textZipCode.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(textZipCode.getText().length() == 4) {
					try {
					Integer.parseInt(textZipCode.getText());
					}
					catch (NumberFormatException ne) {
						System.out.println(ne.getMessage());
						textZipCode.setText("");
						failMsg.setText("Incorrect Zip code");
					}
				}
				else {
					textZipCode.setText("");
					failMsg.setText("Incorrect Zip code");
				}
			}
		});
		textZipCode.setColumns(10);
		textZipCode.setBounds(12, 75, 116, 22);
		panel.add(textZipCode);
		
		textPhoneNo = new JTextField();
		textPhoneNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					Integer.parseInt(textPhoneNo.getText());
					failMsg.setText("");
					}
					catch (NumberFormatException ne) {
						System.out.println(ne.getMessage());
						textPhoneNo.setText("");
						failMsg.setText("Phone invalid ");
					}
			}
		});
		textPhoneNo.setColumns(10);
		textPhoneNo.setBounds(140, 75, 116, 22);
		panel.add(textPhoneNo);
		
		textEmail = new JTextField();
		textEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					if(textEmail.getText().contains("@")) {
					
					}
					else {
						textEmail.setText("");
						failMsg.setText("Email incorrect ");
					}
				}
				catch(NullPointerException np) {
					System.out.println(np.getMessage());
					failMsg.setText("Email incorrect ");
				}
			}
		});
		textEmail.setColumns(10);
		textEmail.setBounds(278, 75, 116, 22);
		panel.add(textEmail);
		
		textType = new JTextField();
		textType.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(textType.getText().equals("p") || textType.getText().equals("b")) {
				
				}
				else {
					textType.setText("");
					failMsg.setText("Type incorrect ");
				}
			}
		
		});
		textType.setColumns(10);
		textType.setBounds(12, 123, 116, 22);
		panel.add(textType);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer c = cusCtr.getCustomer(textPhoneNo.getText());
				if(c != null){
					String s = Character.toString(c.getType());
					textFname.setText(c.getFname());
					textLname.setText(c.getLname());
					textAddress.setText(c.getAddress());
					textEmail.setText(c.getEmail());
					textPhoneNo.setText(c.getPhoneNo());
					textZipCode.setText(c.getZipcode());
					textType.setText(s);
				}
			}
		});
		btnFind.setBounds(121, 213, 97, 25);
		panel.add(btnFind);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = textFname.getText();
				String lname = textLname.getText();
				String address = textAddress.getText();
				String email = textEmail.getText();
				String phoneNo = textPhoneNo.getText();
				String zipCode = textZipCode.getText();
				char type = textType.getText().charAt(0);
				Customer c = new Customer(fname, lname, address, zipCode, phoneNo, email, type);
				if(cusCtr.updateCustomer(c) > 0){
					System.out.println("Customer updated ");
				}
				else {
					System.out.println("Customer not updated");
				}
			}
		});
		btnUpdate.setBounds(12, 213, 97, 25);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cusCtr.deleteCustomer(textPhoneNo.getText()) > 0){
					System.out.println("Customer deleted");
				}
				else {
					System.out.println("Customer not deleted");
				}
			}
		});
		btnDelete.setBounds(123, 175, 97, 25);
		panel.add(btnDelete);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = textFname.getText();
				String lname = textLname.getText();
				String address = textAddress.getText();
				String email = textEmail.getText();
				String phoneNo = textPhoneNo.getText();
				String zipCode = textZipCode.getText();
				char type = textType.getText().charAt(0);
				Customer c = new Customer(fname, lname, address, zipCode, phoneNo, email, type);
				if(cusCtr.insertCustomer(c) > 0){
					System.out.println("Customer created ");
				}
				else {
					System.out.println("Customer not created");
				}
			}
		});
		btnCreate.setBounds(12, 175, 97, 25);
		panel.add(btnCreate);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(12, 13, 77, 16);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(143, 13, 77, 16);
		panel.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(280, 13, 77, 16);
		panel.add(lblAddress);
		
		JLabel lblZipCode_1 = new JLabel("Zip code");
		lblZipCode_1.setBounds(12, 60, 77, 16);
		panel.add(lblZipCode_1);
		
		JLabel lblPhoneNumber = new JLabel("Phone number");
		lblPhoneNumber.setBounds(140, 60, 97, 16);
		panel.add(lblPhoneNumber);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(277, 60, 97, 16);
		panel.add(lblEmail);
		
		JLabel lblZipCode = new JLabel("Type");
		lblZipCode.setBounds(12, 108, 97, 16);
		panel.add(lblZipCode);
	}
}
