package testWebApp.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;


public class DBconn {
	private Connection conn;
	private boolean isopen;
	public DBconn(){
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("No Driver");
		e.printStackTrace();
		return;
	}
	System.out.println("MySQL JDBC Driver Registered!");
	conn = null;

	try {
		conn= DriverManager
		.getConnection("jdbc:mysql://127.0.0.1:3306/httpTest","root", "");
		conn.setAutoCommit(false); 

	} catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return;
	}

	if (conn != null) {
		System.out.println("Connected");
		
	} else {
		System.out.println("Failed to connect");
		}
	}
	public boolean isOpen() {
		return isopen;
	}
	public void close() {
		if (!isopen) return;
			try {conn.close();}
			catch (Exception e) {}
				isopen = false;
				conn = null;
	}
	public void addUser(String name, String email, String telephone, String street, String city, String state, String zip) {
		String createTable ="";
		ResultSet rset = null;
		java.sql.PreparedStatement stmt = null;
		try {
		createTable = "CREATE TABLE IF NOT EXISTS httpTest.users("
				+ "id int (4) NOT NULL AUTO_INCREMENT, "
				+ "name text NOT NULL, "
				+ "email text NOT NULL, "
				+ "telephone text NOT NULL, "
				+ "street text NOT NULL, "
				+ "city text NOT NULL, "
				+ "state text NOT NULL, "
				+ "zip text NOT NULL, "
				+ "PRIMARY KEY (id)"
			+ ")";
			stmt = conn.prepareStatement(createTable);
			stmt.executeUpdate(createTable);
			stmt.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
			try{stmt.close();}
			catch(SQLException ex) {
			} try{conn.rollback();}
			catch(SQLException ex){}
		}
		String sql = "INSERT INTO users (name, email, telephone, street, city, state, zip)"
        + " values (?, ?, ?, ?, ?, ?, ?)"; 
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, name);
				stmt.setString(2, email);
				stmt.setString(3, telephone);
				stmt.setString(4, street);
				stmt.setString(5, city);
				stmt.setString(6, state);
				stmt.setString(7, zip);	
				stmt.executeUpdate();
				stmt.close();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try{stmt.close();}
				catch(SQLException ex){}
				
				try{conn.rollback();}
				catch(SQLException ex){}
			}
	}
	public ArrayList<String> getUsers(){
		ArrayList<String> jsonValues = new ArrayList<String>();
		Map<String, String>  resultValues = null;
		java.sql.PreparedStatement stmt = null;
		String json = null;
		String sql = "SELECT * FROM users LIMIT 5";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rset = stmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			int numColumns = rsmd.getColumnCount();
			while (rset.next()) {
				resultValues = new HashMap<String, String>();
				for(int i = 1; i < numColumns+1; i++){
					String col_name = rsmd.getColumnName(i);
					resultValues.put(col_name, rset.getString(col_name));
					json = new Gson().toJson(resultValues);
				}
		        jsonValues.add(json);
			}
			stmt.close();
			conn.commit();
			return jsonValues;
		} catch (SQLException e) {
			e.printStackTrace();
			try{stmt.close();}
			catch(SQLException ex){}
			
			try{conn.rollback();}
			catch(SQLException ex){}
		}
		return null;
	}
	public String delUser(int searchId) {
		ResultSet rset = null;
		String findUser = "SELECT * FROM users WHERE id = ?";
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(findUser);
			stmt.setInt(1, searchId);
			rset = stmt.executeQuery();			
			if (!rset.isBeforeFirst()) {
				stmt.close();
				conn.commit();
				return "No User with that ID";
			} else {
				stmt.close();
				conn.commit();
				String deleteUser= "DELETE FROM users WHERE id = ?";
				java.sql.PreparedStatement stmt2 = null;
				try {
					stmt2 = conn.prepareStatement(deleteUser);
					stmt2.setInt(1, searchId);
					stmt2.executeUpdate();
					stmt2.close();
					conn.commit();
					return "User Deleted";
				} catch (SQLException e) {
					e.printStackTrace();
					try{stmt.close();}
					catch(SQLException ex){}
					try{conn.rollback();}
					catch(SQLException ex){}
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try{stmt.close();}
			catch(SQLException ex){}
			try{conn.rollback();}
			catch(SQLException ex){}
		}
		return null;
	}
	public String updateUser(int searchId, String updateField, String newValue) {
		ResultSet rset = null;
		String col_name = "", col_value = "";
		String findUser = "SELECT * FROM users WHERE id = ?";
		String returnMessage = "";
		java.sql.PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(findUser);
			stmt.setInt(1, searchId);
			rset = stmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			int numColumns = rsmd.getColumnCount();
			if (!rset.isBeforeFirst()) {
				stmt.close();
				conn.commit();
				return "No User with that ID";
			} else {
				while (rset.next()) {
					for(int i = 1; i < numColumns + 1; i++){
						col_value = rset.getString(i);
						col_name = rsmd.getColumnName(i);
				if (updateField.equals(col_name) && !col_value.equals(newValue)) {
					try {
						java.sql.PreparedStatement stmt2 = null;
						String updateStatement = "UPDATE users SET " + col_name + " = " + "'" + newValue + "'" + " WHERE " + col_name + " = " + "'" + col_value + "'";
						stmt2 = conn.prepareStatement(updateStatement);
						stmt2.executeUpdate();
						stmt2.close();
						conn.commit();
						returnMessage = "User Updated";
						break;
					} catch (SQLException e) {
						e.printStackTrace();
						try{stmt.close();}
						catch(SQLException ex){}
						try{conn.rollback();}
						catch(SQLException ex){}
					}				
				} else {
					returnMessage =  "No Column name";
				}
					}
				}
			}		
		} catch (SQLException e) {
			e.printStackTrace();
			try{stmt.close();}
			catch(SQLException ex){}
			try{conn.rollback();}
			catch(SQLException ex){}
		}
		return returnMessage;
	}
}
