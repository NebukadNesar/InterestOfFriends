package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.db.jpa.JPABase;
import play.db.jpa.Model;

@Entity
public class User extends Model {
	@Column(unique = true)
	public String facebookID;
	public String userName;
	public String piclink;

	@ManyToMany(mappedBy="User")
	public List<Books> books;

	public User(String facebookID,String userName,String piclink,List<Books> books) {
		this.facebookID = facebookID;
		this.piclink=piclink;
		this.userName=userName;
		this.books=books;
	}

	public User(String user_id) {
		// TODO Auto-generated constructor stub
		this.facebookID=user_id;
	}
	
	public static List<User> getAllUsers(){
		return User.findAll();
	}
	
	public String getFacebookID() {
		return facebookID;
	}

	public void setFacebookID(String facebookID) {
		this.facebookID = facebookID;
	}
	
	public static User getByFacebookID(String facebookID) {
		return User.find("facebookID = ? ", facebookID).first();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPiclink() {
		return piclink;
	}

	public void setPiclink(String piclink) {
		this.piclink = piclink;
	}

	public List<Books> getBooks() {
		return books;
	}

	public void setBooks(List<Books> books) {
		this.books = books;
	}
	
	@Override
	public String toString() {
		String books = "";
		
		for(Books b:this.books){
			books+=":"+b.getIdd()+","+b.getTitle()+"\n";
		}
		
		return "USER: "+facebookID+","+userName+","+piclink+" "+books;
	}

}
