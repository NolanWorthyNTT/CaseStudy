package dao;

import java.util.List;

import model.Book;

public interface BookDAO {
	public int createBook(String bookName, String authorName, short edition);
	public List<Book>getBooks();
	public Book getBook(long isbn);
	public void updateBookEdition(long isbn, short edition);
	public void deleteBook(long isbn);
}
