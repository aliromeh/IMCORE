/**
 * 
 */
package fr.epita.iam.business;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.FileIdentityDAO;
import fr.epita.iam.services.IdentityJDBCDAO;

/**
 * @author tbrou
 *
 */
public class CreateActivity {
	
	
	public static void execute(Scanner scanner){
		System.out.println("Identity Creation");
		System.out.println("please input the displayName");
		String displayName = scanner.nextLine();
		System.out.println("please input the email address");
		String email = scanner.nextLine();
		System.out.print("Enter Date of Birth (yyyy-MM-dd ): ");
		String date = scanner.nextLine();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			System.out.println("incorrect date");;
		}
		
		
		Identity identity = new Identity("",displayName, email,date);
		FileIdentityDAO identityWriter = new FileIdentityDAO("test.txt");
		
		
		IdentityJDBCDAO idenJDBCDAO = new IdentityJDBCDAO();
		try {
			idenJDBCDAO.write(identity);
			//persist the identity somewhere
			System.out.println("this is the identity you created");
			

			
			System.out.println("creation Done");
			
		} catch (Exception e) {
			// TODO: handle exception
			//identityWriter.write(identity);
			System.out.println("creation Failded");
		}
		
		
	}
	public static void Update(Scanner scanner){
		System.out.println("Update Records");
		IdentityJDBCDAO idenJDBCDAO = new IdentityJDBCDAO();
		List <Identity> identities = null;
		
		try {
			identities = idenJDBCDAO.readAllIdentities();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No users to Show");
		}
		for(Identity x : identities){
			System.out.println(x.toString());
		}
		System.out.println("Please select uid user to update");
		int uid = scanner.nextInt();
		
		for(Identity x : identities){
			if (Integer.parseInt(x.getUid())==uid ) {
				System.out.println(x.toString());
			}
		}
		
		Scanner scan = new Scanner(System.in);
		System.out.println("please input the newname");
		String displayName = scan.nextLine();
		
		System.out.println("please input the email address");
		String email = scan.nextLine();
		
		System.out.print("Enter Date of Birth (yyyy-MM-dd ): ");
		String date = scan.nextLine();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			System.out.println("incorrect date");
		}
		
		
		Identity identity = new Identity(String.valueOf(uid),displayName, email,date);
		
		try {
			idenJDBCDAO.Update(identity);
			//persist the identity somewhere
			System.out.println("this is the identity you updated");
			

			
			System.out.println("update Done");
			
		} catch (Exception e) {
			// TODO: handle exception
			//identityWriter.write(identity);
			System.out.println("update Failded");
		}
	}
	
	
	public static void Delete(Scanner scanner){
		System.out.println("Delete Records");
		IdentityJDBCDAO idenJDBCDAO = new IdentityJDBCDAO();
		List <Identity> identities = null;
		
		try {
			identities = idenJDBCDAO.readAllIdentities();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No users to Show");
		}
		for(Identity x : identities){
			System.out.println(x.toString());
		}
		System.out.println("Please select uid user to Delete");
		int uid = scanner.nextInt();
		
		for(Identity x : identities){
			if (Integer.parseInt(x.getUid())==uid ) {
				System.out.println(x.toString());
			}
		}
		
		try {
			idenJDBCDAO.Delete(uid);
			//persist the identity somewhere
			System.out.println("Record has been deleted");
			
		} catch (Exception e) {
			// TODO: handle exception
			//identityWriter.write(identity);
			System.out.println("Fail to  Delete");
		}
	}


}
