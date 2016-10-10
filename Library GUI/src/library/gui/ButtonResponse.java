package library.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ButtonResponse implements ActionListener{

	int stuNum, rating, pos = 0;
	double fine, cost, confirm;
	String iden, dateC, dateR;

	JTextField username = new JTextField();

	JPasswordField password = new JPasswordField();

	public void actionPerformed(ActionEvent e) {
		String temp = " ";
		if (e.getActionCommand().equals("Add User")) {
			if(LibraryGUI.textField.getText().equals("") || LibraryGUI.textField_1.getText().equals("") || LibraryGUI.textField_2.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			
			
			while(LibraryGUI.credVerified == false && LibraryGUI.attempts <= 5){
				login();
			}
			if(LibraryGUI.attempts >= 5){
				JOptionPane.showMessageDialog(null, "Access Denied, Restart System");
				return;
			}

			temp = LibraryGUI.textField.getText();
			stuNum = Integer.valueOf(temp);
			if(!LibraryGUI.sys.userExists(stuNum)){
				LibraryGUI.sys.createUser(stuNum, LibraryGUI.textField_1.getText(), LibraryGUI.textField_2.getText());
				LibraryGUI.studentList.addElement(LibraryGUI.textField_1.getText() + " " + LibraryGUI.textField_2.getText() + ", " + Integer.toString(stuNum)
				+ ", " + LibraryGUI.formatter.format(fine));
				JOptionPane.showMessageDialog(null, "User Added");
			}else{
				JOptionPane.showMessageDialog(null, "User Already Exists");
			}
		}

		if(e.getActionCommand().equals("Add Book")){
			if(LibraryGUI.textField_4.getText().equals("") || LibraryGUI.textField_5.getText().equals("") || LibraryGUI.textField_6.getText().equals("")
					|| LibraryGUI.textField_7.getText().equals("") || LibraryGUI.textField_9.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}

			while(LibraryGUI.credVerified == false && LibraryGUI.attempts <= 5){
				login();
			}
			if(LibraryGUI.attempts >= 5){
				JOptionPane.showMessageDialog(null, "Access Denied, Restart System");
				return;
			}


			rating = getSelectedButton();
			temp = LibraryGUI.textField_9.getText();
			cost = Double.valueOf(temp);
			if(getSelectedButton() == 0){
				JOptionPane.showMessageDialog(null, "Enter a Rating");
				return;
			}
			if(!LibraryGUI.sys.bookExists(LibraryGUI.textField_6.getText())){
				double a = Double.valueOf(LibraryGUI.textField_9.getText());
				LibraryGUI.sys.addBook(LibraryGUI.textField_4.getText(), LibraryGUI.textField_5.getText(),LibraryGUI.textField_6.getText(), 
						LibraryGUI.textField_7.getText(), rating, cost);
				LibraryGUI.bookList.addElement(LibraryGUI.textField_4.getText() + ", " + LibraryGUI.textField_5.getText() + ", " + LibraryGUI.textField_6.getText()
				+ ", " + LibraryGUI.textField_7.getText() + ", " + rating + ", " + LibraryGUI.formatter.format(a));
				JOptionPane.showMessageDialog(null, "Book Added");
			}else{
				JOptionPane.showMessageDialog(null, "Book Already Exists");
			}
		}

		if(e.getActionCommand().equals("Delete User")){
			if(LibraryGUI.textField_10.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}

			while(LibraryGUI.credVerified == false && LibraryGUI.attempts <= 5){
				login();
			}
			if(LibraryGUI.attempts >= 5){
				JOptionPane.showMessageDialog(null, "Access Denied, Restart System");
				return;
			}

			temp = LibraryGUI.textField_10.getText();
			stuNum = Integer.valueOf(temp);
			if(!LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "User Does Not Exist");
			}else{
				for(int i = 0; i < LibraryGUI.sys.user.size(); i++){
					if(LibraryGUI.sys.user.get(i).getStudentNumber() == stuNum){
						pos = i;
						break;
					}
				}
				confirm = JOptionPane.showConfirmDialog(null, "Do You Want to Delete User?");
				if(confirm == JOptionPane.YES_OPTION){
					LibraryGUI.sys.deleteUser(stuNum);
					LibraryGUI.studentList.removeElementAt(pos);
					JOptionPane.showMessageDialog(null, "User Deleted");
				}
			}
		}

		if(e.getActionCommand().equals("Delete Book")){
			if(LibraryGUI.textField_11.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}

			while(LibraryGUI.credVerified == false && LibraryGUI.attempts <= 5){
				login();
			}
			if(LibraryGUI.attempts >= 5){
				JOptionPane.showMessageDialog(null, "Access Denied, Restart System");
				return;
			}

			iden = LibraryGUI.textField_11.getText();
			if(!LibraryGUI.sys.bookExists(iden)){
				JOptionPane.showMessageDialog(null, "Book Does Not Exist");
			}else{
				for(int i = 0; i < LibraryGUI.sys.book.size(); i++){
					if(LibraryGUI.sys.book.get(i).getISBN().equals(iden)){
						pos = i;
						break;
					}
				}
				confirm = JOptionPane.showConfirmDialog(null, "Do You Want to Delete Book?");
				if(confirm == JOptionPane.YES_OPTION){
					LibraryGUI.sys.removeBook(iden);
					LibraryGUI.bookList.removeElementAt(pos);
					JOptionPane.showMessageDialog(null, "Book Removed");
				}
			}
		}

		if(e.getActionCommand().equals("Checkout Book")){
			if(LibraryGUI.textField_12.getText().equals("") || LibraryGUI.textField_13.getText().equals("") || LibraryGUI.textField_14.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			temp = LibraryGUI.textField_12.getText();
			stuNum = Integer.parseInt(temp);
			iden = LibraryGUI.textField_13.getText();
			dateC = LibraryGUI.textField_14.getText();

			if(LibraryGUI.sys.hasThreeBooks(stuNum)){
				JOptionPane.showMessageDialog(null, "User Already Has 3 Books Checked Out");
				return;
			}

			if(!LibraryGUI.sys.bookExists(iden)){
				JOptionPane.showMessageDialog(null, "Book Does not Exist");
				return;
			}

			for(int i = 0; i < LibraryGUI.sys.book.size(); i++){
				if(LibraryGUI.sys.book.get(i).getISBN().equals(iden)){
					if(LibraryGUI.sys.book.get(i).getCheckedOut() == true){
						JOptionPane.showMessageDialog(null, "Book Already Checked Out");
						return;
					}
				}
			}
			if(LibraryGUI.sys.canCheckOut(stuNum) == false){
				JOptionPane.showMessageDialog(null, "Fines Over $5");
				return;
			}

			if(!LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "Invalid Student Number");
				return;
			}

			for(int i = 0; i < LibraryGUI.sys.user.size(); i++){
				if(LibraryGUI.sys.user.get(i).getStudentNumber() == stuNum){
					LibraryGUI.sys.checkOutBook(iden, stuNum, dateC);
					JOptionPane.showMessageDialog(null, "Book Checked Out");
					break;
				}
			}
			
			refreshListCheckout();
		}

		if(e.getActionCommand().equals("Return Book")){
			if(LibraryGUI.textField_15.getText().equals("") || LibraryGUI.textField_16.getText().equals("") || LibraryGUI.textField_17.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			temp = LibraryGUI.textField_15.getText();
			stuNum = Integer.parseInt(temp);
			iden = LibraryGUI.textField_17.getText();
			dateR = LibraryGUI.textField_16.getText();

			if(!LibraryGUI.sys.bookExists(iden)){
				JOptionPane.showMessageDialog(null, "Book Does not Exist");
				return;
			}

			for(int i = 0; i < LibraryGUI.sys.book.size(); i++){
				if(LibraryGUI.sys.book.get(i).getISBN().equals(iden)){
					if(LibraryGUI.sys.book.get(i).getCheckedOut() == false){
						JOptionPane.showMessageDialog(null, "Book Was Not Checked Out");
						return;
					}
				}
			}
			if(!(LibraryGUI.sys.userHasBook(stuNum, iden))){
				JOptionPane.showMessageDialog(null, "User Does Not Have The Book");
				return;
			}
			
			if(!LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "Invalid Student Number");
				return;
			}
			
			for(int i = 0; i < LibraryGUI.sys.user.size(); i++){
				if(LibraryGUI.sys.user.get(i).getStudentNumber() == stuNum){
					LibraryGUI.sys.returnBook(iden, stuNum, dateR);
					JOptionPane.showMessageDialog(null, "Book Returned");
					break;
				}
			}
			
			refreshListCheckout();
			refreshUserList();
		}

		if(e.getActionCommand().equals("Lost Book")){
			if(LibraryGUI.textField_15.getText().equals("") || LibraryGUI.textField_17.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			temp = LibraryGUI.textField_15.getText();
			stuNum = Integer.parseInt(temp);
			iden = LibraryGUI.textField_17.getText();
			if(!LibraryGUI.sys.bookExists(iden) || !LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "Book/User Does not Exist");
				return;
			}else if(!LibraryGUI.sys.userHasBook(stuNum, iden)){
				JOptionPane.showMessageDialog(null, "User Does Not Have The Book");
				return;
			}

			LibraryGUI.sys.bookLost(stuNum, iden);
			JOptionPane.showMessageDialog(null, "Fine Added");
			
			refreshListCheckout();
			refreshUserList();
			refreshBookList();
		}

		if(e.getActionCommand().equals("Search By Author")){
			if(LibraryGUI.textField_18.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			boolean x = false;
			for(int i = 0; i < LibraryGUI.sys.book.size(); i++){
				if(LibraryGUI.sys.book.get(i).getAuthor().equalsIgnoreCase(LibraryGUI.textField_18.getText())){
					x = true;
				}
			}
			if(x == false){
				JOptionPane.showMessageDialog(null, "Could Not Find Author");
				return;
			}
			LibraryGUI.titleList.removeAllElements();
			ArrayList <String> list = LibraryGUI.sys.booksByAuthor(LibraryGUI.textField_18.getText());
			for(int i = 0; i < list.size(); i++){
				LibraryGUI.titleList.addElement(list.get(i));
			}
		}

		if(e.getActionCommand().equals("Search By Category")){
			if(LibraryGUI.textField_19.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			LibraryGUI.titleList.removeAllElements();
			boolean a = false;
			for(int i = 0; i < LibraryGUI.sys.book.size(); i++){
				if(LibraryGUI.sys.book.get(i).getCategory().equalsIgnoreCase(LibraryGUI.textField_19.getText())){
					LibraryGUI.titleList.addElement(LibraryGUI.sys.book.get(i).getTitle());
					a = true;
				}
			}
			if(a == false){
				JOptionPane.showMessageDialog(null, "Category Not Available");
				return;
			}
			LibraryGUI.titleList.removeAllElements();
			ArrayList<String> lst = LibraryGUI.sys.booksByCategory(LibraryGUI.textField_19.getText());
			for(int i = 0; i < lst.size(); i++){
				LibraryGUI.titleList.addElement(lst.get(i));
			}
		}

		if(e.getActionCommand().equals("Pay Fine")){
			if(LibraryGUI.textField_10.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}

			while(LibraryGUI.credVerified == false && LibraryGUI.attempts <= 5){
				login();
			}
			if(LibraryGUI.attempts >= 5){
				JOptionPane.showMessageDialog(null, "Access Denied, Restart System");
				return;
			}

			temp = LibraryGUI.textField_10.getText();
			stuNum = Integer.valueOf(temp);

			if(!LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "User Does Not Exist");
				return;
			}

			for(int i = 0; i < LibraryGUI.sys.user.size(); i++){
				if(LibraryGUI.sys.user.get(i).getStudentNumber() == stuNum){
					if(LibraryGUI.sys.user.get(i).getFinesBalance() < 5.0){
						JOptionPane.showMessageDialog(null, "Fine is Under $5");
						return;
					}
				}
			}

			JOptionPane.showMessageDialog(null, "Fine Paid");
			LibraryGUI.sys.payFine(stuNum);
		}

		if(e.getActionCommand().equals("Search")){
			if(LibraryGUI.textField_21.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			LibraryGUI.userBookList.removeAllElements();
			temp = LibraryGUI.textField_21.getText();
			stuNum = Integer.valueOf(temp);
			if(!LibraryGUI.sys.userExists(stuNum)){
				JOptionPane.showMessageDialog(null, "User Does Not Exist");
				return;
			}
			ArrayList<String> lst = LibraryGUI.sys.booksuserHas(stuNum);
			for(int i = 0; i < lst.size(); i++){
				LibraryGUI.userBookList.addElement(lst.get(i));
			}
			JOptionPane.showMessageDialog(null, "Search Completed");
		}

		if(e.getActionCommand().equals("Compare")){
			if(LibraryGUI.textField_22.getText().equals("") || LibraryGUI.textField_23.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			LibraryGUI.bestBooks.removeAllElements();
			String iden1 = LibraryGUI.textField_22.getText();
			String iden2 = LibraryGUI.textField_23.getText();
			if(!LibraryGUI.sys.bookExists(iden1) || !LibraryGUI.sys.bookExists(iden2)){
				JOptionPane.showMessageDialog(null, "Book(s) Does Not Exist");
				return;
			}
			LibraryGUI.bestBooks.addElement(LibraryGUI.sys.compareTwoBooks(iden1, iden2));
		}

		if(e.getActionCommand().equals("Compare By Author")){
			if(LibraryGUI.textField_24.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			LibraryGUI.bestBooks.removeAllElements();
			temp = LibraryGUI.sys.compareByAuthor(LibraryGUI.textField_24.getText());
			if(temp.equals(" ")){
				JOptionPane.showMessageDialog(null, "Author Does Not Exist");
				return;
			}else{
				LibraryGUI.bestBooks.addElement(temp);
			}
		}

		if(e.getActionCommand().equals("Compare By Category")){
			if(LibraryGUI.textField_25.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Missing Fields");
				return;
			}
			LibraryGUI.bestBooks.removeAllElements();
			temp = LibraryGUI.sys.compareByCategory(LibraryGUI.textField_25.getText());
			if(temp.equals(" ")){
				JOptionPane.showMessageDialog(null, "Cateogry Does Not Exist");
				return;
			}else{
				LibraryGUI.bestBooks.addElement(temp);
			}
		}
	}

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

	public Hashtable<String, String> login(JFrame frame) {
		Hashtable<String, String> logininformation = new Hashtable<String, String>();

		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("Username", SwingConstants.RIGHT));
		label.add(new JLabel("Password", SwingConstants.RIGHT));
		panel.add(label, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));

		controls.add(username);

		controls.add(password);
		panel.add(controls, BorderLayout.CENTER);
		
		if(LibraryGUI.attempts >= 1){
			username.setBackground(new Color(240,50,50));
			password.setBackground(new Color(240,50,50));
		}

		JOptionPane.showMessageDialog(frame, panel, "Login, Attempt: " + LibraryGUI.attempts + "/5", JOptionPane.QUESTION_MESSAGE);

		logininformation.put("user", username.getText());
		logininformation.put("pass", new String(password.getPassword()));
		return logininformation;
	}

	public boolean inputCorrect(String user, char [] pass){
		String passString = new String(pass);
		if(!user.equals("admin") || !passString.equals("admin")){
			return false;
		}
		return true;
	}

	public void login(){
		login(LibraryGUI.frame);
		if(!(inputCorrect(username.getText(), password.getPassword()))){
			//JOptionPane.showMessageDialog(null, "Username/Password is Incorrect");
			LibraryGUI.attempts++;
		}
		else
			LibraryGUI.credVerified = true;
	}
	
	public void refreshListCheckout(){
		LibraryGUI.booksAvailable.removeAllElements();
		String temp = " ";
		for(int i = 0; i < LibraryGUI.sys.book.size(); i++){
			temp = String.valueOf(LibraryGUI.sys.book.get(i).getCheckedOut());
			if(temp.equals("true"))
				temp = "Checked Out";
			else
				temp = "Not Checked Out";
			LibraryGUI.booksAvailable.addElement(LibraryGUI.sys.book.get(i).getISBN() + ", " + temp);
		}
	}
	
	public void refreshUserList(){
		double tempD = 0.0;
		LibraryGUI.studentList.removeAllElements();
		for(int i = 0; i < LibraryGUI.sys.user.size(); i++){
			tempD = LibraryGUI.sys.user.get(i).getFinesBalance();
			LibraryGUI.studentList.addElement(LibraryGUI.sys.user.get(i).getFirstName() + " " + LibraryGUI.sys.user.get(i).getLastName() +
					", " + LibraryGUI.sys.user.get(i).getStudentNumber() + ", " + LibraryGUI.formatter.format(tempD));
		}
	}
	
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
}

