/**
 * 
 */
package fr.epita.iam.launcher;

import java.util.Scanner;

import fr.epita.iam.business.CreateActivity;

/**
 * @author tbrou
 *
 */
public class ConsoleLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the IAM software");
		Scanner scanner = new Scanner(System.in);
		
		//authentication
		if (!authenticate(scanner)){
			end(scanner);
			return;
		}
		String choice;
		do {
			//menu
			System.out.println("Please select an action :");
			System.out.println("a. create an Identity");
			System.out.println("b. modify an Identity");
			System.out.println("c. delete an Identity");
			System.out.println("d. quit");
			choice = scanner.nextLine();

			switch (choice) {
			case "a":
				//Create
				CreateActivity.execute(scanner);
				break;
			case "b":
				//Modify
				CreateActivity.Update(scanner);
				break;
				
			case "c":
				//Delete
				CreateActivity.Delete(scanner);
				break;
				
			case "d":
				//Quit
				System.exit(0);
				break;
				
			default:
				System.out.println("Your choice is not recognized");
				break;
			}

		} while (!choice.equals("d"));		
		
		
		end(scanner);
	}

	/**
	 * @param scanner
	 */
	private static void end(Scanner scanner) {
		//System.out.println("Thanks for using this application, good bye!");
		scanner.close();
	}

	/**
	 * @param scanner
	 */
	private static boolean authenticate(Scanner scanner) {
		System.out.println("Please type your login : ");
		String login = scanner.nextLine();
		
		System.out.println("Please type your password : ");
		String password = scanner.nextLine();
		
		if (login.equals("adm") && password.equals("pwd")){
			System.out.println("Athentication was successful");
			return true;
		}else{
			System.out.println("Athentication failed");
			return false;
		}
	}

}
