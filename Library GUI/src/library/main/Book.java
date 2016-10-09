package library.main;

public class Book {
	private String title, author, ISBN, category;
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private boolean checkedOut;
	
		
	public boolean getCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	private double cost;
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	private int bookRating;
	
	public int getBookRating() {
		return bookRating;
	}
	
	//Nim, Umar, Mohammed

	public void setBookRating(int bookRating) {
		this.bookRating = bookRating;
	}

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
