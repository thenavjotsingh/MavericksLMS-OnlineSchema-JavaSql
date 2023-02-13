import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.util.stream.*;
import java.util.function.*;
 
public class CourseEnrollmentForm {
 
	public static void main(String[] argv) {
 
		System.out.println("-------- Oracle JDBC Connection Testing ------");
 Scanner sc=new Scanner(System.in);
	ArrayList<String> listString = new ArrayList<String>();
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
            System.out.println("***COURSE DETAILS***");
            System.out.println("--------------------------");
		} else {
			System.out.println("Failed to make connection!");
		}
        try {
            Statement stmt = connection.createStatement();
           
	       ResultSet rs = stmt.executeQuery("select * "+"from S22_S003_5_Teaches "+"natural join S22_S003_5_Course "+"natural join "+"S22_S003_5_Trainer");
	       
            System.out.println("CourseID   SectionID   CourseName        Trainer Name     CourseFee");
	       while (rs.next())
	         System.out.println("   "+rs.getString("CourseID")+"|    "+rs.getString("SectionID")+"|   "+rs.getString("CourseName")+"|          "+rs.getString("Name")+"|             "+rs.getString("CourseFee"));
	     
	       System.out.println("Enter TraineeID");
	       int id=sc.nextInt();
	       
	        	System.out.println("Enter CourseID  you want to enroll");
	       int courseid=sc.nextInt();
	       ResultSet trainerResult = stmt.executeQuery("select CourseID from S22_S003_5_Course");
		   
	       List<Integer> list = new ArrayList<>(200);
	       
	       while (trainerResult.next()) {
			   try {
			     FileWriter myWriter = new FileWriter("filename.txt",true);
			     myWriter.flush(); 
				 BufferedWriter bw = new BufferedWriter(myWriter);
	      bw.write(trainerResult.getString("CourseID"));
		  bw.newLine();
	    bw.close();
		Scanner s = new Scanner(new File("filename.txt"));
		while (s.hasNext()){
			
		    listString.add(s.next());
		}
		s.close();
		      
				   }
				   catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	       }
	      
	      boolean found=false;  
	       for (int i = 0; i < listString.size(); i++){
	    	  
	    	  
				int element = Integer.parseInt(listString.get(i));
				if(courseid==element){
	            
					System.out.println("Enter SectionID");
	       int section=sc.nextInt();
	       Date d=new Date();
           java.sql.Date sqlDate = new java.sql.Date(d.getTime());
	       
	       String sql="insert into S22_S003_5_Enroll(TraineeID,CourseID,SectionID,DateOfEnrollment) values (?,?,?,?)";
	       //below include your login and your password
	       Connection con = DriverManager.getConnection("jdbc:oracle:thin:@acaddbprod-2.uta.edu:1523/pcse1p.data.uta.edu", "nxs8007", "April12th2022");
	                  PreparedStatement pstmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); 

	                      pstmt.setInt(1, id);
	                      pstmt.setInt(2, courseid);
	                      pstmt.setInt(3, section);
	                      pstmt.setDate(4, sqlDate);
	                     
	           	       
	                      int affectedRows = pstmt.executeUpdate();
	                      // check the affected rows 
	                      if (affectedRows > 0) {
	                      	
	                          try {
	                        	  
	                        	  rs = pstmt.getGeneratedKeys();
	                              if (rs.next()) {
	                                 System.out.println("Enrolled successfully !!!");
	                               
	                              }
	                              
	                          } catch (SQLException ex) {
	                              System.out.println(ex.getMessage());
	                          }
	                      
	                      }
	                      else
	                      {
	                    	  System.out.println("No rows inserted!!!");
	                      }
	                      found=true;
	                      break;
                             }
			
			
	       }
	     
	       if(found==false)
	       {
	    	   System.out.println("Invalid Courseid");
	       }
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
   
