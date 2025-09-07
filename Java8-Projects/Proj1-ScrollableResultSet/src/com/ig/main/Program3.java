package com.ig.main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Program3 
{
	private String dbUrl = "jdbc:mysql://localhost/ingenuity";
	private String dbuser = "root";
	private String dbpass = "1234";
	
	Scanner sc = new Scanner(System.in);
	
	void meth1()
	{
		try
		{
			Connection con = DriverManager.getConnection(dbUrl,dbuser,dbpass);
			CallableStatement cstmt =  con.prepareCall("{call InsertEmpDetails(?,?,?,?,?)}");
			
			System.out.println("Enter Employee Id");
			String e_id = sc.nextLine();
			
			System.out.println("Enter Employee Name");
			String e_name = sc.nextLine();
			
			System.out.println("Enter Employee Desg");
			String e_desg = sc.nextLine();
			
			System.out.println("Enter Employee Basic Salary");
			int e_bsal = Integer.parseInt(sc.nextLine());
			
			System.out.println("Enter Employee Total Salary");
			int e_tsal = Integer.parseInt(sc.nextLine());
			
			cstmt.setObject(1, e_id);
			cstmt.setObject(2, e_name);
			cstmt.setObject(3, e_desg);
			cstmt.setObject(4, e_bsal);
			cstmt.setObject(5, e_tsal);
			
			cstmt.execute();
			
			System.out.println("Data updated.");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		Program3 p3 = new Program3();
		p3.meth1();
	}

}
