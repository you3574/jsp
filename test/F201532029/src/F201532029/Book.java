package F201532029;

public class Book {
int id;
String title;
String author;
int categoryId;
int price;
int publisherId;
boolean available;
String categoryName;
String publisherName;

public String getPublisherName() {
	return publisherName;
}
public void setPublisherName(String publisherName) {
	this.publisherName = publisherName;
}
public String getCategoryName() {
	return categoryName;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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

public int getCategoryId() {
	return categoryId;
}
public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}

public int getPublisherId() {
	return publisherId;
}
public void setPublisherId(int publisherId) {
	this.publisherId = publisherId;
}
public boolean isAvailable() {
	return available;
}
public void setAvailable(boolean available) {
	this.available = available;
}



}
