import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

 
public class Traineereg {
 
	public static void main(String[] argv) {
		Scanner in = new Scanner(System.in);
		long id;
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
        	System.out.println("****TRAINEE REGISTRATION****");
			System.out.println("Enter FullName");
	    	String name = in.nextLine();
	    	System.out.println("Enter EmailId");
	    	String email = in.nextLine();
	    	System.out.println("Enter Password");
	    	String password = in.nextLine();
	    	System.out.println("Enter State");
	    	String state = in.nextLine();
	    	System.out.println("Are you an Employee? (Y/N)");
	    	String e=in.next();
	    	char Employee=e.charAt(0);
	    	System.out.println("Are you an Student? (Y/N)");
	    	String s=in.next();
	    	char Student=s.charAt(0);
             Date d=new Date();
             java.sql.Date sqlDate = new java.sql.Date(d.getTime());
	    	
	    	
	    	
	    	
	        String sql="insert into S22_S003_5_Trainee(FullName,Email,Password,State,Employee,Student,RegistrationDate) values (?,?,?,?,?,?,?)";
 //below include your login and your password
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@acaddbprod-2.uta.edu:1523/pcse1p.data.uta.edu", "nxs8007", "April12th2022");
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); 

                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, password);
                pstmt.setString(4, state);
                pstmt.setString(5,String.valueOf(Employee));
                pstmt.setString(6,String.valueOf(Student));
                pstmt.setDate(7, sqlDate);

                int affectedRows = pstmt.executeUpdate();
                // check the affected rows 
                if (affectedRows > 0) {
                	
                    try  {
                    	ResultSet rs = pstmt.getGeneratedKeys();
                        if (rs.next()) {
                           System.out.println("Registered successfully!!!");
                           Statement stmt = connection.createStatement();
                           rs=stmt.executeQuery("Select * from S22_S003_5_Trainee order by TraineeID desc Fetch first 1 row only");
                           while(rs.next()) {
                        	   System.out.println("Your TraineeID is: "+rs.getInt("TraineeID"));
                           }
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
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
