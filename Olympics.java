import java.sql.*;
import java.io.*;
import java.util.*;

/**
 * @author Christopher Godfrey ctg18
 *
 */
public class Olympics {
	private static String JDBC_DRIVER = "";
	private static String DB_URL = "jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass";
	private static String userID = "ctg18";
	private static String pwd = "4110329";
	private static Connection dbcon;
	private static Statement st;
	
	//booleans to indicate login status.
	private static boolean status = false;
	private static boolean organizer = false;
	private static boolean coach = false;
	private static boolean guest = false;
	
	public static void main(String[] args) { //main method establishes database connection and calls other methods.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			dbcon = DriverManager.getConnection(DB_URL, userID, pwd);
			st = dbcon.createStatement();
			System.out.println("Database Connection Succesful");
		}
		catch (Exception e ){
			System.out.println("Failed to Connect to DataBase");
			System.exit(0);
		}
		
		String insert_values = "'exampleuser', 'password', 0, '08-AUG-12'"; 
		createUser(insert_values);
		exit();
	}
	//ORGANIZERS METHODS
	public static void createUser(String insert_values )  {//1 creates users based on values passed in the method and stores them in the Olympic database
		String insert = "INSERT INTO USER_ACCOUNT(USERNAME, PASSKEY, ROLE_ID, LAST_LOGIN) VALUES ("+insert_values+" )"; //TestStatement
		try {
			st.execute(insert);
			st.execute("Commit");
			System.out.println("Account Successfully Created");
		}
		catch (SQLException e1) {
			System.out.println("Insert Failed");
            while (e1 != null) {
                System.out.println("Message = " + e1.getMessage());
                System.out.println("SQLState = " + e1.getSQLState());
                System.out.println("SQLErrorCode = " + e1.getErrorCode());
                e1 = e1.getNextException();
            }
		}
	}
	public static void dropUser() { //2
		
	}
	public static void createEvent() { //3
		String insert = "INSERT INTO EVENT(EVENT_ID, SPORT_ID, VENUE_ID, GENDER, EVENT_TIME) VALUES(1, 1, 1, 0, '08-AUG-12')"; //Test Statement
		try {
			st.execute(insert);
			st.execute("Commit");
			System.out.println("Event Sucessfully Created");
		}
		catch (SQLException e1) {
			System.out.println("Insert Failed");
            while (e1 != null) {
                System.out.println("Message = " + e1.getMessage());
                System.out.println("SQLState = " + e1.getSQLState());
                System.out.println("SQLErrorCode = " + e1.getErrorCode());
                e1 = e1.getNextException();
            }
		}		
	}
	public static void addEventOutcome() { //4
		String insert = ""; //Test Statement
		try {
			st.execute(insert);
			st.execute("Commit");
			System.out.println("Event Outcome has been successfully added.");
		}
		catch (SQLException e1) {
			System.out.println("Insert Failed");
            while (e1 != null) {
                System.out.println("Message = " + e1.getMessage());
                System.out.println("SQLState = " + e1.getSQLState());
                System.out.println("SQLErrorCode = " + e1.getErrorCode());
                e1 = e1.getNextException();
            }
		}				
	}		
	//COACH METHODS
	public static void createTeam() { //5
		String insert = ""; //Test Statement
		try {
			st.execute(insert);
			st.execute("Commit");
			System.out.println("Insert into Team Sucessful");
		}
		catch (SQLException e1) {
			System.out.println("Insert Failed");
            while (e1 != null) {
                System.out.println("Message = " + e1.getMessage());
                System.out.println("SQLState = " + e1.getSQLState());
                System.out.println("SQLErrorCode = " + e1.getErrorCode());
                e1 = e1.getNextException();
            }
		}				
	}
	public static void registerTeam() { //6
		
	}
	public static void addParticipant() { //7
		String insert = ""; //Test Statement
		try {
			st.execute(insert);
			st.execute("Commit");
			System.out.println("Participant Succesfully added");
		}
		catch (SQLException e1) {
			System.out.println("Insert Failed");
            while (e1 != null) {
                System.out.println("Message = " + e1.getMessage());
                System.out.println("SQLState = " + e1.getSQLState());
                System.out.println("SQLErrorCode = " + e1.getErrorCode());
                e1 = e1.getNextException();
            }
		}				
	}		
	public static void addTeamMember() { //8
		String insert = ""; //Test Statement
		try {
			st.execute(insert);
			st.execute("Commit");
			System.out.println("Team Member Succesfully added");
		}
		catch (SQLException e1) {
			System.out.println("Insert Failed");
            while (e1 != null) {
                System.out.println("Message = " + e1.getMessage());
                System.out.println("SQLState = " + e1.getSQLState());
                System.out.println("SQLErrorCode = " + e1.getErrorCode());
                e1 = e1.getNextException();
            }
		}					
	}
	public static void dropTeamMember() { //9
		
	}
	//METHODS ACCESSED BY EVERYONE
	public static void login(String username, String password) { //10 Searches for the user ID
		
	}
	public static void displaySport() { //11
		
	}
	public static void displayEvent() { //12
		
	}
	public static void countryRanking() { //13
		
	}
	public static void topkAthletes() { //14
		
	}
	public static void connectedAthletes() { //15
		
	}
	public static void logout() { //16
		status = false;
		
		if (organizer)
			organizer = false;
		else if (coach)
			coach= false;
		else if (guest)
			guest = false;
		
	}
	public static void exit() { //17 cleanly exits the program
		if ( status ) { //if the user is still logged in, then log them out
			logout();
		}
		
		try {
			st.close();
			dbcon.close();	
		}
		catch (SQLException E) {
			System.out.println("Failed to Close Safely");
		}
		finally {
			System.out.println("Exitting Program");
			System.exit(0);
		}
	}

}
