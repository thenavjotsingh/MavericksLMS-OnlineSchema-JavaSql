import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

 
public class Sales {
 
	public static void main(String[] argv) {
		Scanner in = new Scanner(System.in);
		int paid=0,revenue=0;
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
            
		} else {
			System.out.println("Failed to make connection!");
		}
        try {
        	Statement stmt = connection.createStatement();
        	System.out.println("Do you want to know about 1.Total salary paid or 2.Total revenue");
        	int option=in.nextInt();
        	
        		ResultSet rs=stmt.executeQuery("select sum(amount) from S22_S003_5_TrainersSalary t join S22_S003_5_Salary s on t.PaymentID=s.PaymentID");
        	    while(rs.next())
        		paid=rs.getInt(1);
        	    rs=stmt.executeQuery("select sum(CourseFee) from S22_S003_5_Enroll  natural join S22_S003_5_Course");
        	    while(rs.next())
        	    revenue=rs.getInt(1);
        	    if(option==1) {
        	    System.out.println("Total salary paid to Trainers:"+paid);
        	    }
        	   
            	else if(option==2)
            	{
            		System.out.println("Total Revenue:"+revenue);
            	}
            	else
            	{
            		System.out.println("Invalid option");
            		
            	}
        	    if(option==1||option==2)
        	    {	
        if(paid>revenue)
        	{
        		
            		System.out.println("Maverick LMS is Loss");
            	
        	    
        	}
        else
        {
        	System.out.println("Maverick LMS is in Profit!!!Hurrrayyyyy!!!");
        }
        	
        	
        	    }
        	
        	
                
            connection.close();
        }
        catch (SQLException e) {
 
			System.out.println("error in accessing the relation");
			e.printStackTrace();
			return;
 
		}    
	}
 
}
