package jSonJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

//This class is used to connect with database.

public class DbConnection {
	
	protected static Scanner scan = new Scanner(System.in);
	public static Connection con = null;
	public static void connection() {
		try {
			System.out.print("MySQL DB: ");
			String DB = scan.nextLine();
			String url = "jdbc:mysql://localhost:3306/"+DB;
			System.out.print("MySQL Username: ");
			String username = scan.nextLine().trim();
			System.out.print("MySQL Password: ");
			String password = scan.nextLine().trim();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// connection being assigned to con object using DriverManager class.
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.err.println("ERROR: System error "+e.getMessage());
		}
	}
}
