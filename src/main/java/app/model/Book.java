package app.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "books")
@Setter
@Getter
@NoArgsConstructor

public class Book {
	@Id
	@Column (name= "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column (name= "user_id")
	private int user_id;
	
	@Column(name="category_id")
	private int category_id;
	
	@Column(name= "review_id")
	private int review_id;
	
	@Column (name= "created_at")
	private LocalDateTime createDateTime;
	
	@Column (name= "updated_at")
	private LocalDateTime updateDateTime;
	
	@Column (name= "title")
	private String title;
	
	@Column (name= "cover")
	private String cover;
	
	@Column (name= "link")
	private String link;
	
	@Column (name= "rate")
	private Double rate;
	
	@Column (name= "status")
	private int status;
	
	@Column (name= "author")
	private String author;

}