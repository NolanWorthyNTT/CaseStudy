package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.BookDAO;
import dao.BookDAOImpl;

class CreateBookTest {
	BookDAO dao = new BookDAOImpl();

	@Test
	void CreateBookWithEmptyBookNameTest() {
		int result = dao.createBook("", "test author", (short)1);
		assertEquals(1, result);
	}
	
	@Test
	void CreateBookWithEmptyAuthorNameTest() {
		int result = dao.createBook("test book", "", (short)1);
		assertEquals(1, result);
	}
	
	@Test
	void CreateBookWithEditionZeroTest() {
		int result = dao.createBook("test book", "test author", (short)0);
		assertEquals(-1, result);
	}
	
	@Test
	void CreateBookWithEditionNegativeTest() {
		int result = dao.createBook("test book", "test author", (short)-1);
		assertEquals(-1, result);
	}
	
	@Test
	void CreateBookWithEditionLargeTest() {
		int result = dao.createBook("test book", "test author", (short)1000000000);
		assertEquals(-1, result);
	}

}
