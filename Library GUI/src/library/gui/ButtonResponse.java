package library.gui;

import java.awt.BorderLayout;



import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ButtonResponse implements ActionListener{
	
	private int stuNum, rating, pos = 0;
	private double fine, cost, confirm;
	private String iden, dateC, dateR;

	private JTextField username = new JTextField();

	private JPasswordField password = new JPasswordField();
	
	/*actionPreformed runs when a button on the GUI is clicked 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		String temp = " ";
		//If the admin creates a new user
		if (e.getActionCommand().equals("Add User")) {
			//checks to see if any fields are missing 
			if(LibraryGUI.textField.getText().equals("") || LibraryGUI.textField_1.getText().equals("") || LibraryGUI.textField_2.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			
			temp = LibraryGUI.textField.getText();
			
			//Checks to see if user entered numerical values only
			if(!inputCorrect(temp)){
				JOptionPane.showMessageDialog(null, "Input Type Incorrect");
				return;
			}
			
			//admin login to system
			while(LibraryGUI.credVerified == false && LibraryGUI.attempts <= 5){
				login();
			}
			//deny acess if attempts are over 5
			if(LibraryGUI.attempts > 5){
				JOptionPane.showMessageDialog(null, "Access Denied, Restart System");
				return;
			}
			
			stuNum = Integer.valueOf(temp);
			
			//creates user and adds user to the list displayed on the user tab  if they don't already exist
			if(!LibraryGUI.sys.userExists(stuNum)){
				LibraryGUI.sys.createUser(stuNum, LibraryGUI.textField_1.getText(), LibraryGUI.textField_2.getText());
				LibraryGUI.studentList.addElement(LibraryGUI.textField_1.getText() + " " + LibraryGUI.textField_2.getText() + ", " + Integer.toString(stuNum)
				+ ", " + LibraryGUI.formatter.format(fine));
				JOptionPane.showMessageDialog(null, "User Added");
			}else{
				JOptionPane.showMessageDialog(null, "User Already Exists");
			}
		}
		
		//If an admin creates a new book
		if(e.getActionCommand().equals("Add Book")){
			if(LibraryGUI.textField_4.getText().equals("") || LibraryGUI.textField_5.getText().equals("") || LibraryGUI.textField_6.getText().equals("")
					|| LibraryGUI.textField_7.getText().equals("") || LibraryGUI.textField_9.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}

			//gets the value of the radio button clicked
			rating = getSelectedButton();
			temp = LibraryGUI.textField_9.getText();
			if(!inputCorrect(temp)){
				JOptionPane.showMessageDialog(null, "Input Type Incorrect");
				return;
			}
			cost = Double.valueOf(temp);
			
			while(LibraryGUI.credVerified == false && LibraryGUI.attempts <= 5){
				login();
			}
			if(LibraryGUI.attempts > 5){
				JOptionPane.showMessageDialog(null, "Access Denied, Restart System");
				return;
			}
			
			if(getSelectedButton() == 0){
				JOptionPane.showMessageDialog(null, "Enter a Rating");
				return;
			}
			//Adds book to the database and to the list displayed in the book tab if it doesn't already exist
			if(!LibraryGUI.sys.bookExists(LibraryGUI.textField_6.getText())){
				double a = Double.valueOf(LibraryGUI.textField_9.getText());
				LibraryGUI.sys.addBook(LibraryGUI.textField_4.getText(), LibraryGUI.textField_5.getText(),LibraryGUI.textField_6.getText(), 
						LibraryGUI.textField_7.getText(), rating, cost);
				LibraryGUI.bookList.addElement(LibraryGUI.textField_4.getText() + ", " + LibraryGUI.textField_5.getText() + ", " + LibraryGUI.textField_6.getText()
				+ ", " + LibraryGUI.textField_7.getText() + ", " + rating + ", " + LibraryGUI.formatter.format(a));
				JOptionPane.showMessageDialog(null, "Book Added");
				refreshListCheckout();
			}else{
				JOptionPane.showMessageDialog(null, "Book Already Exists");
			}
		}
		
		//If the admin wants to delete a user
		if(e.getActionCommand().equals("Delete User")){
			if(LibraryGUI.textField_10.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}

			temp = LibraryGUI.textField_10.getText();
			if(!inputCorrect(temp)){
				JOptionPane.showMessageDialog(null, "Input Type Incorrect");
				return;
			}
			while(LibraryGUI.credVerified == false && LibraryGUI.attempts <= 5){
				login();
			}
			if(LibraryGUI.attempts > 5){
				JOptionPane.showMessageDialog(null, "Access Denied, Restart System");
				return;
			}
			stuNum = Integer.valueOf(temp);
			
			//Makes sure that the user exists
			if(!LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "User Does Not Exist");
			}else{
				for(int i = 0; i < LibraryGUI.sys.user.size(); i++){
					if(LibraryGUI.sys.user.get(i).getStudentNumber() == stuNum){
						pos = i;
						break;
					}
				}
				//Confirmation to delete user
				confirm = JOptionPane.showConfirmDialog(null, "Do You Want to Delete User?");
				
				
				if(confirm == JOptionPane.YES_OPTION){
					LibraryGUI.sys.deleteUser(stuNum); //Deletes user (from database) if the admin selects the yes option
					LibraryGUI.studentList.removeElementAt(pos); //Deletes the user from the list in the user tab
					JOptionPane.showMessageDialog(null, "User Deleted");
				}
			}
		}
		
		//If the admin wants to delete a book
		if(e.getActionCommand().equals("Delete Book")){
			if(LibraryGUI.textField_11.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}

			while(LibraryGUI.credVerified == false && LibraryGUI.attempts <= 5){
				login();
			}
			if(LibraryGUI.attempts > 5){
				JOptionPane.showMessageDialog(null, "Access Denied, Restart System");
				return;
			}
			iden = LibraryGUI.textField_11.getText();
			//Makes sure that the book exists 
			if(!LibraryGUI.sys.bookExists(iden)){
				JOptionPane.showMessageDialog(null, "Book Does Not Exist");
			}else{
				for(int i = 0; i < LibraryGUI.sys.book.size(); i++){
					if(LibraryGUI.sys.book.get(i).getISBN().equals(iden)){
						pos = i;
						break;
					}
				}
				//Confirms if the admin wants to delete the book
				confirm = JOptionPane.showConfirmDialog(null, "Do You Want to Delete Book?");
				//If yes the book is deleted
				if(confirm == JOptionPane.YES_OPTION){
					LibraryGUI.sys.removeBook(iden);//deleted from the database
					LibraryGUI.bookList.removeElementAt(pos);//deleted form the list in the book tab
					JOptionPane.showMessageDialog(null, "Book Removed");
					refreshListCheckout();
				}
			}
		}
		
		//If the user wants to checkout a book
		if(e.getActionCommand().equals("Checkout Book")){
			if(LibraryGUI.textField_12.getText().equals("") || LibraryGUI.textField_13.getText().equals("") || LibraryGUI.textField_14.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			temp = LibraryGUI.textField_12.getText();
			if(!inputCorrect(temp)){
				JOptionPane.showMessageDialog(null, "Input Type Incorrect");
				return;
			}
			stuNum = Integer.parseInt(temp);
			iden = LibraryGUI.textField_13.getText();
			dateC = LibraryGUI.textField_14.getText();
			
			//checks to see if the user exists
			if(!LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "Invalid Student Number");
				return;
			}
			
			//Makes sure that the book exists 
			if(!LibraryGUI.sys.bookExists(iden)){
				JOptionPane.showMessageDialog(null, "Book Does not Exist");
				return;
			}
			
			//Makes sure that the user has less than three books checked out currently
			if(LibraryGUI.sys.hasThreeBooks(stuNum)){
				JOptionPane.showMessageDialog(null, "User Already Has 3 Books Checked Out");
				return;
			}
			
			//Checks if the book has been checked out already or not
			for(int i = 0; i < LibraryGUI.sys.book.size(); i++){
				if(LibraryGUI.sys.book.get(i).getISBN().equals(iden)){
					if(LibraryGUI.sys.book.get(i).getCheckedOut() == true){
						JOptionPane.showMessageDialog(null, "Book Already Checked Out");
						return;
					}
				}
			}
			//checks if the users fines are under $5
			if(LibraryGUI.sys.canCheckOut(stuNum) == false){
				JOptionPane.showMessageDialog(null, "Fines Over $5");
				return;
			}
			
			//Checks out the book (Sets the books boolean to true)
			for(int i = 0; i < LibraryGUI.sys.user.size(); i++){
				if(LibraryGUI.sys.user.get(i).getStudentNumber() == stuNum){
					LibraryGUI.sys.checkOutBook(iden, stuNum, dateC);
					JOptionPane.showMessageDialog(null, "Book Checked Out");
					break;
				}
			}
			
			//Refreshes the checked out book list
			refreshListCheckout();
		}
		
		//If the user wants to return a book
		if(e.getActionCommand().equals("Return Book")){
			if(LibraryGUI.textField_15.getText().equals("") || LibraryGUI.textField_16.getText().equals("") || LibraryGUI.textField_17.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			temp = LibraryGUI.textField_15.getText();
			if(!inputCorrect(temp)){
				JOptionPane.showMessageDialog(null, "Input Type Incorrect");
				return;
			}
			stuNum = Integer.parseInt(temp);
			iden = LibraryGUI.textField_17.getText();
			dateR = LibraryGUI.textField_16.getText();
			
			//Makes sure book exists
			if(!LibraryGUI.sys.bookExists(iden)){
				JOptionPane.showMessageDialog(null, "Book Does not Exist");
				return;
			}
			
			//Makes sure user exists
			if(!LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "Invalid Student Number");
				return;
			}
			
			//Checks to see if the book was checked out in the first place
			for(int i = 0; i < LibraryGUI.sys.book.size(); i++){
				if(LibraryGUI.sys.book.get(i).getISBN().equals(iden)){
					if(LibraryGUI.sys.book.get(i).getCheckedOut() == false){
						JOptionPane.showMessageDialog(null, "Book Was Not Checked Out");
						return;
					}
				}
			}
			//Checks to see if the book was checked out by the specified user
			if(!(LibraryGUI.sys.userHasBook(stuNum, iden))){
				JOptionPane.showMessageDialog(null, "User Does Not Have The Book");
				return;
			}
			
			//Returns book
			for(int i = 0; i < LibraryGUI.sys.user.size(); i++){
				if(LibraryGUI.sys.user.get(i).getStudentNumber() == stuNum){
					LibraryGUI.sys.returnBook(iden, stuNum, dateR);
					JOptionPane.showMessageDialog(null, "Book Returned");
					break;
				}
			}
			//Refreshes the checkout book list
			refreshListCheckout();
			//Refreshes the user list in order to add fines
			refreshUserList();
		}
		
		//If the user lost their book
		if(e.getActionCommand().equals("Lost Book")){
			if(LibraryGUI.textField_15.getText().equals("") || LibraryGUI.textField_17.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			temp = LibraryGUI.textField_15.getText();
			if(!inputCorrect(temp)){
				JOptionPane.showMessageDialog(null, "Input Type Incorrect");
				return;
			}
			stuNum = Integer.parseInt(temp);
			iden = LibraryGUI.textField_17.getText();
			
			//Makes sure that the book and user exist
			if(!LibraryGUI.sys.bookExists(iden) || !LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "Book/User Does not Exist");
				return;
			//Checks to see if the user actually checked out the book
			}else if(!LibraryGUI.sys.userHasBook(stuNum, iden)){
				JOptionPane.showMessageDialog(null, "User Does Not Have The Book");
				return;
			}
			
			//Run the lost book method
			LibraryGUI.sys.bookLost(stuNum, iden);
			JOptionPane.showMessageDialog(null, "Fine Added");
			
			//Refresh books available list
			refreshListCheckout();
			//Refreshes user list to add cost of the book
			refreshUserList();
			//Refreshes book list to remove the lost book from the database
			refreshBookList();
		}
		
		//If the user wants to search for a book by author
		if(e.getActionCommand().equals("Search By Author")){
			if(LibraryGUI.textField_18.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			//Clear the list found on this tab
			LibraryGUI.titleList.removeAllElements();
			//Run the booksByAuthor method and store the return values in an array list
			ArrayList <String> list = LibraryGUI.sys.booksByAuthor(LibraryGUI.textField_18.getText());
			for(int i = 0; i < list.size(); i++){
				LibraryGUI.titleList.addElement(list.get(i));//add the elements to the title list
			}
			JOptionPane.showMessageDialog(null, "Search Completed");
		}
		
		//If the user wants to search for a book by category
		if(e.getActionCommand().equals("Search By Category")){
			
			if(LibraryGUI.textField_19.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			//remove all elements from the list found in this tab
			LibraryGUI.titleList.removeAllElements();
			//run the booksByCategory method and store the return values in an array list
			ArrayList<String> lst = LibraryGUI.sys.booksByCategory(LibraryGUI.textField_19.getText());
			for(int i = 0; i < lst.size(); i++){
				LibraryGUI.titleList.addElement(lst.get(i));//add each element to the list on the tab
			}
			JOptionPane.showMessageDialog(null, "Search Completed");
		}
		
		//If the admin wants to clear a users fine balance 
		if(e.getActionCommand().equals("Pay Fine")){
			if(LibraryGUI.textField_10.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}

			while(LibraryGUI.credVerified == false && LibraryGUI.attempts <= 5){
				login();
			}
			if(LibraryGUI.attempts > 5){
				JOptionPane.showMessageDialog(null, "Access Denied, Restart System");
				return;
			}

			temp = LibraryGUI.textField_10.getText();
			if(!inputCorrect(temp)){
				JOptionPane.showMessageDialog(null, "Input Type Incorrect");
				return;
			}
			stuNum = Integer.valueOf(temp);
			
			//makes sure that the user exists
			if(!LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "User Does Not Exist");
				return;
			}
			
			//Makes sure that the user has fines above $5, if not returns
			for(int i = 0; i < LibraryGUI.sys.user.size(); i++){
				if(LibraryGUI.sys.user.get(i).getStudentNumber() == stuNum){
					if(LibraryGUI.sys.user.get(i).getFinesBalance() < 5.0){
						JOptionPane.showMessageDialog(null, "Fine is Under $5");
						return;
					}
				}
			}
			
			//Pays the users fine
			JOptionPane.showMessageDialog(null, "Fine Paid");
			LibraryGUI.sys.payFine(stuNum);
		}
		
		//Searches for the books that a user currently has checked out
		if(e.getActionCommand().equals("Search")){
			if(LibraryGUI.textField_21.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			LibraryGUI.userBookList.removeAllElements();
			temp = LibraryGUI.textField_21.getText();
			if(!inputCorrect(temp)){
				JOptionPane.showMessageDialog(null, "Input Type Incorrect");
				return;
			}
			stuNum = Integer.valueOf(temp);
			//Makes sure that the user exists
			if(!LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "User Does Not Exist");
				return;
			}
			//Calls the booksUserHas method and stores the returned isbn values (if any) in an array list
			ArrayList<String> lst = LibraryGUI.sys.booksUserHas(stuNum);
			for(int i = 0; i < lst.size(); i++){
				LibraryGUI.userBookList.addElement(lst.get(i));
			}
			JOptionPane.showMessageDialog(null, "Search Completed");
		}
		
		//If the user wants to compare two books
		if(e.getActionCommand().equals("Compare")){
			if(LibraryGUI.textField_22.getText().equals("") || LibraryGUI.textField_23.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			LibraryGUI.bestBooks.removeAllElements();
			String iden1 = LibraryGUI.textField_22.getText();
			String iden2 = LibraryGUI.textField_23.getText();
			//Makes sure that the two books exist
			if(!LibraryGUI.sys.bookExists(iden1) || !LibraryGUI.sys.bookExists(iden2)){
				JOptionPane.showMessageDialog(null, "Book(s) Does Not Exist");
				return;
				//Makes sure that both books are different
			}else if(iden1.equalsIgnoreCase(iden2)){
				JOptionPane.showMessageDialog(null, "Both Books are the Same");
				return;
			}
			//Adds the best book the bestBooks list displayed on the best books tab
			LibraryGUI.bestBooks.addElement(LibraryGUI.sys.compareTwoBooks(iden1, iden2));
		}
		
		//Finds the best book by a specific author
		if(e.getActionCommand().equals("Compare By Author")){
			if(LibraryGUI.textField_24.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			LibraryGUI.bestBooks.removeAllElements();
			temp = LibraryGUI.sys.compareByAuthor(LibraryGUI.textField_24.getText());
			if(!inputCorrect(temp)){
				JOptionPane.showMessageDialog(null, "Input Type Incorrect");
				return;
			}
			//If not results pop up we know that the author does not exist
			if(temp.equals(" ")){
				JOptionPane.showMessageDialog(null, "Author Does Not Exist");
				return;
			}else{
				//If he/she does exists find their best book
				LibraryGUI.bestBooks.addElement(temp);
			}
		}
		
		//Searches for a book by its name
		if(e.getActionCommand().equals("Search By Name")){
			if(LibraryGUI.textField_3.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			LibraryGUI.titleList.removeAllElements();
			//Runs the booksByTitle method and stores the returned values in an array list
			ArrayList <String> lst = LibraryGUI.sys.booksByTitle(LibraryGUI.textField_3.getText());
			for(int i = 0; i < lst.size(); i++){
				//Adds each element of the array list to the title list found in the best books tab
				LibraryGUI.titleList.addElement(lst.get(i));
			}
			JOptionPane.showMessageDialog(null, "Search Completed");
		}
		
		//Searches for a student by their student number
		if(e.getActionCommand().equals("Search By Number")){
			if(LibraryGUI.textField_8.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			temp = LibraryGUI.textField_8.getText();
			if(!inputCorrect(temp)){
				JOptionPane.showMessageDialog(null, "Input Type Incorrect");
				return;
			}
			stuNum = Integer.valueOf(temp);
			if(temp.equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			//Makes sure the user exists
			if(!LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "User Does not Exist");
				return;
			}
			//Finds the user and returns their first and last names as well as their fine balance
			LibraryGUI.searchedUser.removeAllElements();
			String user = LibraryGUI.sys.searchUser(stuNum);
			LibraryGUI.searchedUser.addElement(user);
			JOptionPane.showMessageDialog(null, "Search Completed");
		}
		//Search for a student by name
		if(e.getActionCommand().equals("Search By Student Name")){
			if(LibraryGUI.textField_20.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			LibraryGUI.searchedUser.removeAllElements();
			//Runs the searchUserName method and stores it return values in an array list
			ArrayList<String> lst = LibraryGUI.sys.searchUserName(LibraryGUI.textField_20.getText());
			for(int i = 0; i < lst.size(); i++)
				LibraryGUI.searchedUser.addElement(lst.get(i));
			
			JOptionPane.showMessageDialog(null, "Search Completed");
		}
		//Compares books based on their category
		if(e.getActionCommand().equals("Compare By Category")){
			if(LibraryGUI.textField_25.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			LibraryGUI.bestBooks.removeAllElements();
			//Finds all the books in the specified category
			temp = LibraryGUI.sys.compareByCategory(LibraryGUI.textField_25.getText());
			if(!inputCorrect(temp)){
				JOptionPane.showMessageDialog(null, "Input Type Incorrect");
				return;
			}
			//If the string is empty that means the category currently does not exist
			if(temp.equals(" ")){
				JOptionPane.showMessageDialog(null, "Cateogry Does Not Exist");
				return;
			}else{
				LibraryGUI.bestBooks.addElement(temp);
			}
		}
	}

	/*getSelectedButton gets the value of radio button clicked by the admin when choosing the rating for the book
	 * @return returns the integer value of the radio button
	 */
	public int getSelectedButton()
	{  
		for (Enumeration<AbstractButton> buttons = LibraryGUI.group.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				return Integer.valueOf(button.getText());
			}
		}
		return 0;
	}
	
	/*login is the frame created when the admin wants to create/delete users or books
	 * @param fram is the Jframe in which the GUI is created
	 */
	public void login(JFrame frame) {
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("Username", SwingConstants.RIGHT));
		label.add(new JLabel("Password", SwingConstants.RIGHT));
		panel.add(label, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));

		controls.add(username);

		controls.add(password);
		panel.add(controls, BorderLayout.CENTER);
		
		//Changes box colour to red if the user gets the password/username incorrect once
		if(LibraryGUI.attempts >= 1){
			username.setBackground(new Color(240,50,50));
			password.setBackground(new Color(240,50,50));
		}
		
		//Shows how many attempts you have had at logging in
		JOptionPane.showMessageDialog(frame, panel, "Login, Attempt: " + LibraryGUI.attempts + "/5", JOptionPane.QUESTION_MESSAGE);
	}
	
	/*loginCorrect makes sure that the credentials entered are infact the correct ones
	 * @params user is the user name, pass [] is a character array of the password
	 */
	public boolean loginCorrect(String user, char [] pass){
		String passString = new String(pass);
		//Makes sure both the user name and password are admin
		if(!user.equals("admin") || !passString.equals("admin")){
			return false;
		}
		return true;
	}
	
	/*login (overloaded) gets the information inputed by the user
	 * 
	 */
	public void login(){
		login(LibraryGUI.frame);
		//If the login was incorrect increase the number of attempts
		if(!(loginCorrect(username.getText(), password.getPassword()))){
			LibraryGUI.attempts++;
		}
		//If correct credentials are entered they do not need to be entered again
		else
			LibraryGUI.credVerified = true;
	}
	
	//Refreshes the checkout list found in the checkout/return tab
	public void refreshListCheckout(){
		LibraryGUI.booksAvailable.removeAllElements();
		String temp = " ";
		for(int i = 0; i < LibraryGUI.sys.book.size(); i++){
			//Sets each book to checked out or not depending on their boolean values
			temp = String.valueOf(LibraryGUI.sys.book.get(i).getCheckedOut());
			if(temp.equals("true"))
				temp = "Checked Out";
			else
				temp = "Not Checked Out";
			LibraryGUI.booksAvailable.addElement(LibraryGUI.sys.book.get(i).getISBN() + ", " + temp);
		}
	}
	
	//Refreshes the list of users found in the user tab
	public void refreshUserList(){
		double tempD = 0.0;
		LibraryGUI.studentList.removeAllElements();
		for(int i = 0; i < LibraryGUI.sys.user.size(); i++){
			tempD = LibraryGUI.sys.user.get(i).getFinesBalance();
			LibraryGUI.studentList.addElement(LibraryGUI.sys.user.get(i).getFirstName() + " " + LibraryGUI.sys.user.get(i).getLastName() +
					", " + LibraryGUI.sys.user.get(i).getStudentNumber() + ", " + LibraryGUI.formatter.format(tempD));
		}
	}
	
	//Refreshes the list of books found in the book tab
	public void refreshBookList(){
		LibraryGUI.bookList.removeAllElements();
		double a = 0;
		for(int i = 0; i < LibraryGUI.sys.book.size(); i++){
			a =  LibraryGUI.sys.book.get(i).getCost();
			LibraryGUI.bookList.addElement(LibraryGUI.sys.book.get(i).getTitle() + ", " + LibraryGUI.sys.book.get(i).getAuthor()
					+ ", " + LibraryGUI.sys.book.get(i).getISBN() + ", " + LibraryGUI.sys.book.get(i).getCategory()
					+ ", " + LibraryGUI.sys.book.get(i).getBookRating() + ", " + LibraryGUI.formatter.format(a));
		}
	}
	
	/*inputCorrect makes sure that inputs which require integer or double values do not contain any characters
	 * @param input is the input String entered by the user
	 * @return returns true if the input consists of only numbers, false otherwise
	 */
	public boolean inputCorrect(String input){
		char [] ch = {'0','1','2','3','4','5','6','7','8','9'};
		boolean b = false;
		//for loop to run through the length of the input string
		for(int i = 0; i < input.length(); i++){
			b = false;
			//If any character in the string is not an integer then set the boolean b  to false
			for(int j = 0; j < ch.length; j++){
				if(input.charAt(i) == ch[j]){
					b = true;
					return true;
				}else{
					b = false;
				}
			}
		}
		if(!b)
			return false;
		return false;
	}
}

