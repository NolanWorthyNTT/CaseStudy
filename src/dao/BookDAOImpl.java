package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.BookEditionZeroOrLessException;
import model.Book;

public class BookDAOImpl implements BookDAO {
	private final String url = "jdbc:mysql://localhost:3306/casestudy";
	private final String user = "root";
	private final String pass = ""; // password removed for security
	
	private Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}
	
	
	
	// insert book into DB with given book name, author name, and edition. return void.
	@Override
	public int createBook(String bookName, String authorName, short edition) {
		int result = -1;
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Books (BookName, AuthorName, Edition) VALUES (?, ?, ?)")) {
			if(edition <= 0) {
				throw new BookEditionZeroOrLessException("Edition is " + edition);
			}
			
			pStmt.setString(1, bookName);
			pStmt.setString(2, authorName);
			pStmt.setShort(3, edition);
			
			result = pStmt.executeUpdate();
			
		} catch(BookEditionZeroOrLessException e) {
			System.out.println("Book edition invalid: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error creating book: " + e.getMessage());
		}
		return result;
	}
	
	

	// select all books in db. return list of book objects.
	@Override
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement()) {
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Books");
			
			while(rs.next()) {
				books.add(new Book(rs.getLong("ISBN"),
									rs.getString("BookName"),
									rs.getString("AuthorName"),
									rs.getShort("Edition")));
			}
		} catch (SQLException e) {
			System.out.println("Error retrieving books: " + e.getMessage());
		}
		
		return books;
	}
	
	

	// select book from db with given isbn. return book object.
	@Override
	public Book getBook(long isbn) {
		Book book = new Book();
		
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement()) {
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Books WHERE ISBN = " + isbn);
			
			if(rs.next()) {
				book = new Book(rs.getLong("ISBN"),
									rs.getString("BookName"),
									rs.getString("AuthorName"),
									rs.getShort("Edition"));
			}
		} catch (SQLException e) {
			System.out.println("Error retrieving book: " + e.getMessage());
		}
		
		return book;
	}
	
	
	
	// update edition of book in db with given isbn. return void.
	@Override
	public void updateBookEdition(long isbn, short edition) {
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("UPDATE Books SET Edition = ? WHERE ISBN = ?")) {
			
			pStmt.setShort(1, edition);
			pStmt.setLong(2, isbn);
			
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error updating book: " + e.getMessage());
		}
	}
	
	
	
	// delete book from db with given isbn. return void.
	@Override
	public void deleteBook(long isbn) {
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement()) {
			
			stmt.executeUpdate("DELETE FROM Books WHERE ISBN = " + isbn);
			
		} catch (SQLException e) {
			System.out.println("Error deleting book: " + e.getMessage());
		}
	}

}
