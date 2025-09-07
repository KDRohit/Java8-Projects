package com.ig.main;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Program4 
{
	private String dbUrl = "jdbc:mysql://localhost/ingenuity";
	private String dbuser = "root";
	private String dbpass = "1234";
	
	private Scanner sc = new Scanner(System.in);
	
	void meth1()
	{
		try 
		{
			Connection con =  DriverManager.getConnection(dbUrl,dbuser,dbpass);
			CallableStatement cstmt =  con.prepareCall("{call InsertEmployee(?,?,?,?,?)}");
			
			System.out.println("Enter employee id:-");
			String e_id = sc.nextLine();
			
			System.out.println("Enter employee name:-");
			String e_name = sc.nextLine();
			
			System.out.println("Enter employee designation:-");
			String e_desg = sc.nextLine();
			
			System.out.println("Enter employee salary(int):-");
			int e_sal = Integer.parseInt(sc.nextLine());
			
			double e_tsal = e_sal*2.0f + 0.01*3600 + 2500;
			
			cstmt.setString(1, e_id);
			cstmt.setString(2, e_name);
			cstmt.setString(3, e_desg);
			cstmt.setInt(4, e_sal);
			cstmt.setDouble(5, e_tsal);
			
			boolean result =  cstmt.execute();
			
			System.out.println("data updated successfully");
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void meth2()
	{
		try
		{
			Connection con = DriverManager.getConnection(dbUrl,dbuser,dbpass);
			CallableStatement cstmt =  con.prepareCall("call findSalAndTotSal(?,?,?)");
			System.out.println("Enter employee id (for salary):-");
			String eid = sc.nextLine();
			cstmt.setString(1,eid);
			cstmt.registerOutParameter(2, java.sql.Types.DECIMAL);
			cstmt.registerOutParameter(3, java.sql.Types.DECIMAL);
			cstmt.execute();
			
			BigDecimal bsalary = cstmt.getBigDecimal(2);
			BigDecimal tsalary = cstmt.getBigDecimal(3);
			System.out.println("Employee basic Salary: " + bsalary);
			System.out.println("Employee total Salary: " + tsalary);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void meth3()
	{
		try
		{
			System.out.println("Implementing Callable Statements(In,Out):-");
			Connection con = DriverManager.getConnection(dbUrl,dbuser,dbpass);
			CallableStatement cstmt =  con.prepareCall("{call RetrieveEmpData(?,?,?,?,?)}");
			
			System.out.println("Enter emp id:-");
			String eid = sc.nextLine();
			
			cstmt.setString(1, eid);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.NUMERIC);
			cstmt.registerOutParameter(5, Types.FLOAT);
			
			cstmt.execute();
			
			System.out.println("\nEmployee Details:-");
			System.out.println("Emp Id:-"+eid);
			System.out.println("Emp Name:-"+cstmt.getString(2));
			System.out.println("Emp Desg:-"+cstmt.getString(3));
			System.out.println("Emp Sal:-"+cstmt.getInt(4));
			System.out.println("Emp TSal:-"+cstmt.getFloat(5));
			
			System.out.println("Data Retrieved");
			
		}
		catch(Exception e)
		{
			
		}
		
		
	}

	public void meth4()
	{
		System.out.println("Implementing Functions");
		try
		{
			Connection con = DriverManager.getConnection(dbUrl,dbuser,dbpass);
			PreparedStatement ps = con.prepareStatement("SELECT RetrieveTotalSal(?)");
			System.out.println("Enter emp id:-");
			String e_id = sc.nextLine();

			ps.setString(1, e_id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
			    float totalSalary = rs.getFloat(1);
			    System.out.println("Employee Details:- ");
			    System.out.println("Emp Id:- " + e_id);
			    System.out.println("Emp Total Salary:- " + totalSalary);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args)
	{
		Program4 p =  new Program4();
		//p.meth1();
		//p.meth2();
		//p.meth3();
		//p.meth4();
	}
	
}
