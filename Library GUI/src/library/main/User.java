package library.main;

import java.util.ArrayList;

public class User{
	
	
	private int studentNumber;
	private int numOfBooks;
	
	//getter num of books
	public int getNumOfBooks() {
		return numOfBooks;
	}
	
	//setter for number of books
	public void setNumOfBooks(int numOfBooks) {
		this.numOfBooks = numOfBooks;
	}
	
	//userISBN, checkOutDate arry lists
	//userISBNKeep array list which never gets elements removed from it
	public ArrayList<String> userISBN = new ArrayList<String>();
	public ArrayList<String> checkOutDate = new ArrayList<String>();
	public ArrayList<String> userISBNKeep = new ArrayList<String>();
	
	//getter for student number
	public int getStudentNumber() {
		return studentNumber;
	}
	
	//setter for student number
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	private String firstName, lastName;
	
	//getter for last name
	public String getLastName() {
		return lastName;
	}
	
	//setter for last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	//getter for first name
	public String getFirstName() {
		return firstName;
	}
	
	//setter for first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private String dateCheckedOut;
	
	//getter for checkout date
	public String getDateCheckedOut() {
		return dateCheckedOut;
	}
	
	//setter for checkout date
	public void setDateCheckedOut(String dateCheckedOut) {
		this.dateCheckedOut = dateCheckedOut;
	}

	private double finesBalance;
	
	//getter for fines balance
	public double getFinesBalance() {
		return finesBalance;
	}
	
	//setter for fines balance
	public void setFinesBalance(double finesBalance) {
		this.finesBalance = finesBalance;
	}
	
	//constructor for the uer class
	public User(int num, String first, String last){
		studentNumber = num;
		firstName = first;
		lastName = last;
		finesBalance = 0.0;
		numOfBooks = 0;
	}
	
	//adds to the number of books the user has checked out
	public void addBooksUserHas(){
		numOfBooks++;
	}
	
	//subtracts the number of books a user has if he/she returned a book
	public void removeBooksUserHas(){
		numOfBooks--;
	}
	
	//Nim, Umar, Mohammed
	
	/*canCheckoutBook to determine if a user has a fines balnce of over $5
	 * @return returns false if the fines balance is over $5 true otherwise
	 */
	public boolean canCheckoutBook(){
		if(finesBalance >= 5.0){
			return false;
		}
		return true;
	}
	
	//adds book cost to fines balance if the book is lost
	public void lostBook(double bookValue){
		finesBalance += bookValue;
	}
	
	//adds fines for everyday a book is overdue
	public void addFines(int daysOverdue){
		finesBalance += 0.1 * daysOverdue;
	}
	
	//sets the users fine balnce to $0.00
	public void removeFine(){
		finesBalance = 0.0;
	}
}
