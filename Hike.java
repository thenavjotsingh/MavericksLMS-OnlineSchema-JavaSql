import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;



public class Hike {
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
System.out.println("****Multi-Lingual Trainer(s)****");
ResultSet rs=stmt.executeQuery("select TrainerID,Name from S22_S003_5_Trainer where TrainerID in(select TrainerID from S22_S003_5_TrainerLanguage group by TrainerID having count(TrainerID)>=ALL(select count(TrainerID) from S22_S003_5_TrainerLanguage group by TrainerID))");
while (rs.next()){
System.out.println(rs.getString("Name")+"speaks highest number of languages among others."+rs.getString("Name")+" gets hike");

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