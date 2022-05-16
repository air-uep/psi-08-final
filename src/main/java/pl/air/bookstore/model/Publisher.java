package pl.air.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "publishers")
@NoArgsConstructor @Getter @Setter
public class Publisher {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pub_id")
	private Long id;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String location;

}
