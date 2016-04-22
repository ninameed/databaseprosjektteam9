package databaseguiii;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;
import databasePackage.Database;

class IngredientsInMealGui extends JFrame {
	
	private Meal meal = null; 
	private Cook cook = null;
  
	public IngredientsInMealGui(Cook cook, Meal meal){ 
		this.cook=cook;
		this.meal = meal;
		IngredientsInMealDialog dialog = new IngredientsInMealDialog(this);
		dialog.setVisible(true);
		dialog.setLocation(350, 350);
		setTitle("Registrer user");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300); 
	 } 

	private class IngredientsInMealDialog extends MyDialog{

		private TextEditor editor = new TextEditor();
		
		private ArrayList<Meal> mealList = new ArrayList<Meal>();
 		private JComboBox mealIdSelected;
 		
 		private int my_meal;
 		
		
		public IngredientsInMealDialog(JFrame parent){
			super(parent, "Choose the meal");
			add(new JPanel(), BorderLayout.NORTH);
			add(new UserDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}
				
		private class UserDatapanel extends JPanel{
			public UserDatapanel(){
				setLayout(new GridLayout(1,2));
				
				try {
	 				mealList = cook.viewAvailableMeals();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ArrayList<String> my_list = new ArrayList<>();
				for(Meal m: mealList){
					my_list.add(m.getMealName());
				}
				mealIdSelected = new JComboBox<>(my_list.toArray());
 				add(new JLabel("Meal Id: ", JLabel.RIGHT));
 				add(mealIdSelected);
					
			}
		}
		
		public boolean okData(){
			
			my_meal = mealIdSelected.getSelectedIndex();
				
			String s ="";
			ArrayList <Ingredient> ingr_in_meal = meal.getIngredients();
			for(int i=0; i<ingr_in_meal.size(); i++){
				s +=  ingr_in_meal.get(i).toString() + " \n";
			}
			JOptionPane.showMessageDialog(null, s, "Inredients of this meal: ", JOptionPane.INFORMATION_MESSAGE );
			return true;
		}
	}
	public static void main(String[] args){
		String username = "espenme";
		String passingword = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
		Database database = new Database("com.mysql.jdbc.Driver", databasename);
		new IngredientsInMealGui(new Cook("","", database), new Meal(1,"","", true,1));
	}
}  





