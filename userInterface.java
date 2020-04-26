import java.io.*;
import java.sql.SQLException;
import java.util.*;

/* Author Christopher Godfrey */

public class userInterface {
	
	private static Scanner scan = null;
	
	private static Olympics olympic = new Olympics();
	
	public static void main(String args[]) {
		System.out.println("*********************************");
		System.out.println("Establishing Connection");
		olympic.main(args); //connects to the database
		
		//olympic.status = true;
		//olympic.organizer = true;
		//olympic.coach = true;
		
		userInterface mainMenu = new userInterface();
		scan = new Scanner(System.in);
		while(true) {
			switch(mainMenu.menu()) {
				case 1:
					userCreate();
					break;
				case 2:
					userDelete();
					break;
				case 3:
					eventCreate();
					break;
				case 4:
					eventOutcomeAdd();
					break;
				case 5:
					teamCreate();
					break;
				case 6:
					teamRegister();
					break;
				case 7:
					participantAdd();
					break;
				case 8:
					teamMemberAdd();
					break;
				case 9:
					teamMemberDrop();
					break;
				case 10:
					sportDisplay();
					break;
				case 11:
					eventDisplay();
					break;
				case 12:
					athletestopK();
					break;
				case 13:
					athletesConnected();
					break;
				case 14:
					rankingCountry();
					break;
				case 15:
					login();
					break;
				case 16:
					logout();
					break;
				case 17:
					exit();
					break;
			}
		}
	}
	private int menu() {
	    System.out.println("*********************************");
	    System.out.println("Welcome to Olympic Manager System");
	    System.out.println("1. Create User.");
	    System.out.println("2. Delete User.");
	    System.out.println("3. Create Event.");
	    System.out.println("4. Add Event Outcome.");
		System.out.println("5. Create Team.");
		System.out.println("6. Register Team.");
		System.out.println("7. Add Participant" );
		System.out.println("8. Add Team Member" );
		System.out.println("9. Delete Team Member" );
	    System.out.println("10. Display Sport");
	    System.out.println("11. Display Event");
	    System.out.println("12. Top k Athletes");
	    System.out.println("13. Connected Athletes");
	    System.out.println("14. Country Ranking");
	    System.out.println("15. Login");
	    System.out.println("16. Signout");
	    System.out.println("17. Exit");
	    System.out.println("*********************************");
	    System.out.print("Please choose a menu option (1-17): ");

	    int choice = Integer.parseInt(scan.nextLine());
	    return choice;
	}

