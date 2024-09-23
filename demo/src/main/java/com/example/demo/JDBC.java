package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBC {

	  public static Connection connection = null;

	    public static void main(String[] args) {

	        System.out.println("-------- MySQL JDBC Connection Testing ------------");

	        try {
	            connect();
	        //createTables();
	            InsertTable.updateTables();
	        } catch (SQLException e) {
	            System.out.println("Connection Failed!");
	            e.printStackTrace();
	            return;
	        }

	       close();
	    }

	    public static void connect () throws SQLException {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            System.out.println("MySQL JDBC Driver Registered!");
	        } catch (ClassNotFoundException e) {
	            System.out.println("Where is your MySQL JDBC Driver?");
	            e.printStackTrace();
	            throw new SQLException();
	        }

	        connection = DriverManager
	                .getConnection("jdbc:mysql://localhost:3306/testiba?useUnicode=true&serverTimezone=UTC", "root", "ЗМЩлшкпшы789");
	        if (connection == null) {
	            throw new SQLException();
	        } else {
	            System.out.println("Successfully connected");
	        }
	    }

	    public static void close() {
	        try {
	            if(connection != null) {
	                connection.close();
	                System.out.println("Closing connection");
	            }
	        } catch (SQLException e) {
	            System.out.println("Failed to close connection!");
	        }
	    }
	public static void createTables()throws SQLException
	{
		Statement stmt = null;
  

                stmt = connection.createStatement();

                //Drop Tables
                String drop1 = "DROP TABLE IF EXISTS Authors";
                stmt.executeUpdate(drop1);

                String drop2 = "DROP TABLE IF EXISTS Titles";
                stmt.executeUpdate(drop2);

                String drop3 = "DROP TABLE IF EXISTS Publishers";
                stmt.executeUpdate(drop3);

                String drop4 = "DROP TABLE IF EXISTS authorISBN";
                stmt.executeUpdate(drop4);
                System.out.println("Data deleted");
            // Create Tables
            String authorsTable = "CREATE TABLE Authors " +
                    "(authorID INTEGER NOT NULL AUTO_INCREMENT, " +
                    " firstName CHAR(20), " +
                    " lastName CHAR(20), " +
                    " PRIMARY KEY (authorID))";

            stmt.executeUpdate(authorsTable);
            System.out.println("Created Authors table");

            String titlesTable = "CREATE TABLE Titles " +
                    "(isbn CHAR(13) not NULL, " +
                    " title VARCHAR(255), " +
                    " editionNumber INTEGER, " +
                    " year CHAR(4), " +
                    " publisherID INTEGER REFERENCES Publishers(publisherID), " +
                    " price DECIMAL(8,2), " +
                    " PRIMARY KEY (isbn))";

            stmt.executeUpdate(titlesTable);
            System.out.println("Created Titles table");

            String publishersTable = "CREATE TABLE Publishers " +
                    "(publisherID INTEGER NOT NULL AUTO_INCREMENT, " +
                    " publisherName CHAR(100), " +
                    " PRIMARY KEY (publisherID))";

            stmt.executeUpdate(publishersTable);
            System.out.println("Created Publishers table");

            String authorISBNTable = "CREATE TABLE authorISBN " +
                    "(authorID INTEGER REFERENCES Authors(authorID), " +
                    " isbn CHAR(10) REFERENCES Titles(isbn))";

            stmt.executeUpdate(authorISBNTable);
            System.out.println("Created authorISBN table");
  }
}
