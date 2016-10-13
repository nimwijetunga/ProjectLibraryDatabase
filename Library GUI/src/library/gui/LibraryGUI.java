package library.gui;

/*
 * Add return on search books user has 
 */

import java.awt.Color;



import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import library.main.LibrarySystem;
import javax.swing.ImageIcon;



@SuppressWarnings("serial")

public class LibraryGUI extends JFrame{

        //To be able to format double values into currency (used in the ButtonResponse Class)
        static Locale locale = new Locale("en", "CA");      
        static NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

        private JPanel contentPane;

        //variables used in the ButtonResponse class
        private static double fine;
        public static int attempts = 0;
        public static boolean credVerified = false;

        //Creating an instance of the LibrarySystem class
        public static LibrarySystem sys = new LibrarySystem("Stephen Lewis Secondary School");

        //To add values to the JList's on the GUI 
        public static DefaultListModel <String> studentList = new DefaultListModel<String>();
        public static DefaultListModel <String> titleList = new  DefaultListModel<String>();
        public static DefaultListModel <String> bookList = new DefaultListModel<String>();
        public static DefaultListModel <String> booksAvailable = new DefaultListModel<String>();
        public static DefaultListModel <String> userBookList = new DefaultListModel<String>();
        public static DefaultListModel <String> bestBooks = new DefaultListModel<String>();
        public static DefaultListModel <String> searchedUser = new DefaultListModel<String>();

        //Group all the radio buttons together
        public static ButtonGroup group = new ButtonGroup();

        //Creating a JFrame
        public static LibraryGUI frame = new LibraryGUI();

        //Creating an instance of the ButtonResponse class
        private ButtonResponse btn = new ButtonResponse();

        //font for the entire UI
        private Font font = new Font("Yu Gothic UI Light", Font.PLAIN, 12);

        /*creating text fields for user inputs
         * Value of these text fields are read in the ButtonResponse Class and therefore needs to be static
         */
        public static JTextField textField;
        public static JTextField textField_1;
        public static JTextField textField_2;
        public static JTextField textField_4;
        public static JTextField textField_5;
        public static JTextField textField_6;
        public static JTextField textField_7;
        public static JTextField textField_9;
        public static JTextField textField_10;
        public static JTextField textField_11;
        public static JTextField textField_12;
        public static JTextField textField_13;
        public static JTextField textField_14;
        public static JTextField textField_15;
        public static JTextField textField_16;
        public static JTextField textField_17;
        public static JTextField textField_18;
        public static JTextField textField_19;
        public static JTextField textField_21;
        public static JTextField textField_22;
        public static JTextField textField_23;
        public static JTextField textField_24;
        public static JTextField textField_25;
        public static JTextField textField_3;
        public static JTextField textField_8;
        public static JTextField textField_20;

