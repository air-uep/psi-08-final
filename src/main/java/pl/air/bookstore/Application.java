package pl.air.bookstore;

import pl.air.bookstore.dao.AuthorDAO;
import pl.air.bookstore.dao.BookDAO;
import pl.air.bookstore.dao.PublisherDAO;
import pl.air.bookstore.dao.hibernate.AuthorDAOImpl;
import pl.air.bookstore.dao.hibernate.BookDAOImpl;
import pl.air.bookstore.dao.hibernate.PublisherDAOImpl;
import pl.air.bookstore.model.Author;
import pl.air.bookstore.model.Book;
import pl.air.bookstore.model.Publisher;
import pl.air.bookstore.service.DataService;
import pl.air.bookstore.service.HibernateService;
import pl.air.bookstore.service.PrintService;

import java.util.List;
import java.util.logging.Level;

public class Application {

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        System.out.println("---------- Start ----------\n");
        // uruchom Hibernate
        HibernateService.startup();

        // wstaw dane do tabel w bazie danych jeżeli paramter "hbm2ddl.auto" ma wartość "create" (plik: hibernate.cfg.xml)
        if (HibernateService.isCreate()) {
            DataService.insertInitData();
        }

        // dao
        AuthorDAO authorDAO = new AuthorDAOImpl();
        PublisherDAO pubDAO = new PublisherDAOImpl();
        BookDAO bookDAO = new BookDAOImpl();

        // ******************************************************************
        // początek transakcji
        HibernateService.beginTransaction();

        // pobierz z bazy danych wszystkich autorów
        List<Author> allAuthors = authorDAO.findAll();
        System.out.println("Lista wszystkich autorów");
        System.out.println("------------------------");
        PrintService.printAuthorList(allAuthors);
        System.out.println();

        // pobierz z bazy danych wydawnictwo o id = 1
        Publisher pub1 = pubDAO.findById(1L);
        System.out.println("Wydawnictwo, którego id = 1");
        System.out.println("---------------------------");
        PrintService.printPublisher(pub1);
        System.out.println();

        // koniec transakcji
        HibernateService.commitTransaction();


        // ******************************************************************
        // początek transakcji
        HibernateService.beginTransaction();

        // pobierz z bazy danych wszystkie książki, których autorem jest Adam Mickiewicz
        //      1. utwórz obiekt "fredro" na podstawie danych pobranych z bazy danych
        Author fredro = authorDAO.findByName("Aleksander", "Fredro");
        //      2. pobierz z bazy danych wszystkie książki, których autorem jest obiekt "fredro"
        List<Book> mickiewBooks = bookDAO.findAllByAuthor(fredro);
        System.out.println("Informacja o książkach, których autorem jest Aleksander Fredro");
        System.out.println("--------------------------------------------------------------");
        PrintService.printBookList(mickiewBooks);

        // koniec transakcji
        HibernateService.commitTransaction();


        // zakończ Hibernate
        HibernateService.shutdown();

        System.out.println("\n---------- Koniec ----------");
    }
}
