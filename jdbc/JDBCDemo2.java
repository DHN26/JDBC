package com.kn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo2 {

	private static final String SELECT_QUERY = "SELECT * FROM TRAINER;";

	public static void main(String[] args) {
		Connection con=null;
		String URL="jdbc:mysql://localhost:3306/university";
		String USERNAME="root";
		String PASSWORD="root";
		//String CREATE_QUERY="CREATE TABLE TRAINER(ID INT, NAME VARCHAR(30));";
		//String INSERT_QUERY="INSERT INTO TRAINER VALUES(1,'PUNITH');";
		//String UPDATE_QUERY="UPDATE TRAINER SET NAME='ARUN' WHERE ID=1;";
		//String DELETE_QUERY="DELETE FROM TRAINER WHERE ID=1;";
		
		
		try {
			//Load and reg driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded successfully!");
			
			//2. Establish the connection with database
			con=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			//3. Create statement object
			Statement stmt=con.createStatement();
			
			//4. Send and execute query
			//Create query --> stmt.execute(CREATE_QUERY);
			//System.out.println("Table created successfully");
			
//			int affected=stmt.executeUpdate(DELETE_QUERY);
//			System.out.println(affected+" rows afffected!");
			
			//5. SELECT QUERY
			ResultSet res= stmt.executeQuery(SELECT_QUERY);
			while(res.next())
			{
				System.out.println("ID : "+res.getInt(1)+" and NAME : "+res.getString(2));
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		finally {
			
			try {
				if(con!=null)
				{
				con.close();
				System.out.println("Connection closed!!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
	}

}
