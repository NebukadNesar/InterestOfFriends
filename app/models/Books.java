package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Books extends Model {

	@Lob
	public String content;
	public String title;
	public String author;
	public double rate;
	
	@OneToMany(mappedBy="book", cascade=CascadeType.ALL)
	public List<Comment> comments;
	
	@ManyToOne
    public User user;
	
	public Books(String content, String title, String author, double rate) {
		super();
		this.content = content;
		this.title = title;
		this.author = author;
		this.rate = rate;
	}

	
	
	
	
}
