package src.main.java.com.gcaceres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hello {

	public static void main(String[] args) {

		System.out.println("hello world");
		/*
		 * int [][] twodim2 = {{1,2,3}, {9,4}, {5,6,7,8}};
		 * 
		 * for(int x=0;x<10;x++){ for(int y=0; y<10;y++){ try {
		 * System.out.println(twodim2[y][x]); } catch (Exception e) {
		 * System.out.println("out of bounds"); } } }
		 */
	}

	// conexion a DB usando JDBC
	public void connectToAndQueryDatabase(String username, String password) throws Exception {

		Connection con = DriverManager.getConnection("jdbc:myDriver:myDatabase", username, password);

		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM Table1");
		while (rs.next()) {
			int x = rs.getInt("a");
			String s = rs.getString("b");
			float f = rs.getFloat("c");
		}
	}
}
