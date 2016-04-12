package testPackageDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import databasePackage.Database;
import databasePackage.QueryMethods;

public class TestMeal {
	public Database database;
	
	@Before
	public void beforeTest(){
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@Test
	public void mealTest() throws Exception{

		// view all entries
		String[][] meals = QueryMethods.viewMeals(database);
		// select last entry
		String[] meal = meals[meals.length-1];
				
		// attempt to register new info about existing entry
		boolean exp = QueryMethods.updateMeal(Integer.parseInt(meal[0]), meal[1], meal[2], false, Integer.parseInt(meal[4]), 1, 1, database);
		assertEquals(true, exp);		
		
		// attempt to remove entry		
		exp = QueryMethods.removeMeal(Integer.parseInt(meal[0]), database);
		assertEquals(true, exp);

		// attempt to reregister removed entry	
		int iexp = QueryMethods.registerMeal(meal[1], meal[2], false, Integer.parseInt(meal[4]), 1, 1, database);
		assertNotEquals(-1, iexp);		
	}
}