package com.ig.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

import javax.management.RuntimeErrorException;

public class TrainSeatBooking 
{
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost/ingenuity";
	private String dbuser = "root";
	private String dbpass = "1234";
	
	private String customerId = "C1234";
	private String bookingId = "B101";
	private String trainId = "12345";
	
	Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName(driver);
			
			//System.out.println("OS:-"+com.mysql.cj.jdbc.Driver.getOSName());
			//System.out.println("Platform:-"+com.mysql.cj.jdbc.Driver.getPlatform());
			
			con = DriverManager.getConnection(dbUrl,dbuser,dbpass);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	
	boolean LockSeat(Connection con)
	{
		boolean result = false;
		String sqlQuery1 = "update trainSeatAvailability set available_seats = available_seats - 1"
				+ " where train_id = ? and"
				+ " journey_date = ? and"
				+ " class = ? and"
				+ " available_seats > 0 ";
		
		try
		{
			PreparedStatement pstmt =  con.prepareStatement(sqlQuery1);
			pstmt.setString(1, trainId);
			pstmt.setString(2, "2024-10-10");
			pstmt.setString(3, "Sleeper");
			
			int rows = pstmt.executeUpdate();
			
			if(rows == 0)
			{
				throw new RuntimeException("Seat not locked!");
			}
			
			System.out.println("Seat Locked!!!");
			result = true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	boolean BookingDetails(Connection con)
	{
		boolean result = false;
		String sqlQuery2 = "insert into bookingdetails values(?,?,?,?,?)";
		try
		{
			PreparedStatement pstmt2 = con.prepareStatement(sqlQuery2);
			pstmt2.setString(1,bookingId);
			pstmt2.setString(2,trainId);
			pstmt2.setString(3,customerId);
			pstmt2.setInt(4,1);
			pstmt2.setString(5,"Payment_Pending");
			
			int rows2 = pstmt2.executeUpdate();
			
			if(rows2==0)
			{
				throw new RuntimeException("Booking Failed!");
			}
			System.out.println("Booking Success\nWaiting for Payment Confirmation!!!");
			result = true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	boolean GetPaymentStatus(Connection con)
	{
		boolean result = false;
		try 
		{
			String sqlQuery3 = "select payment_status from customerpayment where customer_id = ?";
			PreparedStatement pstmt3 = con.prepareStatement(sqlQuery3);
			pstmt3.setString(1, customerId);
			ResultSet rs =  pstmt3.executeQuery();
			
			if(rs.next() && rs.getString(1).equalsIgnoreCase("Success"))
			{
				result = true;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	boolean UpdateBookingDetails(Connection con)
	{
		boolean result = false;
		String sqlQuery4 = "update bookingdetails set status = ? where customer_id = ?";
		try 
		{
			PreparedStatement pstmt4 = con.prepareStatement(sqlQuery4);
			pstmt4.setString(1, "Success");
			pstmt4.setString(2, customerId);
			
			int updatedRows = pstmt4.executeUpdate();

			if(updatedRows == 0)
			{
				throw new RuntimeException("Transaction Not Success!");
			}
			
			System.out.println("Booking Updated:-\nPayment_Pending--->Success");
			result = true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	void ticketBooking()
	{
		System.out.println("Implementing Transaction Management:-");
		
		try
		{
			Connection con = getConnection();
			con.setAutoCommit(false);
			System.out.println("is setAutoCommit mode on ===> "+con.getAutoCommit()+"\n");
			
			boolean isSeatLocked = LockSeat(con);
			Savepoint onSeatLocked = con.setSavepoint();
			
			if(!isSeatLocked)
			{
				con.rollback(onSeatLocked);
				System.out.println("Program Terminate:- Reason : SEAT NOT LOCKED");
				return;
			}
			
			boolean isSeatBooked = BookingDetails(con);
			Savepoint onSeatBooked = con.setSavepoint();
			
			if(!isSeatBooked)
			{
				con.rollback(onSeatBooked);
				System.out.println("Program Terminate:- Reason : SEAT NOT BOOKED");
				return;
			}
			
			boolean isPaymentDone = GetPaymentStatus(con);
			if(!isPaymentDone)
			{
				con.rollback(onSeatBooked);
				System.out.println("Program Terminate:- Reason : PAYMENT NOT DONE");
				return;
			}
			
			boolean isSeatBookedUpdated = UpdateBookingDetails(con);
			Savepoint onSeatBookedUpdated = con.setSavepoint();
			if(!isSeatBookedUpdated)
			{
				con.rollback(onSeatBookedUpdated);
				System.out.println("Program Terminate:- Reason : SEAT BOOK UPDATED");
				return;
			}
			System.out.println("Program Terminate:- Reason : SEAT BOOKED("+bookingId+")");
			con.commit();
			System.out.println("All the savepoints are released");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) 
	{
		TrainSeatBooking p5 = new TrainSeatBooking();
		p5.ticketBooking();
	}
	
}
