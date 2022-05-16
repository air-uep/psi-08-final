package pl.air.bookstore.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@NoArgsConstructor @Getter @Setter
public class Book {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long id;

	@Column(length = 1000, nullable = false)
	private String title;
	
	@Column(name = "pub_year")
	private Integer pubYear;
	
	@Column(precision = 7, scale = 2)
	private BigDecimal price;
	
	@ManyToMany
	@JoinTable(
			name = "author_book",
			joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "author_id")
	)
	private List<Author> authors = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "pub_id")
	private Publisher publisher;

}
