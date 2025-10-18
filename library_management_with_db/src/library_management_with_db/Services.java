package library_management_with_db;

import java.sql.SQLException;

public interface Services {
	public void add_book(String title,String author,int year,boolean ststus) throws SQLException;
	
	public void show_allBooks() throws SQLException;
	
	public void show_avilablebooks() throws SQLException;
	
	public void borrow_book(int id) throws SQLException;
	
	public void return_book(int id) throws SQLException;

}
