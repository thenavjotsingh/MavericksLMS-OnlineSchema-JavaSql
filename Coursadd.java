import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

 
public class Coursadd {
 
	public static void main(String[] argv) {
		Scanner sc = new Scanner(System.in);
	
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
   int emp=0,stu=0;
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
        	System.out.println("Choose Employee or Student to know their total count");
        String option=sc.next();
        
        
        	String s1="Employee";
        
        	if("Employee".equals(option))
        	{
        		ResultSet rs=stmt.executeQuery("select count(Employee)  from S22_S003_5_Trainee where Employee='Y' group by Employee");
        	
       	    	while(rs.next())
       	    	emp=rs.getInt(1);
       	     System.out.println("No. of Employees:"+emp);
       	    	rs=stmt.executeQuery("select count(Employee)  from S22_S003_5_Trainee where Employee='N' group by Employee");
       	    	while(rs.next())
           	    	stu=rs.getInt(1);
              	  System.out.println("No. of Students:"+stu);
       	    	if(emp>stu)
       	    	{
       	    		System.out.println("Add Employee related courses");
       	    	}
        	
        	
        	else
        	{
        		System.out.println("Not a employee");
        	}
        }
        	else if("Student".equals(option)){
        		ResultSet rs=stmt.executeQuery("select count(Student)  from S22_S003_5_Trainee where Student='Y' group by Student");
            	
       	    	while(rs.next())
       	    	stu=rs.getInt(1);
       	     System.out.println("No. of Students:"+stu);
       	    	rs=stmt.executeQuery("select count(Student)  from S22_S003_5_Trainee where Student='N' group by Student");
       	    	while(rs.next())
           	    	emp=rs.getInt(1);
              	  System.out.println("No. of Employees:"+emp);
       	    	if(stu>emp)
       	    	{
       	    		System.out.println("Add Student related courses");
       	    	}
       	    	else
       	    	{
       	    		System.out.println("Add Employee related courses");
       	    	}
       	    	
        	}
        	else
   	    	{
   	    		System.out.println("Invalid option");
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
