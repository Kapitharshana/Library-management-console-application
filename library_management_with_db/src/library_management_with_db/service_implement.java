package library_management_with_db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class service_implement implements Services{
	 public static final String RED="\u001B[31m";
	    public static final String RESET="\u001B[0m";//back everything to normal color
	    public static final String BLUE="\u001B[34m";
	    public static final String GREEN="\u001B[32m";
	    public static final String CYAN="\u001B[36m";
	    public static final String BLACK="\u001B[30m";
	    
	    Scanner sc=new Scanner(System.in);
	    
	   	    
	    
    @Override
	public void add_book(String title, String author, int year, boolean status) throws SQLException {
		String query= "INSERT INTO books (Title, Author, Published_year, Status) VALUES (?, ?, ?, ?)";

	    Connection con = dbconnection.getConnection();
	    PreparedStatement pst=con.prepareStatement(query);
	    pst.setString(1,title);
	    pst.setString(2, author);
	    pst.setInt(3, year);
	    pst.setBoolean(4,status);
	    
	    pst.executeUpdate();
	    System.out.println(GREEN+ "Book added successfully" + RESET);
	    
		
	}

	@Override
	public void show_allBooks() throws SQLException {
		String query="Select * from books";
		Connection con = dbconnection.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		while(rs.next()) {
			
			System.out.println(BLUE+ "ID : "+ RESET + rs.getInt(1) + BLUE+
					" |     TITLE :  "+ RESET + rs.getString(2)  +  
					BLUE+ " |     AUTHOR : "+ RESET + rs.getString(3) +   
					BLUE+ " |     YEAR : "+ RESET + rs.getInt(4)+    
					BLUE+ " |     STATUS : "+ RESET + rs.getBoolean(5)  );
			
			System.out.println("");
			
		}
		
		
	}

	@Override
	public void show_avilablebooks() throws SQLException {
		String query="select * from books where Status=1  ";
		Connection con = dbconnection.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		while(rs.next()) {
			
			System.out.println(BLUE+ "ID : "+ RESET + rs.getInt(1) + BLUE+
					" |     TITLE :  "+ RESET + rs.getString(2)  +  
					BLUE+ " |     AUTHOR : "+ RESET + rs.getString(3) +   
					BLUE+ " |     YEAR : "+ RESET + rs.getInt(4)+    
					BLUE+ " |     STATUS : "+ RESET + rs.getBoolean(5)  );
			
			System.out.println("");
			
		}
		
	}

	//borrow a book
	
	@Override
	public void borrow_book(int id) throws SQLException {
		//if the id's available is zero then they can't borrow the book. if the book id is none of the table then they can't borrow.
		
		if((bookExists(id)==true)&& check(id)==true) {
		
		String query="Update books set Status=0 where book_id=?";
		Connection con = dbconnection.getConnection();
	    PreparedStatement pst=con.prepareStatement(query);
	    pst.setInt(1,id);
	   
	    pst.executeUpdate();
	    System.out.println(BLUE+ "Book borrowed successfully" + RESET);
		}
		
		else {
			System.out.println(RED + "Book is not avilable" + RESET);
		}
		
		
	}

	
	//return the borrowed work
	@Override
	public void return_book(int id) throws SQLException {
		if((bookExists(id)==true)&& check(id)==false) {
			
		
		String query="Update books set Status=1 where book_id=?";
		Connection con = dbconnection.getConnection();
	    PreparedStatement pst=con.prepareStatement(query);
	    pst.setInt(1,id);
	   
	    pst.executeUpdate();
	    System.out.println(CYAN+ "Book returned successfully" + RESET);
	    
		}
		else {
			System.out.println(RED + "No need to return" + RESET);
		}
	}
	
	
     //do the user need to do more work?
	 public boolean domore() {
	    	Scanner sc=new Scanner (System.in);
	    	System.out.print(BLACK +"\nDo you want to do any work?(for yes enter 'true', for no enter 'false': " + RESET);
			boolean ans=sc.nextBoolean();
			if(ans==false) {
				System.out.println("Thank you \n" );
				
			}
			
			return ans;
	    }
	 
	 //check whether the book is already in the books table no matter it it's status is zero or one
	/* public boolean all_bookid(int id) throws SQLException {
		 String query ="select book_id from books";
		 Connection con=dbconnection.getConnection();
		 Statement st=con.createStatement();
		 ResultSet rs=st.executeQuery(query);
		 
		boolean non=false;
		 while(rs.next()) {
			 
			 int bid= rs.getInt(1);
			 if(bid == id) {
				 non = true;
				 break;
				 
			 }
			 		 
		 }
		 return non;
		 		 				 
	 }*/
	 // this is the shorten version of the previous code
	 public boolean bookExists(int id) throws SQLException {
		    String query = "SELECT 1 FROM books WHERE book_id = ?";
		    Connection con = dbconnection.getConnection();
		    PreparedStatement pst = con.prepareStatement(query);
		    pst.setInt(1, id);
		    ResultSet rs = pst.executeQuery();

		    boolean exists = rs.next(); // true if a row exists ,false if a row didn't exist

		    return exists;
		}

	 
	 //check availability of the given book id ;
	   
	 public boolean check(int id) throws SQLException {
		    String query = "SELECT status FROM books WHERE book_id = ?";
		    Connection con = dbconnection.getConnection();
		    PreparedStatement pst = con.prepareStatement(query);
		    pst.setInt(1, id);
		    ResultSet rs = pst.executeQuery();

		    boolean available = false;

		    if (rs.next()) {
		        int status = rs.getInt("status");  // read 0 or 1
		        available = (status == 1);         // convert 1 → true, 0 → false
		    }

		    return available;
		}
  
}
