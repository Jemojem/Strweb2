package com.example.demo;


import java.sql.*;
import java.util.*;

public class GetAllRows {
   
   public static void main(String[] args) {
      Statement stmt = null;
      try{

         JDBC.connect();
         
         stmt = JDBC.connection.createStatement();
         printAuthors(stmt);
         printTableAuthorisbn(stmt);
         printTableTitles(stmt);
         printTablePublishers(stmt);
        
      } catch(SQLException se) {
          // Handle errors for JDBC
          se.printStackTrace();
       } finally {
          // Finally block, used to close resources
          JDBC.close();
       }
    }
  public static void printAuthors(Statement stmt)throws SQLException
  {
   String exampleQuery1 = "SELECT * FROM Authors";
   System.out.println("Authors:");
   ResultSet rs1 = stmt.executeQuery(exampleQuery1);
    while (rs1.next()) {
     int id = rs1.getInt("authorID");
      String firstName = rs1.getString("firstName");
      String lastName = rs1.getString("lastName");
      System.out.println(id + "\t" + firstName + "\t" + lastName);
    }
  }
  public static void printTableAuthorisbn(Statement stmt) throws SQLException
  {
       String query = "SELECT * FROM authorisbn";
       System.out.println("authorisbn:");
       ResultSet rs1 = stmt.executeQuery(query);
        while (rs1.next()) 
          System.out.println(rs1.getInt("authorID") + "\t" + rs1.getString("isbn"));
  }

  public static void printTableTitles(Statement stmt) throws SQLException
  {
       String query = "SELECT * FROM titles";
       System.out.println("titles:");
       ResultSet rs1 = stmt.executeQuery(query);
       while (rs1.next()) 
          System.out.println(rs1.getString("isbn") + "\t" 
                              + rs1.getString("title") + "\t"
                              + rs1.getInt("editionNumber") + "\t"
                               + rs1.getString("year") + "\t"
                               + rs1.getInt("publisherID") + "\t"
                               + rs1.getDouble("price"));
  }

  public static void printTablePublishers(Statement stmt) throws SQLException
  {
       String query = "SELECT * FROM publishers";
       System.out.println("publishers:");
       ResultSet rs1 = stmt.executeQuery(query);
       while (rs1.next()) 
          System.out.println(rs1.getInt("publisherID") + "\t" 
                              + rs1.getString("publisherName"));
  }
}
  
  
