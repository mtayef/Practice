package createStatements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;
//import java.util.ArrayList;

public class DBCreateStatement {

	public Statement st;
	public ResultSet rs;
	public ResultSetMetaData md;
	public int col;
	public String col_name = null;
	public String sql = null;
	
	Connection con = null;

	public String column;

	private static Scanner scan = new Scanner(System.in);

	public static void main(String args[]) {
		// System.out.println("Getting Column Names Example!");

		DBCreateStatement db = new DBCreateStatement();
		db.connection();
	}

	public void connection() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/";

		// ==> MySQL Database name
		System.out.print("MySQL Database: ");
		String db = scan.nextLine();

		// ==> MySQL UserName
		System.out.print("MySQL Username: ");
		String user = scan.nextLine();

		// ==> MySQL Password
		System.out.print("MySQL Password: ");
		String pass = scan.nextLine();

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url + db, user, pass);

			// objects of statement help us create queries.
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM CustomerInfo");
			colName();
			//deleteCol();
			//alterColName();
			colName();

			con.close();
		} catch (Exception e) {
			System.err.println("ERROR: Unable to process " + e.getMessage());
		}

	}

	public void colName() {
		try {
			md = rs.getMetaData();
			col = md.getColumnCount();
			System.out.println("Number of Column: " + col);
			System.out.print("Columns Name:");
			col_name = null;
			for (int i = 1; i <= col; i++) {
				col_name = md.getColumnName(i);
				System.out.print(" " + col_name);
			}
			System.out.println();

		} catch (Exception e) {
			System.err.println("ERROR: Unable to process " + e.getMessage());
		}
	}

	public void alterColName() {
		try {
			System.out.print("Please enter column: ");
			column = scan.nextLine();
			
			for (int i = 0; i < col; i++) {
                sql = "ALTER TABLE CustomerInfo ADD " + column + " VARCHAR(30)";
                st.executeUpdate(sql);
            }

		} catch (Exception e) {
			System.err.println("ERROR: Unable to process " + e.getMessage());
		}
	}

	public void deleteCol() {
		try {
			System.out.print("Please enter column: ");
			column = scan.nextLine();
			sql = "ALTER TABLE CustomerInfo  \r\n" + 
		      		"DROP COLUMN Name;";
			PreparedStatement preparedStmt = con.prepareStatement(sql);

		      // execute the preparedstatement
		      preparedStmt.execute();
			
		} catch (Exception e) {
			System.err.println("ERROR: Unable to delete: "+e.getMessage());
		}
	}

}
