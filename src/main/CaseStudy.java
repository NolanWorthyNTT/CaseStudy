package main;

import dao.BookDAO;
import dao.BookDAOImpl;

public class CaseStudy {
	public static void main(String[] args) {
		BookDAO dao = new BookDAOImpl();
		
		System.out.println("Books:");
		System.out.println(dao.getBooks());
		
		System.out.println("\nInserting book...");
		dao.createBook("Nolan's Book", "Nolan Worthy", (short)1);
		
		System.out.println("Book by ISBN:");
		System.out.println(dao.getBook(1));
		
		System.out.println("\nUpdating book edition...");
		dao.updateBookEdition((long)1, (short)2);
		
		System.out.println("Books:");
		System.out.println(dao.getBooks());
		
		System.out.println("\nDeleting book...");
		dao.deleteBook((long)1);
		
		System.out.println("Books:");
		System.out.println(dao.getBooks());
	}
}
