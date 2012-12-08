package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Books extends Model {

	@Lob
	public String idd;
	public String content;
	public String title;
	public String author;
	public double rate;
	
	@OneToMany(mappedBy="book", cascade=CascadeType.ALL)
	public List<Comment> comments;
	
	@ManyToMany(mappedBy="Books")
    public User user;
	
	public Books(String idd,String content, String title, String author, double rate) {
		super();
		this.idd=idd;
		this.content = content;
		this.title = title;
		this.author = author;
		this.rate = rate;
	}
	
	public static List<Books> getAllBooks(){
		return Books.findAll();
	}
	
	public String getIdd() {
		return idd;
	}


	public void setIdd(String idd) {
		this.idd = idd;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
