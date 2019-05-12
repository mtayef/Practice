package createStatements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * A Java MySQL DELETE example.
 * Demonstrates the use of a SQL DELETE statement against a
 * MySQL database, called from a Java program, using a
 * Java PreparedStatement.
 * 
 * Created by Alvin Alexander, http://devdaily.com
 */
public class Deleting
{

  public static void main(String[] args)
  {
    try
    {
      // create the mysql database connection
      String myDriver = "com.mysql.cj.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/Business";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root1", "root1");
      
      // create the mysql delete statement.
      // i'm deleting the row where the id is "3", which corresponds to my
      // "Barney Rubble" record.
      String query = "ALTER TABLE CustomerInfo  \r\n" + 
      		"DROP COLUMN Name;";
      PreparedStatement preparedStmt = conn.prepareStatement(query);

      // execute the preparedstatement
      preparedStmt.execute();
     
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
  }
}

