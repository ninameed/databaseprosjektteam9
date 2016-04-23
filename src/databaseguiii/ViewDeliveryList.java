package databaseguiii;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.*;
import databasePackage.Database;

public class ViewDeliveryList extends JFrame {
	  private DefaultListModel<String> listcontent = new DefaultListModel<String>(); 
	  private JList<String> list = new JList<String>(listcontent);
	  private Driver driver = null;
  	  String[][] mealList = null;
	  
	  public ViewDeliveryList(Driver driver) {
		  this.driver = driver;
		  setTitle("Delivery list");
		  
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  add(new TextPanel(), BorderLayout.NORTH);
	   	  add(new ListPanel(), BorderLayout.CENTER);
	   	  JButton markButton = new JButton("Mark as delivered");
	   	  markButton.addActionListener(new markButtonListener());
	   	  add(markButton, BorderLayout.SOUTH);
		  pack();
		  
	  }

	  private class TextPanel extends JPanel {
	    public TextPanel() {
	      setLayout(new GridLayout(4, 1, 2, 2));
	      add(new JLabel(""));  
	      add(new JLabel("List of deliveries"));
	      add(new JLabel("Mark and click button to mark as delivered"));
	      add(new JLabel(""));  
	    }
	  }

	  private class ListPanel extends JPanel {
	    public ListPanel() {
	    	setLayout(new BorderLayout());
	    	
	    	try {
				mealList = driver.generateDeliveryPlan();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	if(mealList.length == 0){
	    		listcontent.addElement("No deliveries left for today");
	    	}else{
	    	    for(int i=0; i<mealList.length;i++){
	    	    	listcontent.addElement(mealList[i][0] + ", Quantity: " + mealList[i][1]);
	    	    	listcontent.addElement(mealList[i][2]);
	    	    	listcontent.addElement(mealList[i][3] + " " + mealList[i][4]);
	    	    	listcontent.addElement(" ");
	    	    }	
	    	}
    	    
	    	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    	JScrollPane mealScroller = new JScrollPane(list); 
	    	add(mealScroller, BorderLayout.CENTER);
	    }
	  }
	  
	  private class markButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			int selected = list.getSelectedIndex();
			int baseIndex = (selected/4)*4; 
			for(int i=0;i<4;i++){
				if(baseIndex > -1 && !listcontent.isEmpty()) listcontent.remove(baseIndex);
			}
			int index = selected/4;
			if(index < mealList.length){
				int orderID = Integer.parseInt(mealList[index][5]);
				int mealID = Integer.parseInt(mealList[index][6]);
			    java.util.Date utilDate = new java.util.Date();
			    java.sql.Date date = new java.sql.Date(utilDate.getTime());
			    
				try {
					driver.markDelivered(orderID, mealID, date);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	  }
	  
	 	public static void main(String[] args){
	 		String username = "espenme";
	 		String passingword = "16Sossosem06";
	 		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
	 		Database database = new Database("com.mysql.jdbc.Driver", databasename);
			ViewDeliveryList del = new ViewDeliveryList(new Driver("","", database));
			del.setVisible(true);
	 	}
}
