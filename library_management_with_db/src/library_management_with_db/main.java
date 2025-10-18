package library_management_with_db;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws SQLException {
		
		 //java regular expressions (regex)
		//giving some color code 
		 final String RED="\u001B[31m";
		 
		 //reset the codes to normal color
		 final String RESET="\u001B[0m";
		 
		 
		 System.out.println("Welcome to Book Management Application\n");
		 
		 Scanner sc=new Scanner(System.in);
		 
		 validator valid=new validator();
		 
		 service_implement service = new  service_implement();
		 boolean bol=true;
		 
		 //why do? bcz to start the loop first without any considerations
		 do {
			 System.out.println("\n1.Add Book\n"+
			           "2.Show All Books\n"+
			           "3.Show Available Books\n"+
			           "4.Borrow Book\n"+
			           "5.Return Book\n"+
			           "6.Exit\n");

			 System.out.print("Enter Your Choice ! : ");
			 int ch = sc.nextInt();
			 
			 switch(ch) {
			 
			 
			 case 1:
				    sc.nextLine(); // consume leftover newline from previous input

			    System.out.print("Enter title: ");
			    String title = sc.nextLine(); // read full line, allows spaces

			    System.out.print("Enter author: ");
			    String author = sc.nextLine(); // read full line

			    System.out.print("Enter the year: ");
			    int year = sc.nextInt();

			    //System.out.print("Enter status (true/false): ");
			    boolean status = true;
			    
			   if( (valid.validateAuthorTitle(title, author) == true)){
			    	
			    	// Call the method without id, because SQL auto-generates it
				    service.add_book(title, author, year, status);
				    			    	
			    }
			   else {
				   System.out.println(RED+"Please Enter Valid Author name , Title "+ RESET);
			   }
			    bol=service.domore();
			    break;
			    
			 case 2:
				 service.show_allBooks();
				 bol=service.domore();
				 break;
				 
			 case 3:
				 service.show_avilablebooks();
				 bol=service.domore();
				 break;
				 
			 case 4:
				 System.out.println("Enter the id of the book : ");
				 int id=sc.nextInt();
				 service.borrow_book(id);
				 bol=service.domore();
				 break;
				 
			 case 5:
				 System.out.println("Enter the id of the book : ");
				 int id1=sc.nextInt();
				 service.return_book(id1);
				 bol=service.domore();
				 break;
				 
			 case 6:
				 System.out.println();
				 bol=false;
				 break;
				 
			default :
				 System.out.println("Invalid entry");
				 bol=false;
			 }}
		
while(bol);
		 }}




