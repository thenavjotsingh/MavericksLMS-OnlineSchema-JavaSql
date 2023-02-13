import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;



public class Discount {







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
Statement stmt = connection.createStatement();
System.out.println("****Most registered month****");





Date d=new Date();



String monthName="";


ResultSet rs=stmt.executeQuery("SELECT to_char(RegistrationDate,'Month') as Most_Registered_month from S22_S003_5_Trainee GROUP BY to_char(RegistrationDate,'Month') HAVING COUNT(to_char(RegistrationDate,'Month'))>=ALL(select COUNT(to_char(RegistrationDate,'Month'))from S22_S003_5_Trainee GROUP BY to_char(RegistrationDate,'Month'))");
while (rs.next()) 
 monthName = rs.getString("Most_Registered_month");
System.out.println(monthName);
System.out.println("Enter dates between 1 and 31 of "+monthName);
int startDate = in.nextInt();
int endDate = in.nextInt();
if(startDate>endDate) System.out.println("start date should be lesser than the end date");



ResultSet dateResult=stmt.executeQuery("SELECT * from S22_S003_5_Trainee where RegistrationDate>= DATE '2021-10-"+startDate+"' and RegistrationDate <= DATE'2021-10-"+endDate+"' order by TraineeID Fetch First 10 Rows Only");

System.out.println("Discount offered to first 10 registered Trainees");

while (dateResult.next())
System.out.println(dateResult.getString("Fullname"));










connection.close();
}
catch (SQLException e) {







System.out.println("error in accessing the relation");
e.printStackTrace();
return;







}
}







}