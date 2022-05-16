package pl.air.bookstore.service;

import java.math.BigDecimal;
import java.util.List;

import pl.air.bookstore.model.Author;
import pl.air.bookstore.model.Book;
import pl.air.bookstore.model.Publisher;

public class PrintService {

	/* autor */
	private static String getAuthor(Author one) {
		Long id = one.getId();
		String firstName = one.getFirstName();
		String lastName = one.getLastName();
		
		return String.format("%s %s (id = %d)", firstName, lastName, id);
	}

	public static void printAuthor(Author one) {
		String printout = getAuthor(one);
		System.out.println(printout);
	}

	public static void printAuthorList(List<Author> list) {
		String printout = "";
		for (Author one : list) {
			printout += getAuthor(one) + ", ";
		}
		printout = printout.substring(0, printout.lastIndexOf(","));
		System.out.println(printout);
	}

	
	/* książka */
	public static void printBook(Book one) {
		Long id = one.getId();
		String title = one.getTitle();
		Integer year = one.getPubYear();
		BigDecimal price = one.getPrice();
		Publisher pub = one.getPublisher();
		List<Author> authors = one.getAuthors();

		// tytuł + id
		System.out.println(title + " (id = " + id + ")");

		// autorzy
		if (authors.size() == 1) {
			System.out.print(" - autor: ");
		}
		else {
			System.out.print(" - autorzy: ");
		}
		printAuthorList(authors);

		// wydawnictwo
		System.out.print(" - wydawnictwo: ");
		printPublisher(pub);

		// rok wydania
		System.out.println(" - rok wydania: " + (year != null ? year : "---"));

		// cena
		System.out.println(" - cena: " + (price != null ? price : "---") + " zł");
	}

	public static void printBookList(List<Book> list) {
		for (Book one : list) {
			printBook(one);
		}
	}

	
	/* wydawnictwo */
	private static String getPublisher(Publisher one) {
		Long id = one.getId();
		String name = one.getName();
		String location = one.getLocation();
		
		return String.format("%s (id = %d), %s", name, id, location);
	}
	
	public static void printPublisher(Publisher one) {
		String printout = getPublisher(one);
		System.out.println(printout);
	}

}
