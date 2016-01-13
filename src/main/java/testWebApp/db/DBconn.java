package testWebApp.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		String sql="INSERT INTO users (name, email, telephone, street, city, state, zip)"
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
}