	@SuppressWarnings("static-access")
	private static void userCreate() { //deals with user create in that it looks at privileges  
		
		if ( !olympic.status ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		else if ( !olympic.organizer ) {
			System.out.println("*********************************");
			System.out.println("You do not have permission to access this section");
			return;
		}
		System.out.println("Create User");
		System.out.println("*********************************");
		System.out.println("Enter Username: ");
		String username = "'"+scan.nextLine()+"',";
		System.out.println("Enter Password: ");
		String pwd = "'"+scan.nextLine()+"',";
		System.out.println("Enter Role (organizer, coach); ");
		String role = "'"+ scan.nextLine()+"'"; 
		
		if (role.equals("'organizer'"))
			role = Integer.toString(1)+",";
		if (role.equals("'coach'"))
			role = Integer.toString(2)+",";
		
		String Login = "'01-JUL-1972'";
			
		String insert = username+pwd+role+Login;
		olympic.createUser(insert);
			
	}
	@SuppressWarnings("static-access")
	private static void userDelete() { //deals with user delete in that it looks at privileges  

		if ( !olympic.status ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		else if ( !olympic.organizer ) {
			System.out.println("*********************************");
			System.out.println("You do not have permission to access this section");
			return;
		}
		System.out.println("Drop User");
		System.out.println("*********************************");
		System.out.println("Enter User_ID to be deleted: ");
		String user_id = scan.nextLine();
		
		olympic.dropUser(user_id);
		
	}
	@SuppressWarnings("static-access")
	private static void eventCreate() { //deals withevent create in that it looks at privileges  

		if ( !olympic.status ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		else if ( !olympic.organizer ) {
			System.out.println("*********************************");
			System.out.println("You do not have permission to access this section");
			return;
		}
		System.out.println("Create Event");
		System.out.println("*********************************");
		System.out.println("Please Enter Sport ID: ");
		String sportid = "'"+scan.nextLine()+"',";
		System.out.println("Please Enter Venue ID ");
		String venue_id = "'"+scan.nextLine()+"',";
		System.out.println("Please Indicate the Gender (M or F): ");
		String gender = "'"+ scan.nextLine()+"',"; 		
		System.out.println("Please Indicate the Data in Format ( DD-MON-YY ):");
		String date = "'"+ scan.nextLine()+"'"; 
		
		String insert = sportid + venue_id + gender + date;
		olympic.createEvent(insert);
		
	}
	@SuppressWarnings("static-access")
	private static void eventOutcomeAdd() { //deals with eventoutcome add in that it looks at privileges  
		if ( !olympic.status ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		else if ( !olympic.organizer ) {
			System.out.println("*********************************");
			System.out.println("You do not have permission to access this section");
			return;
		}

		System.out.println("Create Event Outcome Add");
		System.out.println("*********************************");
		System.out.println("Please Enter Olympic ID: ");
		String Olympic = "'"+scan.nextLine()+"',";
		System.out.println("Please Enter Event ID ");
		String Eventid = "'"+scan.nextLine()+"',";
		System.out.println("Please Enter Team ID ");
		String teamid = "'"+scan.nextLine()+"',";
		System.out.println("Please Enter Participant ID ");
		String participant = "'"+scan.nextLine()+"',"; 		
		System.out.println("Please Indicate the Data in Format ( DD-MON-YY ):");
		String date = "'"+ scan.nextLine()+"'"; 
		
	}	
	@SuppressWarnings("static-access")
	private static void teamCreate() { //deals with eventoutcome add in that it looks at privileges  
		if ( !olympic.coach ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		else if ( !olympic.organizer ) {
			System.out.println("*********************************");
			System.out.println("You do not have permission to access this section");
			return;
		}
		
	}	
	@SuppressWarnings("static-access")
	private static void teamRegister() { //deals with eventoutcome add in that it looks at privileges  
		if ( !olympic.coach ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		else if ( !olympic.organizer ) {
			System.out.println("*********************************");
			System.out.println("You do not have permission to access this section");
			return;
		}
		
	}	
	@SuppressWarnings("static-access")
	private static void participantAdd() { //deals with eventoutcome add in that it looks at privileges  
		if ( !olympic.status ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		else if ( !olympic.coach ) {
			System.out.println("*********************************");
			System.out.println("You do not have permission to access this section");
			return;
		}
		
	}	
	@SuppressWarnings("static-access")
	private static void teamMemberAdd() { //deals with eventoutcome add in that it looks at privileges  
		if ( !olympic.status ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		else if ( !olympic.coach ) {
			System.out.println("*********************************");
			System.out.println("You do not have permission to access this section");
			return;
		}
		
	}	
	@SuppressWarnings("static-access")
	private static void teamMemberDrop() { //deals with eventoutcome add in that it looks at privileges  
		if ( !olympic.status ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		else if ( !olympic.coach ) {
			System.out.println("*********************************");
			System.out.println("You do not have permission to access this section");
			return;
		}
		
	}
	private static void login() { //stores username and password into a String and inserts them into the login method which is checked for a value in the database.
		
		System.out.println("Username:");
		String Username = scan.nextLine(); 
		
		System.out.println("Password:");
		String Password = scan.nextLine();
		
		try {
			olympic.login(Username, Password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("static-access")
	private static void logout() {
		olympic.logout();
		
	}
	@SuppressWarnings("static-access")
	private static void exit() {
		olympic.exit();
	}
	@SuppressWarnings("static-access")
	private static void rankingCountry() { //deals with eventoutcome add in that it looks at privileges  
		if ( !olympic.status ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		
	}	
	@SuppressWarnings("static-access")
	private static void sportDisplay() { //deals with eventoutcome add in that it looks at privileges  
		if ( !olympic.status ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		
	}
	@SuppressWarnings("static-access")
	private static void eventDisplay() { //deals with eventoutcome add in that it looks at privileges  
		if ( !olympic.status ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		
	}
	@SuppressWarnings("static-access")
	private static void athletestopK() { //deals with eventoutcome add in that it looks at privileges  
		if ( !olympic.status ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		
	}
	@SuppressWarnings("static-access")
	private static void athletesConnected() { //deals with eventoutcome add in that it looks at privileges  
		if ( !olympic.status ) {
			System.out.println("*********************************");
			System.out.println("Please Login Before Continuing");
			return;
		} 
		
	}

}
