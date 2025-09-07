package com.ig.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Program1 
{
	private String dbUrl = "jdbc:mysql://localhost/ingenuity";
	private String dbuser = "root";
	private String dbpass = "1234";

	private Scanner sc = new Scanner(System.in);
	
	private Connection getConnection()
	{
		Connection con = null;
		try
		{
			con = DriverManager.getConnection(dbUrl,dbuser,dbpass);
			System.out.println("Connected to Database!");
			return con;
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return con;
	}
	
	private void ShowInfoAboutResultSet()
	{
		System.out.println("ResultSet.TYPE_FORWARD_ONLY = "+ResultSet.TYPE_FORWARD_ONLY);
		System.out.println("ResultSet.TYPE_SCROLL_INSENSITIVE = "+ResultSet.TYPE_SCROLL_INSENSITIVE);
		System.out.println("ResultSet.TYPE_SCROLL_SENSITIVE = "+ResultSet.TYPE_SCROLL_SENSITIVE);

		System.out.println("ResultSet.CONCUR_READ_ONLY = "+ResultSet.CONCUR_READ_ONLY);
		System.out.println("ResultSet.CONCUR_UPDATABLE = "+ResultSet.CONCUR_UPDATABLE);
	}
	
	private void RetriveData()
	{
		Connection con = getConnection();
		try
		{
			int resultSetType = 1005;
			int resultSetConcurrency = 1008;
			String tableName = "developers";
			Statement stmt =  con.createStatement(resultSetType,resultSetConcurrency);
			ResultSet rs =  stmt.executeQuery("select * from "+tableName);
			System.out.println("waiting for type something...");
			String s = sc.nextLine();
			System.out.println(s);
			int count=0;
			while(rs.next())
			{
				if(++count==1)
				{
				    //rs.updateObject("description", "line 1 changed");
				    //rs.updateRow();
				}
				rs.refreshRow();
				int cols = rs.getMetaData().getColumnCount();
				for(int i=1;i<=cols;i++)
				{
					System.out.print(rs.getObject(i));
					System.out.print(" ");
				}
				System.out.println();
			}
			
			System.out.println("ResultSet type: " + resultSetType);
			System.out.println("ResultSet concurrency: " +resultSetConcurrency);
			System.out.println("ResultSet type (Driver Updated) : " + rs.getType());
			System.out.println("ResultSet concurrency (Driver Updated) : " + rs.getConcurrency());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		Program1 p1 = new Program1();
		p1.ShowInfoAboutResultSet();
		p1.RetriveData();
	}
}
