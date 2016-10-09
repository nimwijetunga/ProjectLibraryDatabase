package library.main;

import java.util.ArrayList;

public class LibrarySystem{
	
	/*Object Array Lists
	 * One for the User class
	 * The other for Book Class 
	 */
	public ArrayList<User> user = new ArrayList<User>();
	public ArrayList<Book> book = new ArrayList<Book>();
	
	private String dateC = " ";
	public int userCount = 0;
	
	private String systemName;
	
	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	//Constructor for this class (only parameter is the name of the system)
	public LibrarySystem(String name){
		setSystemName(name);
	}
	
	/*userExists to find if a user given his/her student number actually exists
	 * @params stuNum is the student number of the user
	 * @return returns true if the user exists false otherwise
	 */
	public boolean userExists(int stuNum){
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				return true;
			}
		}
		return false;
	}
	
	/*createUser to create a new user given specific input criteria
	 * @params stuNum is the student number of the user
	 * @params first the first name of the student
	 * @params last is the last name of the student
	 */
	public void createUser(int stuNum, String first, String last){
		if(userExists(stuNum)){
			return;
		}
		user.add(new User(stuNum, first, last));
	}
	
	/*deleteUser is a method to delete a specific user in the system
	 * @params stuNum is the student number of the individual 
	 */
	public void deleteUser(int stuNum){
		for(int i = 0; i < user.size(); i++){
			if(((User) user.get(i)).getStudentNumber() == stuNum){
				userCount--;
				user.remove(i);
			}
		}
	}
	
	/*bookExists to determine if a specific book in the system exists given its isbn
	 * @params iden is the isbn of the book being searched for
	 * @return returs true if the book was found false otherwise
	 */
	public boolean bookExists(String iden){
		for(int i = 0; i < book.size(); i++){
			if(book.get(i).getISBN().equalsIgnoreCase(iden)){
				return true;
			}
		}
		return false;
	}
	
	/*addBook to create a new book and add it to the system given specific criteria
	 * @param name = name, aut = author, iden = isbn, genre = category (action..), rate = rating (1-5), price = cost of book
	 */
	public void addBook(String name, String aut, String iden, String genre,int rate, double price){
		book.add(new Book(name, aut, iden, genre, rate, price));
	}
	
	//Nim, Umar, Mohammed
	
	/*removeBook to remove a specific book in the system
	 * @params iden is the isbn of the book
	 */
	public void removeBook(String iden){
		for(int i = 0; i < book.size(); i++){
			if(((Book) book.get(i)).getISBN().equals(iden)){
				book.remove(i);
			}
		}
	}
	
	/*canCheckout determines if a user can checkout a book 
	 * method calls to another method found in the user class
	 * @param stuNnm is the student number of the individual
	 * @return returns true if the user can checkout the book, false otherwise 
	 */
	public boolean canCheckOut(int stuNum){
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				if(!user.get(i).canCheckoutBook()){
					return false;
				}
			}
		}
		return true;
	}
	
	/*checkOutBook makes all the necesarry adjustments so a book can be checked out
	 * @params iden = isbn, stuNum = student number, date = checkout date
	 */
	public void checkOutBook(String iden, int stuNum, String date){
		
		//loop to set the books checkout varaible to true
		for(int i = 0; i < book.size(); i++){
			if(((Book) book.get(i)).getISBN().equalsIgnoreCase(iden)){
				((Book) book.get(i)).setCheckedOut(true);
			}
		}
		
		//loop to add to the number of books the user has, set the date of the book checked out to the array list found in user
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				user.get(i).checkOutDate.add(date);
				user.get(i).addBooksUserHas();
				user.get(i).userISBN.add(iden);
				user.get(i).userISBNKeep.add(iden);
				break;
			}
		}
		
	}
	
	/*hasTHreeBOoks to determine if a user has 3 books already checked out
	 * @param stuNum is the student number of the user
	 * @return returns true if the user has three books false otherwise
	 */
	public boolean hasThreeBooks(int stuNum){
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				if(user.get(i).getNumOfBooks() == 3){
					return true;
				}
			}
		}
		return false;
	}
	
	/*returnBook returns a book that a student has checked out
	 * @param iden = isbn stuNUm = student number and returnDate = date the book was returned
	 */
	public void returnBook(String iden, int stuNum, String returnDate){
		
		//for loop to get the date which the book was checked out
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				for(int j = 0; j < user.get(i).userISBNKeep.size(); j++){
					if(user.get(i).userISBNKeep.get(j).equals(iden)){
						dateC = user.get(i).checkOutDate.get(j);
					}
				}
			}
		}
		
		//charges fines if any
		chargeFine(dateC,returnDate, stuNum, iden);
		
		//removes the book that the user had
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				user.get(i).removeBooksUserHas();
				user.get(i).userISBN.remove(iden);
			}
		}
		
		//sets the book checked out variable to false
		for(int i = 0; i < book.size(); i++){
			if(book.get(i).getISBN().equalsIgnoreCase(iden)){
				book.get(i).setCheckedOut(false);
			}
		}
	}
	
	/*bookLost to add fine to the student if a book has been lost
	 * @param stuNum = student number , iden = isbn
	 */
	public void bookLost(int stuNum, String iden){
		//loop i to search through the user array to find the specified user
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				user.get(i).userISBN.remove(iden);//removes book isbn from the user class
				//loop j to search through book array to find specified book
				for(int j = 0; j < book.size(); j++){
					if(book.get(j).getISBN().equals(iden)){
						user.get(i).lostBook(book.get(j).getCost());// adds the cost of the book to users fine
						book.remove(j);//removes the book
						return;
					}
				}
			}
		}
	}
	
	/*payFine sets the user fine to $0.00
	 * @param stuNum = student number
	 */
	public void payFine(int stuNum){	
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				user.get(i).setFinesBalance(0.0);
			}
		}
	}
	
	/*booksByAuthor finds all the books by one specific author
	 * @param auth is the author being search for
	 * @return returns an array list with all the book titles by that author
	 */
	public ArrayList<String> booksByAuthor(String auth){
		ArrayList <String> booksAuthor = new ArrayList<String>();
		for(int i = 0; i < book.size(); i++){
			if(book.get(i).getAuthor().equalsIgnoreCase(auth)){
				booksAuthor.add(book.get(i).getTitle());
			}
		}
		return booksAuthor;
	}
	
	/*booksByCategory finds all the books in one specific category
	 * @param cat is the category being search for
	 * @return returns an array list with all the book titles in the category
	 */
	public ArrayList<String> booksByCategory(String cat){
		ArrayList<String> booksCategory = new ArrayList<String>();
		for(int i = 0; i < book.size(); i++){
			if(book.get(i).getCategory().equalsIgnoreCase(cat)){
				booksCategory.add(book.get(i).getTitle());
			}
		}
		return booksCategory;
	}
	
	/*userHasBook to see if a user actually has a specific book checked out
	 * @param stuNum = student number, iden = book's isbn
	 * @return returns true if the user has the book false otherwise
	 */
	public boolean userHasBook (int stuNum, String iden){
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				for(int j = 0; j < user.get(i).userISBN.size(); i++){
					if(user.get(i).userISBN.get(j).equalsIgnoreCase(iden)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/*booksUserHas to return all the books that a user has checked out
	 * @param stuNum = student number
	 * @return returns an array list with all the users books
	 */
	public ArrayList<String> booksuserHas(int stuNum){
		ArrayList<String> lst = new ArrayList<String>();
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				for(int j = 0; j < user.get(i).userISBN.size(); j++){
					lst.add(user.get(i).userISBN.get(j));
				}
			}
		}
		return lst;
	}
	
	/*compareTwoBooks gives the book with the highest rating 
	 * @param iden = isbn of book 1, iden2 = isbn of book 2
	 * @return returns the title and isbn of the higher rated book
	 */
	public String compareTwoBooks(String iden, String iden2){
		int rating1 = 0, rating2 = 0;
		int tempInd = 0, tempInd2 = 0;
		for(int i = 0; i < book.size(); i++){
			if(book.get(i).getISBN().equals(iden)){
				rating1 = book.get(i).getBookRating();
				tempInd = i;
			}else if(book.get(i).getISBN().equals(iden2)){
				rating2 = book.get(i).getBookRating();
				tempInd2 = i;
			}
		}
		if(rating1 >= rating2){
			return book.get(tempInd).getISBN() + " " + book.get(tempInd).getTitle();
		}else{
			return book.get(tempInd2).getISBN() + " " + book.get(tempInd2).getTitle();
		}
	}
	
	/*compareByAuthor gives the best book by a certain author
	 * @param auth = author
	 * @return returns the title and isbn of the book
	 */
	public String compareByAuthor(String auth){
		int currBest = 0;
		String bestBook = " ";
		for(int i = 0; i < book.size(); i++){
			if(book.get(i).getAuthor().equalsIgnoreCase(auth)){
				if(book.get(i).getBookRating() > currBest){
					currBest = book.get(i).getBookRating();
					bestBook = book.get(i).getISBN() + " " + book.get(i).getTitle();
				}
			}
		}
		return bestBook;
	}
	
	/*compareByCategory gives the best book in a certain category
	 * @param category = category of the book
	 * @return returns the title and isbn of the book
	 */
	public String compareByCategory(String category){
		int currBest = 0;
		String bestBook = " ";
		for(int i = 0; i < book.size(); i++){
			if(book.get(i).getCategory().equalsIgnoreCase(category)){
				if(book.get(i).getBookRating() > currBest){
					currBest = book.get(i).getBookRating();
					bestBook = book.get(i).getISBN() + " " + book.get(i).getTitle();
				}
			}
		}
		return bestBook;
	}
	
	/*chargeFIne to add a fine to a user if he/she returns a book late
	 * @params checkDate = checkout date, returnDate = return date, stuNUm = student number, iden = isbn
	 */
	public void chargeFine(String checkDate, String returnDate, int stuNum, String iden){
		int iDayC, iMonthC, iYearC;
		int iDayR, iMonthR, iYearR;
		
		int totalCheckOut, totalReturn;
		
		//converts the checkout dates, day, month and year to integers (dd/mm/yyyy)
		
		//converts dd to int
		String dayC = "";
		dayC += checkDate.charAt(0);
		dayC += checkDate.charAt(1);
		iDayC = Integer.parseInt(dayC);
		
		//converts mm to inr
		String monthC = "";
		monthC += checkDate.charAt(3);
		monthC += checkDate.charAt(4);
		iMonthC = Integer.parseInt(monthC);
		
		//converts yyyy to int
		String yearC = "";
		yearC += checkDate.charAt(6);
		yearC += checkDate.charAt(7);
		yearC += checkDate.charAt(8);
		yearC += checkDate.charAt(9);
		iYearC = Integer.parseInt(yearC);
		
		//Same for return date
		
		String dayR = "";
		dayR += returnDate.charAt(0);
		dayR += returnDate.charAt(1);
		iDayR = Integer.parseInt(dayR);
		
		String monthR = "";
		monthR +=  returnDate.charAt(3);
		monthR += returnDate.charAt(4);
		iMonthR = Integer.parseInt(monthR);
		
		String yearR = "";
		yearR +=  returnDate.charAt(6);
		yearR += returnDate.charAt(7);
		yearR += returnDate.charAt(8);
		yearR += returnDate.charAt(9);
		iYearR = Integer.parseInt(yearR);
		
		//finds the total of both the checkout and return date
		totalCheckOut = iDayC + (iMonthC * 30) + (iYearC * 365);
		totalReturn = iDayR + (iMonthR * 30) + (iYearR * 365);
		
		//if the subtraction of the two dates is over 14 the book is overdue and a fine must be charged
		if((totalReturn - totalCheckOut) > 14){
			for(int i = 0; i < user.size(); i++){
				if(user.get(i).getStudentNumber() == stuNum){
					int temp = (totalReturn - totalCheckOut) - 14;
					user.get(i).addFines(temp);
				}
			}
		}
	}
}
