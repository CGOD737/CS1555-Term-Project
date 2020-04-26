import java.sql.SQLException;

/* Author Christopher Godfrey */

public class driver { //Similar to the userInterface.java in that all methdods have verification just 

	private static Olympics olympic = new Olympics();
	
	public static void main(String[] args) throws SQLException {
		System.out.println("*********************************");
		System.out.println("Establishing Connection");
		olympic.main(args); //connects to the database
		
		//example login
		olympic.login("example","password");
		olympic.testuserCreate();
		olympic.testuserDelete();
		olympic.testeventCreate();
		olympic.logout();
		olympic.login("coach", "pw123");
		olympic.testteamCreate();
		olympic.testparticipantAdd();
		olympic.testteamMemberAdd();
		olympic.exit();

	}

}