package app.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "categories")
@Setter
@Getter
@NoArgsConstructor

public class Category {
	@Id
	@Column (name= "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="category")
    private Set<Book> book;

	@Column (name= "created_at")
	private LocalDateTime createDateTime;
	
	@Column (name= "updated_at")
	private LocalDateTime updateDateTime;
	
	@Column (name= "name")
	private String name;

}