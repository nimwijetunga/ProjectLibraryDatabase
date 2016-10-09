package library.main;

import java.util.ArrayList;

public class User{
	
	private int studentNumber;
	private int numOfBooks;
	public int getNumOfBooks() {
		return numOfBooks;
	}

	public void setNumOfBooks(int numOfBooks) {
		this.numOfBooks = numOfBooks;
	}

	private int daysOverdue;
	
	public int getDaysOverdue() {
		return daysOverdue;
	}

	public void setDaysOverdue(int daysOverdue) {
		this.daysOverdue = daysOverdue;
	}

	public ArrayList<String> userISBN = new ArrayList<String>();
	public ArrayList<String> checkOutDate = new ArrayList<String>();
	public ArrayList<String> userISBNKeep = new ArrayList<String>();
	
	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	private String firstName, lastName;
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private String dateCheckedOut;
	
	public String getDateCheckedOut() {
		return dateCheckedOut;
	}

	public void setDateCheckedOut(String dateCheckedOut) {
		this.dateCheckedOut = dateCheckedOut;
	}

	private double finesBalance;
	
	public double getFinesBalance() {
		return finesBalance;
	}

	public void setFinesBalance(double finesBalance) {
		this.finesBalance = finesBalance;
	}

	public User(int num, String first, String last){
		studentNumber = num;
		firstName = first;
		lastName = last;
		finesBalance = 0.0;
		numOfBooks = 0;
	}
	
	public void addBooksUserHas(){
		numOfBooks++;
	}
	
	public void removeBooksUserHas(){
		numOfBooks--;
	}
	
	//Nim, Umar, Mohammed
	
	public boolean canCheckoutBook(){
		if(finesBalance >= 5.0){
			return false;
		}
		return true;
	}
	
	public void lostBook(double bookValue){
		finesBalance += bookValue;
	}
	
	public void addFines(){
		finesBalance += 0.1 * daysOverdue;
	}
	
	public void removeFine(){
		finesBalance = 0.0;
	}
}
