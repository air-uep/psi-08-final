package pl.air.bookstore.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import pl.air.bookstore.dao.*;
import pl.air.bookstore.dao.hibernate.*;
import pl.air.bookstore.model.*;

public class DataService {

	public static void insertInitData() {
		
		// utwórz autorów
		Author fredro = createAuthor("Aleksander", "Fredro");
		Author mickiewicz = createAuthor("Adam", "Mickiewicz");
		Author slowacki = createAuthor("Juliusz", "Słowacki");

		Author wrycza = createAuthor("Stanisław", "Wrycza");
		Author marcinkowski = createAuthor("Bolesław", "Marcinkowski");
		Author wyrzykowski = createAuthor("Karol", "Wyrzykowski");
		Author maslan = createAuthor("Jacek", "Maślankowski");

		// utwórz wydawnictwa
		Publisher nk = createPublisher("Nasza Księgarnia", "Warszawa");
		Publisher wsip = createPublisher("Wydawnictwa Szkolne i Pedagogiczne", "Warszawa");
		Publisher helion = createPublisher("Helion", "Gliwice");
		Publisher pwe = createPublisher("Polskie Wydawnictwo Ekonomiczne", "Warszawa");
		Publisher pwn = createPublisher("Wydawnictwo Naukowe PWN", "Warszawa");
		Publisher znak = createPublisher("Znak", "Kraków");

		// utwórz książki
		Book zemsta = createBook("Zemsta", 2012, 19.50, nk, fredro);
		Book sluby = createBook("Śluby panieńskie", 2009, 13.00, znak, fredro);
		Book panTadeusz = createBook("Pan Tadeusz", 2014, 39.90, wsip, mickiewicz);
		
		Book uml = createBook("Język UML 2.0 w modelowaniu systemów informatycznych", 2006, 28.40, helion, wrycza, marcinkowski, wyrzykowski);
		Book ie = createBook("Informatyka ekonomiczna. Teoaria i zastosowania", 2019, 21.30, pwn, wrycza, maslan);
		
		// zapisz dane w bazie danych
		HibernateService.beginTransaction();

		AuthorDAO authorDAO = new AuthorDAOImpl();
		authorDAO.save(fredro);
		authorDAO.save(mickiewicz);
		authorDAO.save(slowacki);
		authorDAO.save(wrycza);
		authorDAO.save(marcinkowski);
		authorDAO.save(wyrzykowski);
		authorDAO.save(maslan);

		PublisherDAO pubDAO = new PublisherDAOImpl();
		pubDAO.save(nk);
		pubDAO.save(wsip);
		pubDAO.save(helion);
		pubDAO.save(pwe);
		pubDAO.save(pwn);
		pubDAO.save(znak);

		BookDAO bookDAO = new BookDAOImpl();
		bookDAO.save(zemsta);
		bookDAO.save(sluby);
		bookDAO.save(panTadeusz);
		bookDAO.save(uml);
		bookDAO.save(ie);

		HibernateService.commitTransaction();
	}

	private static Author createAuthor(String firstName, String lastName) {
		Author one = new Author();
		one.setFirstName(firstName);
		one.setLastName(lastName);
		return one;
	}

	private static Publisher createPublisher(String name, String location) {
		Publisher one = new Publisher();
		one.setName(name);
		one.setLocation(location);
		return one;
	}
	
	private static Book createBook(String title, Integer pubYear, double price, Publisher publisher, Author... authors) {
		Book book = new Book();
		book.setTitle(title);
		book.setPubYear(pubYear);
		book.setPrice(new BigDecimal(price));
		book.setAuthors(List.of(authors));
		book.setPublisher(publisher);
		return book;
	}

}
