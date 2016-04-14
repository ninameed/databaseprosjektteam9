package controller;

import java.util.ArrayList;
import databasePackage.*;

// import java.sql.Date;

public class Order {
	private int orderID;
	private String orderDate; // java.util.Date / java.sql.Date?
	private String deliveryDate;
	private int customerID;
	private String info;
	private String userID;
	private ArrayList<Meal> meals;
	private SubPlan subPlan = null;
	
	public Order(int orderID, String orderDate, String deliveryDate, int customerID, String info, String userID){
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.customerID = customerID;
		this.info = info;
		this.userID = userID;
	}

	public boolean markMealAsReady(int index, Database database) throws Exception{
		return QueryMethods.markMealOrderAsReadyForDelivery(orderID, meals.get(index).getMealID(), deliveryDate, database);
	}
	
	public boolean markMealAsDelivered(int index, Database database) throws Exception{
		return QueryMethods.markMealOrderAsDelivered(orderID, meals.get(index).getMealID(), deliveryDate, database);
	}	
	
	public int getOrderID(){
		return orderID;
	}
	
	public String getOrderDate(){
		return orderDate;
	}
	
	public String getDeliveryDate(){
		return deliveryDate;
	}
	
	public int getCustomerID(){
		return customerID;
	}
	
	public String getInfo(){
		return info;
	}
	
	public String getUserID(){
		return userID;
	}
	
	public void addMeal(Meal meal){
		meals.add(meal);
	}
	
	public Meal getMeal(int index){
		if(meals.size() > 0) return meals.get(index);
		else return null;
	}
	
	public Meal[] getMeals(){
		Meal[] temp = new Meal[meals.size()];
		return temp;
	}
	
	public boolean updateOrder(Database database) throws Exception{
		return QueryMethods.updateOrder(orderID, orderDate, customerID, info, userID, database);
	}
	
	
	
	//Fetches meals from database
	public void fetchMeals(Database database) throws Exception{

		String[][] mealT = QueryMethods.viewMealsInOrder(orderID, database);
		TextEditor t = new TextEditor();
		boolean check= false;
		
		for(int i=0;i<mealT.length;i++){			
			for(int j=0;j<meals.size();j++){		//Checks if meal is in arrayList already
				if (meals.get(j).getMealID()==t.stringToInt(mealT[i][0])){
					check=true;
				}
			}
			if (!check){							//Adds meal to arrayList if check is false
				meals.add(new Meal(t.stringToInt(mealT[i][0]),mealT[i][1],mealT[i][2],true, t.stringToInt(mealT[i][4])));
			}
			check=false;
		}
	}
	
	
	public String toString(){
		String res = "";
		res += "OrderID: " + orderID + ". Orderdate: " + orderDate + ". Delivery date: " + deliveryDate
				+ ". Info: " + info + "\n";
		res += "Meals: \n";
		for(Meal m:meals){
			res += "   " + m.toString() + "\n";
		}
		return res;
	}
	
	static public void main(String[] arg){
		
	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		System.out.println(sqlDate);
		
	}
	
}
