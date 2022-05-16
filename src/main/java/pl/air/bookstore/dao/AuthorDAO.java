package pl.air.bookstore.dao;

import java.util.List;

import pl.air.bookstore.model.*;

public interface AuthorDAO {

	// CRUD -- create, read, update, delete

	// create
	Long save(Author object);
	
	// read
	Author findById(Long id);
	Author findByName(String firstName, String lastName);
	List<Author> findAll();

	// update
	void update(Author object);
	
	// delete
	void delete(Author object);

}
