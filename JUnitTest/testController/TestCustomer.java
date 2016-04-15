package testController;

import databasePackage.Database;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.Customer;
import databasePackage.Database;
import databasePackage.QMCustomer;

public class TestCustomer {
	private static Database database;
	private static Customer customer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		customer = new Customer(1, "Jens", "Svendsen", "12345678", "jens@svendsen.com", "Veibakken 12", 7014, 1, "None", true);
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShouldUpdateCustomer() throws Exception{
		//boolean updateCustomer(Database database)
		boolean res = customer.updateCustomer(database);
		boolean expRes = true;
		
		assertEquals(res, expRes);
	
	}

}
