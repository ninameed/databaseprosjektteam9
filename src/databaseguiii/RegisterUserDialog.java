package databaseguiii;

import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import databasePackage.*;

class RegisterUserDialog extends JFrame {
	private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
	private Admin user = null; 
  
	public RegisterUserDialog(User user) throws Exception { 
		this.user = (Admin)user;
		UserDialog dialog = new UserDialog(this);
		dialog.setVisible(true);
		dialog.setLocation(350, 350);
		setTitle("Registrer user");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300); 
	 } 

	private class UserDialog extends MyDialog{

		private TextEditor editor = new TextEditor();
		private JTextField userIDfield = new JTextField(10);
		private final String userRoles[] = {"Admin", "Cook", "Driver", "Sales", "Storage"}; 
		private JComboBox<String> userList = new JComboBox<String>(userRoles);	
		private JTextField usernameField = new JTextField(20);		
		private JTextField passwordField = new JTextField(20);
		private String pword;
		private String name;
		private String userID;
		private int userType;
		
		public UserDialog(JFrame parent){
			super(parent, "New user");
			add(new JPanel(), BorderLayout.NORTH);
			add(new UserDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}
				
		private class UserDatapanel extends JPanel{
			public UserDatapanel(){
				setLayout(new GridLayout(4,2));
				add(new JLabel("Username: ", JLabel.RIGHT));
				add(userIDfield);
				
				add(new JLabel("User type: ", JLabel.RIGHT));
				add(userList);
				
				add(new JLabel("Name: ", JLabel.RIGHT));
				add(usernameField);				

				add(new JLabel("Password: ", JLabel.RIGHT));
				add(passwordField);
			}
		}
		
		public boolean okData(){
			userID = userIDfield.getText();	
			userType = userList.getSelectedIndex();				
			pword = passwordField.getText();
			name = usernameField.getText();
			
			try {
				user.registerUser(userID, userType, name, pword, database);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return true;		
		}
	}
}