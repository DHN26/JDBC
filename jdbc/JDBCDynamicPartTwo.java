package com.kn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCDynamicPartTwo {

	private static final String SELECT_QUERY = "SELECT * FROM EMP;";
	private static final String UPDATE_QUERY = "UPDATE EMP SET SALARY=SALARY+1000 WHERE ID=?;";
	private static final String DELETE_QUERY = "DELETE FROM EMP WHERE ID=?;";
	private static final String INSERT_QUERY = "INSERT INTO EMP VALUES (?,?,?);";

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		String URL = "jdbc:mysql://localhost:3306/university";
		String NAME = "root";
		String PASSWORD = "root";

		try {
			// step-1
			Class.forName("com.mysql.cj.jdbc.Driver");

			// step-2
			Connection con = DriverManager.getConnection(URL, NAME, PASSWORD);

			// step-3
			Statement stmt = con.createStatement();
			int n = 0;
			while (n != 5) {
				System.out.println("1-->INSERT DATA");
				System.out.println("2-->UPDATE DATA");
				System.out.println("3-->SELECT DATA");
				System.out.println("4-->DELETE DATA");
				System.out.println("Enter your option among these :");
				n = scan.nextInt();

				switch (n) {
				case 1:
					// METHOD CALL FOR INSERT DATA
					create(con, scan);
					break;

				case 2:
					// METHOD CALL FOR UPDATE DATA
					update(con, scan);
					break;

				case 3:
					// METHOD CALL FOR READ(SELECT) DATA
					read(con, scan);
					break;

				case 4:
					// METHOD CALL FOR DELETE DATA
					delete(con, scan);

				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// method to insert data into the table
	public static void create(Connection con, Scanner scan) {
		try {
			System.out.println("Data before insertion : ");
			read(con, scan);
			System.out.println();

			// for dynamic insertion of data from user
			System.out.println("Enter ID : ");
			int id = scan.nextInt();
			System.out.println("Enter NAME : ");
			String name = scan.next();
			System.out.println("Enter SALARY : ");
			int sal = scan.nextInt();

			// actual task
			PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, sal);
			ps.execute();

			System.out.println("Insert successful!!");
			System.out.println();

			System.out.println("Data after insertion : ");
			read(con, scan);
			System.out.println();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Inserted!!");
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	// select data
	public static void read(Connection con, Scanner scan) {
		try {
			PreparedStatement ps = con.prepareStatement(SELECT_QUERY);
			ResultSet res = ps.executeQuery(SELECT_QUERY);
			while (res.next()) {
				System.out.println("ID: " + res.getInt(1) + " NAME:" + res.getString(2) + " SALARY:" + res.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// update data
	public static void update(Connection con, Scanner scan) {
		try {
			// data before updation
			System.out.println("Data before updation : ");
			read(con, scan);
			System.out.println();

			// actual task
			PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);
			System.out.println("Enter id whose salary has to be updates:");
			int id = scan.nextInt();
			ps.setInt(1, id);
			ps.execute();

			System.out.println("Salary has been modified!");

			// data after updation
			System.out.println("Data after updation : ");
			read(con, scan);
			System.out.println();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// delete data
	public static void delete(Connection con, Scanner scan) {
		try {
			// display before deletion
			System.out.println("Data before deletion : ");
			read(con, scan);
			System.out.println();

			// actual operation
			PreparedStatement ps = con.prepareStatement(DELETE_QUERY);
			System.out.println("Enter id to be deleted : ");
			int id = scan.nextInt();
			ps.setInt(1, id);
			ps.execute();

			System.out.println("ID deleted!!");

			// data after deletion
			System.out.println("Data after deletion : ");
			read(con, scan);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
