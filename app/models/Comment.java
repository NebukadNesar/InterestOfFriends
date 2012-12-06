package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.data.validation.Required;
import play.db.jpa.*;
 
@Entity
public class Comment extends Model {
 
	@Required
    public User author;
    public Date postedAt;
     
    @Lob
    public String content;
    

    @ManyToOne
    public Books book ;
    
    
    public Comment( User author, String content) {
        this.author = author;
        this.content = content;
        this.postedAt = new Date();
    }
 
}