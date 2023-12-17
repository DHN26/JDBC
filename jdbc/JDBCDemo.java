package com.kn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) {
		Connection con=null;
		String URL="jdbc:mysql://localhost:3306/university";
		String username="root";
		String password="root";
		//String insert_query="create table student3 (id int, name varchar(20), marks int)";
		//String insert_query="insert into student3 values(1,'Rama',100);";
		//String update_query="update student3 set name='Seeta' where id=1;";
		String del_query="delete from student3 where id=1;";
		
		try {
			//1.Load and Register driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//FQCN(Fully Qualified Class Name) or Cannonical Name
			System.out.println("Driver loaded and registered successfully!");
			
			//2. Establish the connection with database
			con=DriverManager.getConnection(URL, username, password);
			System.out.println("Connection established succesfully!");
			
			//3. Create statement object
			Statement stmt=con.createStatement();
			
			//4. Send and execute query
			//for creation - stmt.execute(insert_query);
			//for insertion,update,delete - 
			int affected=stmt.executeUpdate(del_query);
			System.out.println(affected+" rows affected");
			
			//System.out.println("Table student3 created successfully!");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Driver not found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Failed to estblish connection");
			e.printStackTrace();
		}
		
		finally {
			
				try {
					if(con!=null)
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		

	}

}
