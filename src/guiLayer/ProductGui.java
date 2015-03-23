package guiLayer;

import CtrLayer.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import modelLayer.Product;
import modelLayer.Supplier;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JRadioButton;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ProductGui extends JFrame{
	private JTextField textSupplier;
	private JTextField textSPrice;
	private JTextField textMinstock;
	private JTextField textPname;
	private JTextField textRPrice;
	private JTextField textCurStock;
	private JTextField textPPrice;
	private JTextField textCountry;
	private JLabel lblName;
	private JLabel lblPurchasePrice;
	private JLabel lblDfafa;
	private JLabel lblRentPrice;
	private JLabel lblCountryOfOrigin;
	private JLabel lblMinimumStock;
	private JLabel lblCurrentStock;
	private JTextField textSize;
	private JTextField textColour;
	private JTextField textType;
	private JTextField textDesc;
	private JTextField textFabric;
	private JTextField textCali;
	private JLabel lblSize;
	private JLabel lblColour;
	private JLabel lblDescription;
	private JLabel lblType;
	private JLabel lblFabric;
	private JLabel lblCalibre;
	private JLabel lblCloth;
	private JLabel lblEquipment;
	private JLabel lblGunReplica;
	
	private ProductCtr proCtr;
	
	public ProductGui() {
		
		setTitle("Product");
		setVisible(true);
		setPreferredSize(new Dimension(500, 500));
		pack();
		
		proCtr = new ProductCtr();
		getContentPane().setLayout(null);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product p = proCtr.getProduct(textPname.getText());
				if(p != null){
					//textSupplier.setText(p.getSupplier().getName());
					textPname.setText(p.getName());
					textPPrice.setText(Double.toString(p.getPurchasePrice()));
					textSPrice.setText(Double.toString(p.getSalesPrice()));
					textRPrice.setText(Double.toString(p.getRentPrice()));
					textCurStock.setText(Integer.toString(p.getStock()));
					textMinstock.setText(Integer.toString(p.getMinStock()));
					textCountry.setText(p.getCountryOfOrigin());
				}
			}
		});
		btnFind.setBounds(323, 279, 97, 25);
		getContentPane().add(btnFind);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Supplier supplier = proCtr.findSupplier((textSupplier.getText()));
					String pname = textPname.getText();
					double purchasePrice = Double.parseDouble(textPPrice.getText());
					double salesPrice = Double.parseDouble(textSPrice.getText());
					double rentPrice = Double.parseDouble(textRPrice.getText());
					String country = textCountry.getText();
					int minStock = Integer.parseInt(textMinstock.getText());
					int curStock = Integer.parseInt(textCurStock.getText());
					Product p = new Product(curStock, minStock, pname, country, purchasePrice, salesPrice, rentPrice);
					if(proCtr.insertProduct(p) > 0){
						System.out.println("Product created ");
					}
					else {
						System.out.println("Product not created");
					}
				}
				catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btnCreate.setBounds(12, 279, 97, 25);
		getContentPane().add(btnCreate);
		
		JButton button_2 = new JButton("Update");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Supplier supplier = proCtr.findSupplier(textSupplier.getText());
				String pname = textPname.getText();
				double purchasePrice = Double.parseDouble(textPPrice.getText());
				double salesPrice = Double.parseDouble(textSPrice.getText());
				double rentPrice = Double.parseDouble(textRPrice.getText());
				String country = textCountry.getText();
				int minStock = Integer.parseInt(textMinstock.getText());
				int curStock = Integer.parseInt(textCurStock.getText());
				Product p = new Product(curStock, minStock, pname, country, purchasePrice, rentPrice, salesPrice);
				if(proCtr.updateProduct(p, pname) > 0){
					System.out.println("Product updated ");
				}
				else {
					System.out.println("Product not updated");
				}
			}
		});
		button_2.setBounds(223, 279, 97, 25);
		getContentPane().add(button_2);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(proCtr.deleteProduct(textPname.getText()) > 0){
					System.out.println("Product deleted");
				}
				else {
					System.out.println("Product not deleted");
				}
			}
		});
		btnDelete.setBounds(121, 279, 97, 25);
		getContentPane().add(btnDelete);
		
		textSupplier = new JTextField();
		textSupplier.setColumns(10);
		textSupplier.setBounds(12, 29, 116, 22);
		getContentPane().add(textSupplier);
		
		textSPrice = new JTextField();
		textSPrice.setColumns(10);
		textSPrice.setBounds(12, 75, 116, 22);
		getContentPane().add(textSPrice);
		
		textMinstock = new JTextField();
		textMinstock.setColumns(10);
		textMinstock.setBounds(12, 123, 116, 22);
		getContentPane().add(textMinstock);
		
		textPname = new JTextField();
		textPname.setColumns(10);
		textPname.setBounds(140, 29, 116, 22);
		getContentPane().add(textPname);
		
		textRPrice = new JTextField();
		textRPrice.setColumns(10);
		textRPrice.setBounds(140, 75, 116, 22);
		getContentPane().add(textRPrice);
		
		textCurStock = new JTextField();
		textCurStock.setColumns(10);
		textCurStock.setBounds(140, 123, 116, 22);
		getContentPane().add(textCurStock);
		
		textPPrice = new JTextField();
		textPPrice.setColumns(10);
		textPPrice.setBounds(278, 29, 116, 22);
		getContentPane().add(textPPrice);
		
		textCountry = new JTextField();
		textCountry.setColumns(10);
		textCountry.setBounds(278, 75, 116, 22);
		getContentPane().add(textCountry);
		
		JLabel lblSupplier = new JLabel("Supplier");
		lblSupplier.setBounds(12, 13, 56, 16);
		getContentPane().add(lblSupplier);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(162, -3, 56, 16);
		getContentPane().add(label_1);
		
		lblName = new JLabel("Product name");
		lblName.setBounds(140, 13, 95, 16);
		getContentPane().add(lblName);
		
		lblPurchasePrice = new JLabel("Purchase price");
		lblPurchasePrice.setBounds(278, 13, 97, 16);
		getContentPane().add(lblPurchasePrice);
		
		lblDfafa = new JLabel("Sales price");
		lblDfafa.setBounds(12, 57, 97, 16);
		getContentPane().add(lblDfafa);
		
		lblRentPrice = new JLabel("Rent price");
		lblRentPrice.setBounds(140, 57, 95, 16);
		getContentPane().add(lblRentPrice);
		
		lblCountryOfOrigin = new JLabel("Country of Origin");
		lblCountryOfOrigin.setBounds(278, 57, 116, 16);
		getContentPane().add(lblCountryOfOrigin);
		
		lblMinimumStock = new JLabel("Minimum stock");
		lblMinimumStock.setBounds(12, 105, 97, 16);
		getContentPane().add(lblMinimumStock);
		
		lblCurrentStock = new JLabel("Current stock");
		lblCurrentStock.setBounds(140, 105, 95, 16);
		getContentPane().add(lblCurrentStock);
		
		textSize = new JTextField();
		textSize.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(textType.getText().length() > 0 || textFabric.getText().length() > 0 || 
						textCali.getText().length() > 0 || textDesc.getText().length() > 0)
				{
					textSize.setText("");
				}
					
			}
		});
		textSize.setColumns(10);
		textSize.setBounds(12, 192, 116, 22);
		getContentPane().add(textSize);
		
		textColour = new JTextField();
		textColour.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(textFabric.getText().length() > 0 || textType.getText().length() > 0 || 
						textCali.getText().length() > 0 || textDesc.getText().length() > 0)
				{
					textColour.setText("");
				}
			}
		});
		textColour.setColumns(10);
		textColour.setBounds(12, 244, 116, 22);
		getContentPane().add(textColour);
		
		textType = new JTextField();
		textType.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(textFabric.getText().length() > 0 || textColour.getText().length() > 0 || 
						textCali.getText().length() > 0 || textSize.getText().length() > 0)
				{
					textType.setText("");
				}
			}
		});
		textType.setColumns(10);
		textType.setBounds(140, 192, 116, 22);
		getContentPane().add(textType);
		
		textDesc = new JTextField();
		textDesc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(textFabric.getText().length() > 0 || textColour.getText().length() > 0 || 
						textCali.getText().length() > 0 || textSize.getText().length() > 0)
				{
					textDesc.setText("");
				}
			}
		});
		textDesc.setColumns(10);
		textDesc.setBounds(140, 244, 116, 22);
		getContentPane().add(textDesc);
		
		textFabric = new JTextField();
		textFabric.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(textDesc.getText().length() > 0 || textColour.getText().length() > 0 || 
						textType.getText().length() > 0 || textSize.getText().length() > 0)
				{
					textFabric.setText("");
				}
			}
		});
		textFabric.setColumns(10);
		textFabric.setBounds(278, 192, 116, 22);
		getContentPane().add(textFabric);
		
		textCali = new JTextField();
		textCali.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(textDesc.getText().length() > 0 || textColour.getText().length() > 0 || 
						textType.getText().length() > 0 || textSize.getText().length() > 0)
				{
					textCali.setText("");
				}
				
			}
		});
		textCali.setColumns(10);
		textCali.setBounds(278, 244, 116, 22);
		getContentPane().add(textCali);
		
		lblSize = new JLabel("Size");
		lblSize.setBounds(12, 174, 56, 16);
		getContentPane().add(lblSize);
		
		lblColour = new JLabel("Colour");
		lblColour.setBounds(12, 227, 56, 16);
		getContentPane().add(lblColour);
		
		lblDescription = new JLabel("Description");
		lblDescription.setBounds(140, 227, 78, 16);
		getContentPane().add(lblDescription);
		
		lblType = new JLabel("Type");
		lblType.setBounds(140, 174, 56, 16);
		getContentPane().add(lblType);
		
		lblFabric = new JLabel("Fabric");
		lblFabric.setBounds(278, 174, 56, 16);
		getContentPane().add(lblFabric);
		
		lblCalibre = new JLabel("Calibre");
		lblCalibre.setBounds(278, 227, 56, 16);
		getContentPane().add(lblCalibre);
		
		lblCloth = new JLabel("Clothing");
		lblCloth.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCloth.setBounds(12, 158, 84, 16);
		getContentPane().add(lblCloth);
		
		lblEquipment = new JLabel("Equipment");
		lblEquipment.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEquipment.setBounds(140, 158, 95, 16);
		getContentPane().add(lblEquipment);
		
		lblGunReplica = new JLabel("Gun Replica");
		lblGunReplica.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGunReplica.setBounds(278, 158, 97, 16);
		getContentPane().add(lblGunReplica);
	}
}
