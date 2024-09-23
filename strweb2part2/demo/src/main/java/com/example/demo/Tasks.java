package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tasks {

	public static void main(String[] args) {
	      Statement stmt = null;
	      try{

	         JDBC.connect();
	         
	         stmt = JDBC.connection.createStatement();
	         task7(stmt);
	      } catch(SQLException se) {
	          // Handle errors for JDBC
	          se.printStackTrace();
	       } finally {
	          // Finally block, used to close resources
	          JDBC.close();
	       }
	    }
	public static void task1(Statement stmt) throws SQLException
	{
		String query = "SELECT * FROM authors ORDER BY firstName, lastName";
        ResultSet rs1 = stmt.executeQuery(query);
         while (rs1.next()) {
          int id = rs1.getInt("authorID");
           String firstName = rs1.getString("firstName");
           String lastName = rs1.getString("lastName");
           System.out.println(id + "\t" + firstName + "\t" + lastName);
         }
	}
	public static void task2(Statement stmt) throws SQLException 
	{
		 stmt.executeUpdate("INSERT INTO publishers(publisherName) VALUES ('Amongus')");
		 String query = "SELECT * FROM publishers WHERE publisherName = 'Amongus'";
		 ResultSet rs1 = stmt.executeQuery(query);
	     rs1.next(); 
	          System.out.println(rs1.getInt("publisherID") + "\t" 
	                              + rs1.getString("publisherName"));
	}
	public static void task3(Statement stmt) throws SQLException 
	{
		String query = "SELECT * FROM publishers WHERE publisherID = 12";
		 ResultSet rs1 = stmt.executeQuery(query);
		 rs1.next();
		 System.out.println(rs1.getInt("publisherID") + "\t" + rs1.getString("publisherName"));
		 stmt.executeUpdate("UPDATE publishers SET publisherName = 'Valley' WHERE publisherID = 12");
		 rs1 = stmt.executeQuery(query);
		 rs1.next();
		 System.out.println(rs1.getInt("publisherID") + "\t" + rs1.getString("publisherName"));
	}
	public static void task4(Statement stmt) throws SQLException 
	{
		String query = "SELECT * FROM titles WHERE publisherID = 1 ORDER BY title";
		 ResultSet rs1 = stmt.executeQuery(query);
		 while (rs1.next())
		 { 
			 System.out.println(rs1.getString("isbn") + "\t" 
					+ rs1.getString("title") + "\t"
					+ rs1.getInt("editionNumber") + "\t"
					+ rs1.getString("year") + "\t"
					+ rs1.getInt("publisherID") + "\t"
					+ rs1.getDouble("price"));
		 }
	}
	public static void task5(Statement stmt) throws SQLException
	{
		 stmt.executeUpdate("INSERT INTO authors(firstName, lastName) VALUES ('Yevgeny', 'Zamyatin')");
		 
	}
	public static void task6(Statement stmt) throws SQLException
	{
		 stmt.executeUpdate("UPDATE authors SET firstName = 'Joanne', lastName='Rowling' WHERE authorID = 11");
	}
	public static void task7(Statement stmt) throws SQLException
	{
		stmt.executeUpdate("INSERT INTO publishers(publisherName) VALUES ('Amongus')");
		stmt.executeUpdate("INSERT INTO titles(isbn, title, editionNumber, year, publisherID, price) VALUES\r\n"
		 		+ "	('5343175416', 'Demons', 1, 1888, (select publisherID from publishers where publisherName = 'Amongus'), 8.82);");
		stmt.executeUpdate("INSERT INTO authorisbn VALUES ((SELECT authorID "
				+ "FROM authors WHERE firstName = 'Fyodor' AND lastName = 'Dostoevsky'), '5343175416');");
	}
}
