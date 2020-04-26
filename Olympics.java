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
	static boolean status = false;
	static boolean organizer = false;
	static boolean coach = false;
	static boolean guest = false;
	
	public void main(String[] args) { //main method establishes database connection and calls other methods.
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
		
		//createUser(insert_values);
	}
	//ORGANIZERS METHODS
	public void createUser(String insert_values )  {//1 creates users based on values passed in the method and stores them in the Olympic database
		String insert = "INSERT INTO USER_ACCOUNT(USERNAME, PASSKEY, ROLE_ID, LAST_LOGIN) VALUES ("+insert_values+" )"; //Statement to be executed
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
	public void dropUser(String insert_values) { //2
		String insert = "DELETE FROM USER_ACCOUNT WHERE user_id = " + insert_values; //Statement to be executed
		try {
			st.execute(insert);
			st.execute("Commit");
			System.out.println("User Successfully removed");
		}
		catch (SQLException e1) {
			System.out.println("Delete Failed");
            while (e1 != null) {
                System.out.println("Message = " + e1.getMessage());
                System.out.println("SQLState = " + e1.getSQLState());
                System.out.println("SQLErrorCode = " + e1.getErrorCode());
                e1 = e1.getNextException();
            }
		}
	}		
	public void createEvent(String insert_values) { //3
		String insert = "INSERT INTO EVENT(SPORT_ID, VENUE_ID, GENDER, EVENT_TIME) VALUES("+insert_values+")"; //Statement to be executed
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
	public void addEventOutcome(String insert_values) { //4
		String insert = "INSERT INTO SCOREBOARD(OLYMPICS_ID, EVENT_ID, TEAM_ID, PARTICIPANT_ID, POSITION, MEDAL_ID) VALUES()";  //Statement to be executed
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
	public void createTeam(String insert_values) { //5
		String insert = "INSERT INTO TEAM( OLYMPICS_ID, TEAM_NAME, COUNTRY_ID, SPORT_ID, COACH_ID)  VALUES("+insert_values+")";  //Statement to be executed
		try {
			st.execute(insert);
			st.execute("Commit");
			System.out.println("Team Successfully Created");
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
	public void registerTeam(String insert_values) { //6
		String insert = "INSERT INTO EVENT_PARTICIPATION(EVENT_ID, TEAM_ID, STATUS) VALUES("+insert_values+")";  //Statement to be executed
		try {
			st.execute(insert);
			st.execute("Commit");
			System.out.println("Team Successfully Registered");
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
	public void addParticipant(String insert_values) { //7
		String insert = "";  //Statement to be executed
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
	public void addTeamMember(String insert_values) { //8
		String insert = "INSERT INTO TEAM_MEMBER(TEAM_ID, PARTICIPANT_ID) VALUES ("+insert_values+")";  //Statement to be executed
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
	public void dropTeamMember(String insert_values) { //9
		String insert = "";  //Statement to be executed
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
	//METHODS ACCESSED BY EVERYONE
	public void login(String username, String password) throws SQLException { //10 Searches for the user ID
		PreparedStatement s = dbcon.prepareStatement("SELECT * FROM USER_ACCOUNT A WHERE A.USERNAME = ? AND A.PASSKEY = ?"); //searches if it's actually in result set
		s.setString(1, username);
		s.setString(2, password);
		
		ResultSet rs = s.executeQuery();
		if (rs.next()) {
			status = true;
			PreparedStatement d = dbcon.prepareStatement("SELECT ROLE_ID FROM USER_ACCOUNT A WHERE A.USERNAME = ? AND A.PASSKEY = ? "); //searches for the role
			d.setString(1, username);
			d.setString(2, password);
			
			ResultSet rd = d.executeQuery();
			
			int role_id = rs.getInt("role_id");
			
			
			if( role_id == 1 ) {
				organizer = true;
			}
			else if (role_id == 2) {
				coach = true;
			}
			else if (role_id == 3) {
				guest = true;
			}
			
			System.out.println("You are now signed in");
			return;
		}
		else {
			System.out.println("Incorrect username/password");
		}
		
	}
	public void displaySport() { //11
		
	}
	public void displayEvent() { //12
		
	}
	public void countryRanking() { //13
		
	}
	public void topkAthletes() { //14
		
	}
	public void connectedAthletes() { //15
		
	}
	public static void logout() { //16
		
		if ( !status ) {
			System.out.println("You are already logged out.");
			return;
		}
		status = false;
		organizer = false;
		coach= false;
		guest = false;
		
		System.out.println("Logout Successful");
		
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
			System.out.println("Exiting Program");
			System.exit(0);
		}
	}
	
	
	//Driver Helper methods. They are called from driver.java or as an automatic runthrough of all the methods in Olympic.java They essentially show the table and results of any querys wherer necessary
	

	public void testuserCreate() {
		
	
		
		System.out.println("Table Before Insert:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM USER_ACCOUNT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("user_id"));
				String user = rd.getString("username");
				String pass = rd.getString("passkey");
				String rol = Integer.toString(rd.getInt("role_id"));
				String date = rd.getString("last_login");
				
				System.out.println(ID+ " " + user + " " + pass + " " + rol + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		createUser("'Chris' , 'Godfrey', 1 , '11-AUG-12'");
		System.out.println();
		
		System.out.println("Table After Insert");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM USER_ACCOUNT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("user_id"));
				String user = rd.getString("username");
				String pass = rd.getString("passkey");
				String rol = Integer.toString(rd.getInt("role_id"));
				String date = rd.getString("last_login");
				
				System.out.println(ID+ " " + user + " " + pass + " " + rol + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println();
	}
	public void testuserDelete() {
		System.out.println("Table Before Delete:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM USER_ACCOUNT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("user_id"));
				String user = rd.getString("username");
				String pass = rd.getString("passkey");
				String rol = Integer.toString(rd.getInt("role_id"));
				String date = rd.getString("last_login");
				
				System.out.println(ID+ " " + user + " " + pass + " " + rol + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		dropUser(Integer.toString(2));
		System.out.println();
		
		System.out.println("Table After Delete");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM USER_ACCOUNT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("user_id"));
				String user = rd.getString("username");
				String pass = rd.getString("passkey");
				String rol = Integer.toString(rd.getInt("role_id"));
				String date = rd.getString("last_login");
				
				System.out.println(ID+ " " + user + " " + pass + " " + rol + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println();		
	}
	public void testeventCreate() {
		System.out.println("Table Before Event Insert:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM EVENT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		createEvent("4, 1, 'M','12-AUG-12' ");
		System.out.println();
		
		System.out.println("Table After Event Insert:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM EVENT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}

	public void testteamCreate() {
		System.out.println("Table Before Team Create:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM EVENT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		createEvent("4, 1, 'M','12-AUG-12' ");
		System.out.println();
		
		System.out.println("Table After Team Create:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM EVENT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
		
	public void testteamRegister() {
		System.out.println("Table Before Team Register:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM EVENT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		createEvent("4, 1, 'M','12-AUG-12' ");
		System.out.println();
		
		System.out.println("Table After Team Register:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM EVENT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
		
	public void testparticipantAdd() {
		System.out.println("Table Before participant Add:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM PARTICIPANT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		createEvent("4, 1, 'M','12-AUG-12' ");
		System.out.println();
		
		System.out.println("Table After Participant add:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM PARTICIPANT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
	
	public void testeventOutcomeAdd() {
		System.out.println("Table Before Event Outcome added:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM EVENT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		createEvent("4, 1, 'M','12-AUG-12' ");
		System.out.println();
		
		System.out.println("Table After Event Outcome added:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM EVENT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
	public void testteamMemberAdd() {
		System.out.println("Table Before team Member Added:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM EVENT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		createEvent("4, 1, 'M','12-AUG-12' ");
		System.out.println();
		
		System.out.println("Table After Event Insert:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM EVENT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
		

	public void testteamMemberDrop() {
		System.out.println("Table Before Team Member dropped:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM EVENT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		createEvent("4, 1, 'M','12-AUG-12' ");
		System.out.println();
		
		System.out.println("Table After Team Member dropped:");
		try {
			PreparedStatement d = dbcon.prepareStatement("SELECT * FROM EVENT");
			ResultSet rd = d.executeQuery();
			
			while (rd.next()) {
				String ID = Integer.toString(rd.getInt("event_id"));
				String sport = Integer.toString(rd.getInt("sport_id"));
				String venue = Integer.toString(rd.getInt("venue_id"));
				String gen = rd.getString("gender");
				String date = rd.getString("event_time");
				
				System.out.println(ID+ " " + sport + " " + venue + " " + gen + " " + date);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}

}
