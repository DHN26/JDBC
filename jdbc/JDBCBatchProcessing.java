package com.kn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCBatchProcessing {
	private static final String URL="jdbc:mysql://localhost:3306/university";
	private static final String NAME="root";
	private static final String PASSWORD="root";
	private static final String Query1="UPDATE TRAINER SET GENDER='MALE' WHERE ID!=3;";
	private static final String Query2="UPDATE TRAINER SET GENDER='FEMALE' WHERE ID=3;";
	
	public static void main(String[] args) {
		Connection con=null;
		
		try {
			//1. Load and reg driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. Establish the connection with database
			con=DriverManager.getConnection(URL, NAME, PASSWORD);
			
			//3. Create statement object
			Statement stmt=con.createStatement();
			
			//BATCH PROCESSING - EXECUTING MULTIPLE QUERIES AT A TIME
			
			//a. set autocommit as false
			con.setAutoCommit(false);
			
			//b. add tasks to batches
			stmt.addBatch(Query1);
			stmt.addBatch(Query2);
			
			//c. get count of records affected
			int count[]=stmt.executeBatch();
			for (int i : count) {
				System.out.println(i+" rows affected!");
			}
			
			//d. commit back
			con.commit();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

}
