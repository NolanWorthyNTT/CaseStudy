package model;

public class Book {
	private long isbn; // type long because real ISBNs are up to 13 digits
	private String bookName;
	private String authorName;
	private short edition; // type short because edition number will be low - saves space
	
	public Book() {
		super();
		this.isbn = 0;
		this.bookName = "";
		this.authorName = "";
		this.edition = 0;
	}
	
	public Book(long isbn, String bookName, String authorName, short edition) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
		this.authorName = authorName;
		this.edition = edition;
	}
	
	public long getIsbn() {
		return isbn;
	}
	
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public short getEdition() {
		return edition;
	}
	
	public void setEdition(short edition) {
		this.edition = edition;
	}
	
	@Override
	public String toString() {
		return "ISBN: " + isbn + ", Book: " + bookName + ", Author: " + authorName + ", Edition: " + edition;
	}
}
