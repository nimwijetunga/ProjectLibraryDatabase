package library.main;

public class Book {
	private String title, author, ISBN, category;
	
	//getter for author's name
	public String getAuthor() {
		return author;
	}
	
	//setter for the author's name
	public void setAuthor(String author) {
		this.author = author;
	}
	
	//getter for the category
	public String getCategory() {
		return category;
	}
	
	//setter for the category 
	public void setCategory(String category) {
		this.category = category;
	}
	
	//getter for the title
	public String getTitle() {
		return title;
	}
	
	
	//setter for the title 
	public void setTitle(String title) {
		this.title = title;
	}

	private boolean checkedOut;
	
	//getter for the boolean getCheckedOut	
	public boolean getCheckedOut() {
		return checkedOut;
	}
	
	//setter for the boolean getCheckedOut	
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}
	
	//getter for the isbn
	public String getISBN() {
		return ISBN;
	}
	
	//setter for the isbn
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	private double cost;
	
	//getter for the cost
	public double getCost() {
		return cost;
	}
	
	//setter for the cost
	public void setCost(double cost) {
		this.cost = cost;
	}

	private int bookRating;
	
	//getter for the book rating
	public int getBookRating() {
		return bookRating;
	}
	
	//Nim, Umar, Mohammed
	
	//setter for the book rating
	public void setBookRating(int bookRating) {
		this.bookRating = bookRating;
	}
	
	/*constructor for the Book class
	 * @param name = book's name, aut = author, iden = isbn, genre = category, rate = book rating (1-5), price = cost fo the book
	 */
	public Book(String name, String aut, String iden, String genre, int rate, double price){
		title = name;
		author = aut;
		ISBN = iden;
		category = genre;
		cost = price;
		bookRating = rate;
		checkedOut = false;
	}
}
