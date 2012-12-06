package models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class User extends Model {
	@Column(unique = true) 
	public String facebookID;
	
	
	
	
	public User(String facebookID) {
	  this.facebookID = facebookID;
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
}
