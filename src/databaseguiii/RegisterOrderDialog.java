package databaseguiii;

import controller.*;
import databasePackage.Database;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

//Metoden registerNewOrder() ligger i Sales.java

class RegisterOrderDialog extends JFrame {
	private Admin admin = null; 

	public RegisterOrderDialog(Admin admin) {
		this.admin = admin;
		DialogWindow dialog = new DialogWindow(this);
		dialog.setVisible(true);
		setTitle("Register new order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300); 
		dialog.setLocation(350, 350);  
	}

	//int customerID, String deliveryDate, String info, String userID, Database database
	private class DialogWindow extends MyDialog{
		private TextEditor editor = new TextEditor();
		private ArrayList<Customer> customerList = new ArrayList<>();
		private JComboBox customerSelect;
		private SpinnerDateModel dateSelectModel = new SpinnerDateModel();
		private JSpinner dateSelect = new JSpinner(dateSelectModel);
		private JTextField delivery_dateField = new JTextField(10);
		private JTextField infoField = new JTextField(10);
		private int customerID;
		private String deliveryDateStr;
		private Date deliveryDate;
		private String info;
		
		public DialogWindow(JFrame parent){
			super(parent, "New order");
			add(new JPanel(), BorderLayout.NORTH);
			add(new OrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}
	
		private class OrderDatapanel extends JPanel{
			public OrderDatapanel(){
				setLayout(new GridLayout(4,2));
			
				try {
					customerList = user.viewFoodOrders();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
				}
				customerSelect = new JComboBox<>(nameList.toArray());
				
				add(new JLabel("Customer: ", JLabel.RIGHT));
				add(customerSelect);
			
				add(new JLabel("Delivery date: ", JLabel.RIGHT));
				add(dateSelect);
			
				add(new JLabel("Information about the order: ", JLabel.RIGHT));
				add(infoField);
			
			}
		}

		public boolean okData(){

			int customerIndex = customerSelect.getSelectedIndex();
			customerID = customerList.get(customerIndex).getCustomerID();
			
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			deliveryDateStr = s.format(dateSelect.getValue());
			info = infoField.getText();
		
			ArrayList<Meal> m = null;
	    	  try {
	    		  m= admin.viewAvailableMeals();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	  String[] s = new String[m.size()];
		    	 for( int i = 0; i < m.size(); i++ ){
		    		 Meal meal = m.get(i);
		    		 s[i] = meal.getMealName()+ "\n";
		    	 }
		    	 
		    	 JScrollPane scrollpane = new JScrollPane(); 
		         JList list = new JList(s);
		         scrollpane = new JScrollPane(list);
		         JPanel panel = new JPanel(); 
		         panel.add(scrollpane);
		         scrollpane.getViewport().add(list);		    	 
		    	 JOptionPane.showMessageDialog(null, scrollpane, "All meals: ", JOptionPane.INFORMATION_MESSAGE );
		}	
	}
}