        /**Main Method
         * Launch the application.
         */
        public static void main(String[] args) {
                //setUIFont(new FontUIResource(new Font("Yu Gothic UI Light", 0, 12)));
                EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                try {
                                        frame.setVisible(true);//Make the frame visible
                                        frame.setResizable(false);//Restrict maximizing the frame 
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
                //Creating fake users in the system to be used for testing purposes
                int a = 665800;
                String [] first = {"Blair", "Larry", "Elwood", "David", "Antonia", "Arminda", "Fonda", "Danille"};
                String [] last = {"Greeno", " Corle", " Sartori", "Marcelino", "Sweetman", "Sacco", "Mccann", "Owens"};
                for(int i = 0; i < first.length; i++){
                        sys.createUser(a += 10, first[i], last[i]);
                        fine = 0.0;
                        studentList.addElement(first[i] + " " + last[i] + ", " + a
                                        + ", " + formatter.format(fine));
                }

                //Creating fake books for the system to be used for testing purposes
                String isbn = "1234";
                String [] author = {"Maryam Terry", "Hilario Sigman", "Casimira Danna", "Raul Burchfield", "Rodney Clift"};
                String [] title =  {"Gulliver's Travels", "Tom Jones", "The Black Sheep", "Nightmare Abbey", "Wuthering Heights"};
                String [] category = {"Action", "Adventure", "Action", "Drama", "Adventure"};

                int pRate = 0;
                double cost = 12.34;

                for(int i = 0; i < 5; i++){
                        int tempS = Integer.valueOf(isbn);
                        tempS += 1;
                        isbn = Integer.toString(tempS);
                        sys.addBook(title[i], author[i], isbn, category[i], pRate += 1, cost += 2.13);
                        bookList.addElement(title[i] + ", " + author[i] + ", " + isbn + ", "
                                        + category[i] + ", " + pRate + ", " + formatter.format(cost));
                        booksAvailable.addElement(isbn + ", " + "Not Checked Out");
                }
        }

        /*Constructor for the LibraryGUI class
         *Creates objects displayed on the frame
         */
        public LibraryGUI() {

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
                setBounds(100, 100, 1000, 400);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);

                setUIFont(new FontUIResource(font));

                setTitle(sys.getSystemName() + " Library System");
                contentPane.setLayout(null);

                JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
                tabbedPane.setBounds(10, 11, 950, 339);
                contentPane.add(tabbedPane);

                JPanel panel_1 = new JPanel();
                tabbedPane.addTab("User", null, panel_1, null);
                tabbedPane.setBackground(new Color(255, 111, 105));
                panel_1.setLayout(null);
                
                JLabel UserBackground = new JLabel("New label");
                UserBackground.setBounds(-2, 1, 945, 311);
                panel_1.add(UserBackground);
                UserBackground.setIcon(new ImageIcon(LibraryGUI.class.getResource("/library/res/User.png")));

                JLabel label = new JLabel("Add Students");
                label.setBounds(10, 11, 104, 23);
                panel_1.add(label);

                JLabel label_2 = new JLabel("Student Number");
                label_2.setBounds(10, 50, 93, 32);
                panel_1.add(label_2);

                JLabel label_3 = new JLabel("Last Name");
                label_3.setBounds(10, 136, 93, 32);
                panel_1.add(label_3);

                JLabel label_4 = new JLabel("First Name");
                label_4.setBounds(10, 93, 93, 32);
                panel_1.add(label_4);

                textField = new JTextField();
                textField.setBounds(115, 56, 86, 20);
                panel_1.add(textField);
                textField.setColumns(10);

                textField_1 = new JTextField();
                textField_1.setBounds(113, 99, 86, 20);
                textField_1.setColumns(10);
                panel_1.add(textField_1);

                textField_2 = new JTextField();
                textField_2.setBounds(113, 142, 86, 20);
                textField_2.setColumns(10);
                panel_1.add(textField_2);

                JButton btnAddUser = new JButton("Add User");
                btnAddUser.setBounds(63, 196, 89, 23);
                btnAddUser.setBackground(new Color(255,255,204));
                btnAddUser.addActionListener(btn);
                panel_1.add(btnAddUser);

                JPanel panel_2 = new JPanel();
                panel_2.setBounds(257, 11, 371, 300);
                panel_1.add(panel_2);
                panel_2.setLayout(null);

                JList<String> list = new JList<String>(studentList);
                list.setBounds(6, 16, 370, 271);
                panel_2.add(list);

                JPanel panel_5 = new JPanel();
                panel_5.setBounds(638, 11, 276, 300);
                panel_5.setLayout(null);
                panel_1.add(panel_5);

                JLabel label_5 = new JLabel("Delete User/ Pay Fine");
                label_5.setBounds(10, 32, 181, 28);
                panel_5.add(label_5);

                JLabel label_6 = new JLabel("Student Number");
                label_6.setBounds(30, 89, 104, 28);
                panel_5.add(label_6);

                textField_10 = new JTextField();
                textField_10.setColumns(10);
                textField_10.setBounds(155, 92, 98, 24);
                panel_5.add(textField_10);

                JButton DeleteUser = new JButton("Delete User");
                DeleteUser.addActionListener(btn);
                DeleteUser.setBounds(30, 168, 116, 23);
                panel_5.add(DeleteUser);

                JButton button_2 = new JButton("Pay Fine");
                button_2.addActionListener(btn);
                button_2.setBounds(158, 168, 118, 23);
                panel_5.add(button_2);

                JPanel panel = new JPanel();
                panel.setBackground(UIManager.getColor("Button.background"));
                tabbedPane.addTab("Book", null, panel, null);
                panel.setLayout(null);
                
                JLabel lblNewLabel_2 = new JLabel("New label");
                lblNewLabel_2.setBounds(-4, 4, 944, 307);
                panel.add(lblNewLabel_2);
                lblNewLabel_2.setIcon(new ImageIcon(LibraryGUI.class.getResource("/library/res/Book.png")));

                JPanel panel_3 = new JPanel();
                panel_3.setBounds(0, 0, 245, 307);
                panel.add(panel_3);
                panel_3.setLayout(null);

                JLabel lblNewLabel = new JLabel("Add Books");
                lblNewLabel.setBounds(10, 0, 86, 16);
                panel_3.add(lblNewLabel);

                JLabel lblName = new JLabel("Name");
                lblName.setBackground(Color.BLACK);
                lblName.setForeground(Color.BLACK);
                lblName.setBounds(10, 43, 46, 14);
                panel_3.add(lblName);

                JLabel lblAuthor = new JLabel("Author");
                lblAuthor.setBounds(10, 79, 46, 14);
                panel_3.add(lblAuthor);

                JLabel lblIsbn = new JLabel("ISBN");
                lblIsbn.setBounds(10, 111, 46, 24);
                panel_3.add(lblIsbn);

                JLabel lblCategory = new JLabel("Category");
                lblCategory.setBounds(10, 156, 79, 14);
                panel_3.add(lblCategory);

                JLabel lblRating = new JLabel("Rating 1(Worst) - 5(Best)");
                lblRating.setBounds(27, 181, 158, 16);
                panel_3.add(lblRating);

                JLabel lblCost = new JLabel("Cost");
                lblCost.setBounds(10, 245, 46, 14);
                panel_3.add(lblCost);

                textField_4 = new JTextField();
                textField_4.setBackground(Color.WHITE);
                textField_4.setBounds(99, 47, 86, 18);
                panel_3.add(textField_4);
                textField_4.setColumns(10);

                textField_5 = new JTextField();
                textField_5.setColumns(10);
                textField_5.setBounds(99, 79, 86, 21);
                panel_3.add(textField_5);

                textField_6 = new JTextField();
                textField_6.setColumns(10);
                textField_6.setBounds(99, 119, 86, 16);
                panel_3.add(textField_6);

                textField_7 = new JTextField();
                textField_7.setColumns(10);
                textField_7.setBounds(99, 158, 86, 16);
                panel_3.add(textField_7);

                textField_9 = new JTextField();
                textField_9.setColumns(10);
                textField_9.setBounds(99, 242, 86, 20);
                panel_3.add(textField_9);

                JButton btnAddBook = new JButton("Add Book");
                btnAddBook.addActionListener(btn);
                btnAddBook.setBounds(58, 273, 89, 23);
                panel_3.add(btnAddBook);

                JRadioButton radioButton = new JRadioButton("1");
                radioButton.setBackground(Color.LIGHT_GRAY);
                group.add(radioButton);
                radioButton.setBounds(6, 204, 36, 23);
                panel_3.add(radioButton);

                JRadioButton radioButton_1 = new JRadioButton("2");
                group.add(radioButton_1);
                radioButton_1.setBounds(44, 204, 36, 23);
                panel_3.add(radioButton_1);

                JRadioButton radioButton_2 = new JRadioButton("3");
                group.add(radioButton_2);
                radioButton_2.setBounds(89, 204, 36, 23);
                panel_3.add(radioButton_2);

                JRadioButton radioButton_3 = new JRadioButton("4");
                group.add(radioButton_3);
                radioButton_3.setBounds(127, 204, 36, 23);
                panel_3.add(radioButton_3);

                JRadioButton radioButton_4 = new JRadioButton("5");
                group.add(radioButton_4);
                radioButton_4.setBounds(165, 204, 36, 23);
                panel_3.add(radioButton_4);

                JPanel panel_4 = new JPanel();
                panel_4.setBorder(new TitledBorder(null, "Books", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                panel_4.setBackground(Color.WHITE);
                panel_4.setBounds(245, 0, 412, 307);
                panel.add(panel_4);
                panel_4.setLayout(new GridLayout(1, 0, 0, 0));

                JList<String> list_2 = new JList<String>(bookList);
                panel_4.add(list_2);

                JPanel panel_6 = new JPanel();
                panel_6.setBackground(UIManager.getColor("Button.background"));
                panel_6.setBounds(659, 0, 286, 307);
                panel.add(panel_6);
                panel_6.setLayout(null);

                JLabel lblDeleteBook = new JLabel("Delete Book");
                lblDeleteBook.setBounds(96, 5, 95, 28);
                panel_6.add(lblDeleteBook);

                JLabel lblIsbn_1 = new JLabel("ISBN");
                lblIsbn_1.setBounds(10, 83, 62, 33);
                panel_6.add(lblIsbn_1);

                textField_11 = new JTextField();
                textField_11.setBounds(105, 89, 86, 22);
                panel_6.add(textField_11);
                textField_11.setColumns(10);

                JButton btnDeleteBook = new JButton("Delete Book");
                btnDeleteBook.addActionListener(btn);
                btnDeleteBook.setBounds(97, 169, 108, 28);
                panel_6.add(btnDeleteBook);

                JPanel panel_7 = new JPanel();
                tabbedPane.addTab("Checkout/Return Book", null, panel_7, null);
                panel_7.setLayout(null);
                
                JLabel lblNewLabel_3 = new JLabel("New label");
                lblNewLabel_3.setIcon(new ImageIcon(LibraryGUI.class.getResource("/library/res/Checkout Book and Return Book.png")));
                lblNewLabel_3.setBounds(0, 0, 945, 309);
                panel_7.add(lblNewLabel_3);

                JList<String> list_1 = new JList<String>(booksAvailable);
                list_1.setBorder(new TitledBorder(null, "Books Available", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                list_1.setBounds(271, 11, 327, 286);
                panel_7.add(list_1);

                JLabel lblCheckOutBook = new JLabel("Check Out Book");
                lblCheckOutBook.setBounds(10, 11, 133, 48);
                panel_7.add(lblCheckOutBook);

                JLabel lblStudentNumber = new JLabel("Student Number");
                lblStudentNumber.setBounds(10, 70, 104, 48);
                panel_7.add(lblStudentNumber);

                JLabel label_7 = new JLabel("Book ISBN");
                label_7.setBounds(10, 163, 104, 48);
                panel_7.add(label_7);

                textField_12 = new JTextField();
                textField_12.setBounds(155, 86, 86, 18);
                panel_7.add(textField_12);
                textField_12.setColumns(10);

                textField_13 = new JTextField();
                textField_13.setColumns(10);
                textField_13.setBounds(155, 177, 86, 18);
                panel_7.add(textField_13);

                JButton btnCheckoutBook = new JButton("Checkout Book");
                btnCheckoutBook.addActionListener(btn);
                btnCheckoutBook.setBounds(80, 226, 145, 36);
                panel_7.add(btnCheckoutBook);

                JLabel label_8 = new JLabel("Date (dd/mm/yyyy)");
                label_8.setBounds(10, 116, 133, 42);
                panel_7.add(label_8);

                textField_14 = new JTextField();
                textField_14.setColumns(10);
                textField_14.setBounds(155, 131, 86, 18);
                panel_7.add(textField_14);

                JPanel panel_8 = new JPanel();
                panel_8.setBounds(608, 21, 315, 276);
                panel_7.add(panel_8);
                panel_8.setLayout(null);

                JLabel label_9 = new JLabel("Return Book");
                label_9.setBounds(10, 0, 119, 23);
                panel_8.add(label_9);

                JLabel label_10 = new JLabel("Student Number");
                label_10.setBounds(10, 37, 104, 48);
                panel_8.add(label_10);

                JLabel label_11 = new JLabel("Date (dd/mm/yyyy)");
                label_11.setBounds(10, 96, 133, 42);
                panel_8.add(label_11);

                JLabel label_12 = new JLabel("Book ISBN");
                label_12.setBounds(10, 150, 104, 48);
                panel_8.add(label_12);

                textField_15 = new JTextField();
                textField_15.setColumns(10);
                textField_15.setBounds(147, 77, 85, 18);
                panel_8.add(textField_15);

                textField_16 = new JTextField();
                textField_16.setColumns(10);
                textField_16.setBounds(147, 132, 85, 18);
                panel_8.add(textField_16);

                textField_17 = new JTextField();
                textField_17.setColumns(10);
                textField_17.setBounds(147, 187, 85, 18);
                panel_8.add(textField_17);

                JButton button = new JButton("Return Book");
                button.addActionListener(btn);
                button.setBounds(28, 216, 121, 34);
                panel_8.add(button);

                JButton btnLostBook = new JButton("Lost Book");
                btnLostBook.addActionListener(btn);
                btnLostBook.setBounds(178, 217, 113, 34);
                panel_8.add(btnLostBook);

                JPanel panel_9 = new JPanel();
                tabbedPane.addTab("Search Books", null, panel_9, null);
                panel_9.setLayout(null);
                
                JLabel lblNewLabel_4 = new JLabel("New label");
                lblNewLabel_4.setBounds(0, 0, 946, 310);
                panel_9.add(lblNewLabel_4);
                lblNewLabel_4.setIcon(new ImageIcon(LibraryGUI.class.getResource("/library/res/book search.png")));

                JPanel panel_10 = new JPanel();
                panel_10.setBounds(10, 5, 925, 291);
                panel_9.add(panel_10);
                panel_10.setLayout(null);

                JLabel lblNewLabel_1 = new JLabel("Search By Author");
                lblNewLabel_1.setBounds(10, 11, 148, 38);
                panel_10.add(lblNewLabel_1);

                JLabel lblAuthorName = new JLabel("Author Name");
                lblAuthorName.setBounds(10, 60, 95, 24);
                panel_10.add(lblAuthorName);

                textField_18 = new JTextField();
                textField_18.setBounds(144, 64, 85, 18);
                panel_10.add(textField_18);
                textField_18.setColumns(10);

                JList<String> list_3 = new JList<String>(titleList);
                list_3.setBorder(new TitledBorder(null, "List of Books", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                list_3.setBounds(491, 11, 402, 261);
                panel_10.add(list_3);

                JLabel label_13 = new JLabel("Search By Category");
                label_13.setBounds(10, 163, 148, 38);
                panel_10.add(label_13);

                JLabel label_14 = new JLabel("Category");
                label_14.setBounds(10, 212, 95, 24);
                panel_10.add(label_14);

                textField_19 = new JTextField();
                textField_19.setColumns(10);
                textField_19.setBounds(144, 213, 85, 18);
                panel_10.add(textField_19);

                JButton btnSearch = new JButton("Search By Author");
                btnSearch.addActionListener(btn);
                btnSearch.setBounds(7, 111, 147, 25);
                panel_10.add(btnSearch);

                JButton button_1 = new JButton("Search By Category");
                button_1.addActionListener(btn);
                button_1.setBounds(7, 254, 147, 25);
                panel_10.add(button_1);

                JLabel label_1 = new JLabel("Search By Name");
                label_1.setBounds(291, 11, 148, 38);
                panel_10.add(label_1);

                JLabel label_15 = new JLabel("Search Key");
                label_15.setBounds(291, 60, 95, 24);
                panel_10.add(label_15);

                textField_3 = new JTextField();
                textField_3.setColumns(10);
                textField_3.setBounds(390, 65, 85, 18);
                panel_10.add(textField_3);

                JButton button_5 = new JButton("Search By Name");
                button_5.addActionListener(btn);
                button_5.setBounds(304, 110, 145, 23);
                panel_10.add(button_5);
                
                                JPanel panel_11 = new JPanel();
                                tabbedPane.addTab("Books User Has", null, panel_11, null);
                                panel_11.setLayout(null);
                                                
                                                JLabel lbBookUserHasBackground = new JLabel("New label");
                                                lbBookUserHasBackground.setBounds(-20, -46, 978, 355);
                                                panel_11.add(lbBookUserHasBackground);
                                                lbBookUserHasBackground.setIcon(new ImageIcon(LibraryGUI.class.getResource("/library/res/Book User Has.png")));
                                
                                                JPanel panel_12 = new JPanel();
                                                panel_12.setBounds(10, 11, 326, 285);
                                                panel_11.add(panel_12);
                                                panel_12.setLayout(null);
                                                
                                                                JLabel lblSearchBooksUser = new JLabel("Search Books User Has");
                                                                lblSearchBooksUser.setBounds(10, 11, 183, 44);
                                                                panel_12.add(lblSearchBooksUser);
                                                                
                                                                                JLabel lblStudentNumber_1 = new JLabel("Student Number");
                                                                                lblStudentNumber_1.setBounds(10, 83, 101, 33);
                                                                                panel_12.add(lblStudentNumber_1);
                                                                                
                                                                                                textField_21 = new JTextField();
                                                                                                textField_21.setBounds(160, 90, 86, 20);
                                                                                                panel_12.add(textField_21);
                                                                                                textField_21.setColumns(10);
                                                                                                
                                                                                                                JButton btnSearch_1 = new JButton("Search");
                                                                                                                btnSearch_1.setBounds(78, 127, 89, 23);
                                                                                                                btnSearch_1.addActionListener(btn);
                                                                                                                panel_12.add(btnSearch_1);
                                                                                                                
                                                                                                                                JPanel panel_13 = new JPanel();
                                                                                                                                panel_13.setBounds(345, 11, 578, 285);
                                                                                                                                panel_11.add(panel_13);
                                                                                                                                panel_13.setLayout(null);
                                                                                                                                
                                                                                                                                                JList<String> list_4 = new JList<String>(userBookList);
                                                                                                                                                list_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Specified User's Books", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
                                                                                                                                                list_4.setBounds(10, 11, 558, 263);
                                                                                                                                                panel_13.add(list_4);

                JPanel panel_17 = new JPanel();
                tabbedPane.addTab("Search Users", null, panel_17, null);
                panel_17.setLayout(null);
                
                JLabel lblNewLabel_6 = new JLabel("New label");
                lblNewLabel_6.setBounds(0, 0, 945, 309);
                panel_17.add(lblNewLabel_6);
                lblNewLabel_6.setIcon(new ImageIcon(LibraryGUI.class.getResource("/library/res/Search User.png")));

                JPanel panel_18 = new JPanel();
                panel_18.setBounds(12, 13, 334, 296);
                panel_17.add(panel_18);
                panel_18.setLayout(null);

                JLabel label_16 = new JLabel("Search By Student Number");
                label_16.setBounds(12, 13, 185, 39);
                panel_18.add(label_16);

                JLabel lblStudentNumber_2 = new JLabel("Student Number");
                lblStudentNumber_2.setBounds(12, 65, 106, 25);
                panel_18.add(lblStudentNumber_2);

                textField_8 = new JTextField();
                textField_8.setColumns(10);
                textField_8.setBounds(135, 65, 85, 18);
                panel_18.add(textField_8);

                JButton btnSearchByNumber = new JButton("Search By Number");
                btnSearchByNumber.addActionListener(btn);
                btnSearchByNumber.setBounds(56, 104, 142, 23);
                panel_18.add(btnSearchByNumber);

                JLabel label_22 = new JLabel("Search By Name");
                label_22.setBounds(12, 143, 185, 39);
                panel_18.add(label_22);

                JLabel label_23 = new JLabel("Student Name");
                label_23.setBounds(12, 195, 106, 25);
                panel_18.add(label_23);

                textField_20 = new JTextField();
                textField_20.setColumns(10);
                textField_20.setBounds(134, 192, 85, 18); 
                panel_18.add(textField_20);

                JButton button_6 = new JButton("Search By Student Name");
                button_6.addActionListener(btn);
                button_6.setBounds(24, 229, 185, 22);
                panel_18.add(button_6);

                JPanel panel_19 = new JPanel();
                panel_19.setBounds(356, 13, 577, 283);
                panel_17.add(panel_19);
                panel_19.setLayout(null);

                JList<String> list_6 = new JList<String>(searchedUser);
                list_6.setBorder(new TitledBorder(null, "             ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                list_6.setBounds(12, 10, 539, 257);
                panel_19.add(list_6);

                JPanel panel_14 = new JPanel();
                tabbedPane.addTab("Best Books", null, panel_14, null);
                panel_14.setLayout(null);
                
                JLabel lblNewLabel_5 = new JLabel("New label");
                lblNewLabel_5.setBounds(0, 0, 945, 309);
                panel_14.add(lblNewLabel_5);
                lblNewLabel_5.setIcon(new ImageIcon(LibraryGUI.class.getResource("/library/res/best book2.png")));

                JPanel panel_15 = new JPanel();
                panel_15.setBounds(10, 11, 493, 285);
                panel_14.add(panel_15);
                panel_15.setLayout(null);

                JLabel lblCompareTwoBooks = new JLabel("Compare Two Books");
                lblCompareTwoBooks.setBounds(10, 11, 193, 30);
                panel_15.add(lblCompareTwoBooks);

                JLabel lblBookIsbn = new JLabel("Book 1 ISBN");
                lblBookIsbn.setBounds(10, 53, 85, 21);
                panel_15.add(lblBookIsbn);

                textField_22 = new JTextField();
                textField_22.setBounds(119, 53, 85, 18);
                panel_15.add(textField_22);
                textField_22.setColumns(10);

                JLabel label_17 = new JLabel("Book 2 ISBN");
                label_17.setBounds(10, 93, 85, 21);
                panel_15.add(label_17);

                textField_23 = new JTextField();
                textField_23.setBounds(119, 94, 85, 18);
                textField_23.setColumns(10);
                panel_15.add(textField_23);

                JButton btnCompar = new JButton("Compare");
                btnCompar.setBounds(62, 124, 90, 23);
                btnCompar.addActionListener(btn);
                panel_15.add(btnCompar);

                JLabel label_18 = new JLabel("Best Books By Author");
                label_18.setBounds(10, 159, 193, 30);
                panel_15.add(label_18);

                JLabel label_19 = new JLabel("Author");
                label_19.setBounds(10, 200, 85, 21);
                panel_15.add(label_19);

                textField_24 = new JTextField();
                textField_24.setBounds(119, 200, 85, 18);
                textField_24.setColumns(10);
                panel_15.add(textField_24);

                JButton button_3 = new JButton("Compare By Author");
                button_3.setBounds(47, 224, 159, 21);
                button_3.addActionListener(btn);
                panel_15.add(button_3);

                JLabel label_20 = new JLabel("Best Books By Category");
                label_20.setBounds(270, 11, 193, 30);
                panel_15.add(label_20);

                JLabel label_21 = new JLabel("Category");
                label_21.setBounds(265, 53, 85, 21);
                panel_15.add(label_21);

                textField_25 = new JTextField();
                textField_25.setBounds(363, 55, 85, 18);
                textField_25.setColumns(10);
                panel_15.add(textField_25);

                JButton button_4 = new JButton("Compare By Category");
                button_4.setBounds(294, 85, 173, 21);
                button_4.addActionListener(btn);
                panel_15.add(button_4);

                JPanel panel_16 = new JPanel();
                panel_16.setBounds(513, 11, 410, 285);
                panel_14.add(panel_16);
                panel_16.setLayout(null);

                JList<String> list_5 = new JList<String>(bestBooks);
                list_5.setBorder(new TitledBorder(null, "Best Book(s)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                list_5.setBounds(10, 11, 405, 263);
                panel_16.add(list_5);
                
                JLabel border = new JLabel("New label");
                border.setBounds(0, -21, 1017, 382);
                contentPane.add(border);
                border.setIcon(new ImageIcon(LibraryGUI.class.getResource("/library/res/Border.png")));

                MousePicture mouse = new MousePicture();
                tabbedPane.addMouseListener(mouse);
                tabbedPane.addMouseMotionListener(mouse);
        }

        /*setUIFont sets a font for all the GUI components
         * @param f is the font which is set for the entire UI
         */
        public static void setUIFont(FontUIResource f) {
                @SuppressWarnings("rawtypes")
                Enumeration keys = UIManager.getDefaults().keys();
                while (keys.hasMoreElements()) {
                        Object key = keys.nextElement();
                        Object value = UIManager.get(key);
                        if (value instanceof FontUIResource) {
                                FontUIResource orig = (FontUIResource) value;
                                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                                UIManager.put(key, new FontUIResource(font));
                        }
                }
        }
}
