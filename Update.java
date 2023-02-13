//or you can keep the following instead
//import java.sql.DriverManager;
//import java.sql.Connection;
//import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.util.stream.*;
import java.util.function.*;
 
public class Update {
 
	public static void main(String[] argv) {
 
		System.out.println("-------- Oracle JDBC Connection Testing ------");
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
            System.out.println("***UPDATE TRAINER DETAILS***");
		} else {
			System.out.println("Failed to make connection!");
		}
        try {
            Statement stmt = connection.createStatement();
			Scanner userInput = new Scanner(System.in);
			
           System.out.println("Enter TrainerID");
		   int userInputTrainerId = userInput.nextInt();
		   
		   ResultSet trainerResult = stmt.executeQuery("select TrainerID from S22_S003_5_Trainer");
		   List<Integer> list = new ArrayList<>(200);
		   int trainerId;
		   while (trainerResult.next()) {
		   try {
		     FileWriter myWriter = new FileWriter("filename.txt",true);
			 BufferedWriter bw = new BufferedWriter(myWriter);
      bw.write(trainerResult.getString("TrainerID"));
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
		    for (int i = 0; i < listString.size(); i++){
				int element = Integer.parseInt(listString.get(i));
				if(userInputTrainerId==element) {
					System.out.println("Sucess");
					System.out.println("What do you want to update");
					System.out.println("1. Phone Number");
					System.out.println("2. Password");
					int ip = userInput.nextInt();
					if(ip==1)
					{
						//update phoneNumber
						System.out.println("Enter the new phone number");
						long newPhoneNumber = userInput.nextLong();
						int numDigitsofPhoneNo = String.valueOf(newPhoneNumber).length();
						if(numDigitsofPhoneNo==10){
							
						
						ResultSet updateSet = stmt.executeQuery("UPDATE S22_S003_5_Trainer SET PHONENUMBER="+newPhoneNumber + "where TrainerID=" + userInputTrainerId);
						
						ResultSet updatedSet = stmt.executeQuery("SELECT * FROM S22_S003_5_Trainer where TrainerID=" + userInputTrainerId);
						System.out.println("Updating Record...");
						System.out.println(" ");
						System.out.println(" ");
						System.out.println(" ");
						System.out.println("Record Updated!");
						
						System.out.println();
						while (updatedSet.next())
						//System.out.println(updatedSet);
					System.out.println("   "+updatedSet.getString("TrainerID")+
					" "+updatedSet.getString("Name")+
					"   "+updatedSet.getString("Password")+
					"   "+updatedSet.getString("Email")+
					"   "+updatedSet.getString("PhoneNumber")+
					"   "+updatedSet.getString("AccountNumber"));
					break;
						
					}
					else {
						System.out.println("Please enter valid phone number");
					}
					}
					else if (ip==2)
					{
						//update password
						System.out.println("Enter the new password");
						String newPassword = userInput.next();
					
						
						 int newPasswordLength = String.valueOf(newPassword).length();
						if(newPasswordLength<=15){
							
						
						ResultSet updateSet = stmt.executeQuery("UPDATE S22_S003_5_Trainer SET PASSWORD='"+newPassword + 
						"' where TrainerID=" + userInputTrainerId);
						
						ResultSet updatedSet = stmt.executeQuery("SELECT * FROM S22_S003_5_Trainer where TrainerID=" + userInputTrainerId);
						System.out.println("Updating Record...");
						System.out.println(" ");
						System.out.println(" ");
						System.out.println(" ");
						System.out.println("Record Updated!");
						
						System.out.println();
						while (updatedSet.next())
						//System.out.println(updatedSet);
					System.out.println("   "+updatedSet.getString("TrainerID")+
					" "+updatedSet.getString("Name")+
					"   "+updatedSet.getString("Password")+
					"   "+updatedSet.getString("Email")+
					"   "+updatedSet.getString("PhoneNumber")+
					"   "+updatedSet.getString("AccountNumber"));
					break;
						
					}
					else {
						System.out.println("Password should be below 15 characters!!!");
						break;
					}
					}
					else {
						System.out.println("wrong choice");
						break;
					}
					
				}
				
				else
				{
					System.out.println("Invalid TrainerID");
					break;
				}
				//break;
			}
	       
	       trainerResult.close();
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
