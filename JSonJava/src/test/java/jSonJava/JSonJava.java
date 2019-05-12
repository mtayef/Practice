package jSonJava;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSonJava extends DbConnection {
	public static void main(String args[]) {
		try {
			// Customer Details object in ArrayList
			ArrayList<CustomerDetails> a = new ArrayList<CustomerDetails>();

			connection();

			// object of statement class will help us to execute queries
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(
					"select * from customerinfo where location = 'Asia' and"
					+ " purchasedDate = curdate();");

			while (rs.next()) {
				CustomerDetails c = new CustomerDetails();
				c.setCourseName(rs.getString(1));
				c.setPurchasedDate(rs.getString(2));
				c.setAmount(rs.getInt(3));
				c.setLocation(rs.getString(4));

				/*
				 * System.out.println( c.getCourseName() + " " + c.getPurchasedDate() + " $" +
				 * c.getAmount() + " " + c.getLocation());
				 */

				a.add(c);
			}
			
			 ObjectMapper ob = new ObjectMapper(); ob.writeValue(new
			 File("./JsonOutput/customerInfo.json"), a);

			con.close();
			scan.close();

		} catch (Exception e) {
			System.err.println("ERROR: Unable to process " + e.getMessage());
		}
	}
}
