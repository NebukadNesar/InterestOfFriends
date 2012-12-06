package data;

import javax.persistence.Entity;

import play.data.validation.Unique;
import play.db.jpa.Model;

@Entity
public class CategoryStruct extends Model {

	private String name;
	private String category;
	private String date;
	private String ids;
	private String url;
	private int rate;

	/**
	 * 
	 * @param name
	 * @param category
	 * @param date
	 * @param id
	 */
	public CategoryStruct(String name, String category, String date, String id,
			String url ,int rate) {
		this.category = category;
		this.date = date;
		this.ids = id;
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String id) {
		this.ids = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "\n\tData: " + getName() + "," + getCategory() + "," + getDate()
				+ "," + getId() + " " + getUrl()+""+getRate();
	}

}
