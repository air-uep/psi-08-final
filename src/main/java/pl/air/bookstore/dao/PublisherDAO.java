package pl.air.bookstore.dao;

import java.util.List;

import pl.air.bookstore.model.*;

public interface PublisherDAO {
	
	// create
	Long save(Publisher object);
	
	// read
	Publisher findById(Long id);
	List<Publisher> findAll();
	
	// update
	void update(Publisher object);
	
	// delete
	void delete(Publisher object);

}
