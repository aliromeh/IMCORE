/**
 * 
 */
package fr.epita.iam.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.epita.iam.datamodel.Identity;

/**
 * @author tbrou
 *
 */
public class IdentityJDBCDAO {

	private Connection currentConnection;

	/**
	 * 
	 */
	public IdentityJDBCDAO() {
		try {
			getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		try {
			this.currentConnection.getSchema();
		} catch (Exception e) {
			// TODO read those information from a file
			String user = "ALI";
			String password = "ALI";
			String connectionString = "jdbc:derby://localhost:1527/IAM;create=true";
			this.currentConnection = DriverManager.getConnection(connectionString, user, password);
		}
		return this.currentConnection;
	}

	private void releaseResources() {
		try {
			this.currentConnection.close();
		} catch (Exception e) {
			//TODO trace Exception
		}
	}

	public List<Identity> readAllIdentities() throws SQLException {
		List<Identity> identities = new ArrayList<Identity>();

		Connection connection = getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from IDENTITIES");
		ResultSet rs = statement.executeQuery();
		Date now = new Date();

        
		while (rs.next()) {
			String displayName;
			String email;
			Date date;
			int uid = rs.getInt("IDENTITY_ID");
			if (rs.getString("IDENTITY_DISPLAYNAME") != null) {
				 displayName = rs.getString("IDENTITY_DISPLAYNAME");
			}
			else{
				displayName="";
				}
			if (rs.getString("IDENTITY_EMAIL") != null) {
				 email = rs.getString("IDENTITY_EMAIL");
			}
			else{
				email="";
				}
			
			if (rs.getDate("IDENTITY_BIRTHDATE") != null) {
				 date = rs.getDate("IDENTITY_BIRTHDATE");
			}
			else{
				date= now;
				}
			
			Identity identity = new Identity(String.valueOf(uid), displayName, email, date.toString());
			identities.add(identity);
		}

		return identities;
	}

	public void write(Identity identity) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "INSERT INTO IDENTITIES(IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_BIRTHDATE) VALUES(?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		// TODO implement date for identity
		pstmt.setString(3, identity.getDate());

		pstmt.execute();
	}
	
	public void Update(Identity identity) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "update IDENTITIES set IDENTITY_DISPLAYNAME = ?, IDENTITY_EMAIL =?, IDENTITY_BIRTHDATE =? where IDENTITY_ID =? ";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		// TODO implement date for identity
		pstmt.setString(3, identity.getDate());
		pstmt.setString(4, identity.getUid());

		pstmt.execute();
	}
	
	public void Delete(int Uid) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "Delete From IDENTITIES where IDENTITY_ID =?";
		
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setInt(1, Uid);

		pstmt.execute();
	}

}
