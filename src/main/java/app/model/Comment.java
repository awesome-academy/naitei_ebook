package app.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "comments")
@Setter
@Getter
@NoArgsConstructor

public class Comment {
	@Id
	@Column (name= "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "book_id")
    private Book book;
	
	@ManyToOne
	@JoinColumn(name = "reply_id")
    private Comment comment;
    
    @OneToMany(mappedBy="comment", fetch = FetchType.EAGER)
    private List<Comment> replies;
    
	
	@Column (name= "created_at")
	private LocalDateTime createDateTime;
	
	@Column (name= "updated_at")
	private LocalDateTime updateDateTime;

	@Column (name= "content")
	private String content;

}