 package databaseguiii;
import controller.*;
import databasePackage.Database;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.JOptionPane.*;


	

class Parentwindow3 extends JFrame {
  private RegisterOrderDialog dialog = new RegisterOrderDialog(this);
  String userID;
  int userType;
  String name;
  String pword;
  private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/ninameed?user=ninameed&password=1Le5YPPr");
  private Sales sales = new Sales("",0, "","", database); 
  
  
 public Parentwindow3() {
	 //Order order = new Order(0,"","", 0, "", ""); 
      if (dialog.showDialog()) {
    	  sales.registerNewOrder(order.getCustomerID(), order.getOrderDate(), order.getDeliveryDate(), order.getInfo(), 
    			  order.getUserID(), order.getMeals(), database);
          
      } else {
        System.out.println("Cancelled");
      }
      System.out.println(order); //bruker toString().
    
    setTitle("Registrer order");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    
   
    setLocation(300, 300); // plasserer foreldrevinduet..
    dialog.setLocation(350, 350);  // plasserer dialogen
  } 
  
/*  private class ButtonListener2 implements ActionListener {
	  public void actionPerformed(ActionEvent action) {
		  
		  String text = "Userlist:\n";

		  String[][] dbData = null;
		  
		  try {
			  dbData = QueryMethods.viewAllUsers(database);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		
		for(int x=0;x<dbData.length; x++){
			for(int y=0;y<dbData[x].length;y++){
				text += dbData[x][y] + " ";
			}
			text += "\n";
		}
		JOptionPane.showMessageDialog(null, text);
	  }
  }	  */  
}

class TestUserDialog {
  static public void main(String[] args) {
	//Parentwindow test = new Parentwindow();
    test.setSize(300, 200);  // for � f� litt st�rrelse p� vinduet
    test.setVisible(true);
  }   
} 