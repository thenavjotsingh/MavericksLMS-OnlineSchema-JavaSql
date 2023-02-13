//or you can keep the following instead
//import java.sql.DriverManager;
//import java.sql.Connection;
//import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Feedback {
 
	public static void main(String[] argv) {
 
		System.out.println("-------- Oracle JDBC Connection Testing ------");
 
		try {
 
			Class.forName("oracle.jdbc.driver.OracleDriver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
 
		}
 
		System.out.println("Oracle JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
 
 //below include your login and your password
            connection = DriverManager.getConnection("jdbc:oracle:thin:@acaddbprod-2.uta.edu:1523/pcse1p.data.uta.edu", "nxs8007", "April12th2022");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
 
		}
 
		if (connection != null) {
			System.out.println("You made it, take control of your database now!\n");
            System.out.println("***FEEDBACK OF TRAINEE***");
		} else {
			System.out.println("Failed to make connection!");
		}
        try {
            Statement stmt = connection.createStatement();
           
	       ResultSet rs = stmt.executeQuery("select * "+"from (S22_S003_5_TraineesFeedback "+"inner join S22_S003_5_Trainee on (S22_S003_5_Trainee.TraineeID=S22_S003_5_TraineesFeedback.TraineeID))"+" inner join S22_S003_5_Feedback on (S22_S003_5_Feedback.SNo=S22_S003_5_TraineesFeedback.FeedbackNo)");
	       

	       while (rs.next())
	         System.out.println("   "+rs.getString("FullName")+"       "+rs.getString("FeedbackContent"));
	       
	       rs.close();
	       stmt.close();
            connection.close();
        }
        catch (SQLException e) {
 
			System.out.println("error in accessing the relation");
			e.printStackTrace();
			return;
 
		}    
	}
 
}
