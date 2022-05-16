package pl.air.bookstore.dao;

import java.util.List;

import pl.air.bookstore.model.*;

public interface BookDAO {
	
	// create
	Long save(Book object);
	
	// read
	Book findById(Long id);
	List<Book> findAll();
	List<Book> findAllByAuthor(Author author);

	long countByAuthor(Author author);
	long countByPublisher(Publisher publisher);
	
	// update
	void update(Book object);
	
	// delete
	void delete(Book object);
	void deleteAllByAuthor(Author author);
	void deleteAllByPublisher(Publisher publisher);

}
