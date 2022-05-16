package pl.air.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authors")
@NoArgsConstructor @Getter @Setter
public class Author {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_id")
	private Long id;

	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;

}
