package library.main;

import java.util.ArrayList;

public class LibrarySystem{

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
	
	public LibrarySystem(String name){
		setSystemName(name);
	}
	
	public boolean userExists(int stuNum){
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				return true;
			}
		}
		return false;
	}
	
	public void createUser(int stuNum, String first, String last){
		if(userExists(stuNum)){
			return;
		}
		user.add(new User(stuNum, first, last));
	}
	
	public void deleteUser(int stuNum){
		for(int i = 0; i < user.size(); i++){
			if(((User) user.get(i)).getStudentNumber() == stuNum){
				userCount--;
				user.remove(i);
			}
		}
	}
	
	public boolean lookUpUser(int stuNum){
		for(int i = 0; i < user.size(); i++){
			if(((User) user.get(i)).getStudentNumber() == stuNum){
				return true;
			}
		}
		return false;
	}
	
	public boolean bookExists(String iden){
		for(int i = 0; i < book.size(); i++){
			if(book.get(i).getISBN().equalsIgnoreCase(iden)){
				return true;
			}
		}
		return false;
	}
	
	public void addBook(String name, String aut, String iden, String genre,int rate, double price){
		book.add(new Book(name, aut, iden, genre, rate, price));
	}
	
	//Nim, Umar, Mohammed
	
	public void removeBook(String iden){
		for(int i = 0; i < book.size(); i++){
			if(((Book) book.get(i)).getISBN().equals(iden)){
				book.remove(i);
			}
		}
	}
	
	public boolean lookUpBook(String iden){
		for(int i = 0; i < book.size(); i++){
			if(((Book) book.get(i)).getISBN().equalsIgnoreCase(iden)){
				return true;
			}
		}
		return false;
	}
	
	public void rateBook(String iden, int rating){
		for(int i = 0; i < book.size(); i++){
			if(book.get(i).getISBN().equalsIgnoreCase(iden)){
				book.get(i).setBookRating(rating);
			}
		}
	}
	
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
	
	public void checkOutBook(String iden, int stuNum, String date){
		
		for(int i = 0; i < book.size(); i++){
			if(((Book) book.get(i)).getISBN().equalsIgnoreCase(iden)){
				((Book) book.get(i)).setCheckedOut(true);
			}
		}
		
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				user.get(i).setDateCheckedOut(date);
				user.get(i).addBooksUserHas();
				user.get(i).userISBN.add(iden);
				break;
			}
		}
		
	}
	
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
	
	public void returnBook(String iden, int stuNum, String returnDate){
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				dateC = user.get(i).getDateCheckedOut();
			}
		}
		
		chargeFine(dateC,returnDate, stuNum, iden);
		
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				user.get(i).removeBooksUserHas();
				user.get(i).userISBN.remove(iden);
			}
		}
		
		for(int i = 0; i < book.size(); i++){
			if(book.get(i).getISBN().equalsIgnoreCase(iden)){
				book.get(i).setCheckedOut(false);
			}
		}
	}
	
	public void bookLost(int stuNum, String iden){
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				user.get(i).userISBN.remove(iden);
				for(int j = 0; j < book.size(); j++){
					if(book.get(j).getISBN().equals(iden)){
						user.get(i).lostBook(book.get(j).getCost());
						book.remove(j);
						return;
					}
				}
			}
		}
	}
	
	public void payFine(int stuNum){	
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getStudentNumber() == stuNum){
				user.get(i).setFinesBalance(0.0);
			}
		}
	}
	
	public ArrayList<String> booksByAuthor(String auth){
		ArrayList <String> booksAuthor = new ArrayList<String>();
		for(int i = 0; i < book.size(); i++){
			if(book.get(i).getAuthor().equalsIgnoreCase(auth)){
				booksAuthor.add(book.get(i).getTitle());
			}
		}
		return booksAuthor;
	}
	
	public ArrayList<String> booksByCategory(String cat){
		ArrayList<String> booksCategory = new ArrayList<String>();
		for(int i = 0; i < book.size(); i++){
			if(book.get(i).getCategory().equalsIgnoreCase(cat)){
				booksCategory.add(book.get(i).getTitle());
			}
		}
		return booksCategory;
	}
	
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
	
	public void chargeFine(String checkDate, String returnDate, int stuNum, String iden){
		int iDayC, iMonthC, iYearC;
		int iDayR, iMonthR, iYearR;
		
		int totalCheckOut, totalReturn;
		
		String dayC = "";
		dayC += checkDate.charAt(0);
		dayC += checkDate.charAt(1);
		iDayC = Integer.parseInt(dayC);
		
		String monthC = "";
		monthC += checkDate.charAt(3);
		monthC += checkDate.charAt(4);
		iMonthC = Integer.parseInt(monthC);
		
		String yearC = "";
		yearC += checkDate.charAt(6);
		yearC += checkDate.charAt(7);
		yearC += checkDate.charAt(8);
		yearC += checkDate.charAt(9);
		iYearC = Integer.parseInt(yearC);
		
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
		
		totalCheckOut = iDayC + (iMonthC * 30) + (iYearC * 365);
		totalReturn = iDayR + (iMonthR * 30) + (iYearR * 365);
		
		if((totalReturn - totalCheckOut) > 14){
			for(int i = 0; i < user.size(); i++){
				if(user.get(i).getStudentNumber() == stuNum){
					int temp = (totalReturn - totalCheckOut) - 14;
					user.get(i).setDaysOverdue(temp);
					user.get(i).addFines();
				}
			}
		}
	}
}
