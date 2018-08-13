package presentation;


import business.book.Book;
import business.category.Category;
import business.book.BookDAO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBTester {
    private BufferedReader brConsoleReader = null;
    private Properties props;
    private InitialContext ctx;
    {
        props = new Properties();
        try {
            props.load(new FileInputStream("jndi.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        brConsoleReader =
                new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {

        EJBTester ejbTester = new EJBTester();

        ejbTester.testEntityEjb();
    }

    private void showGUI(){
        System.out.println("**********************");
        System.out.println("Welcome to Book Store");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
    }

    private void testEntityEjb(){

        try {
            int choice = 1;

            BookDAO libraryBean = (BookDAO) ctx.lookup("LibraryPersistentBean/remote");

            while (choice != 2) {
                String bookName;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                if (choice == 1) {
                    System.out.print("Enter book name: ");
                    bookName = brConsoleReader.readLine();

                    Book book = new Book();
                    Category bookCategory = new Category();
                    bookCategory.setCategoryName("Fantazy");

                    book.setIsbn("1234567890123");
                    book.setTitle("Igrzyska Śmierci");
                    book.setPrice(50.23);
                    book.setPages(378);
                    book.setAuthor("Suzanne Collins");
                    book.setBookCategory(bookCategory);
                    libraryBean.persist(book);
                } else if (choice == 2) {
                    break;
                }
            }

            List<Book> booksList = libraryBean.getAll();

            System.out.println("Book(s) entered so far: " + booksList.size());
            int i = 0;
            for (Book book:booksList) {
                System.out.println((i+1)+". " + book.getTitle());
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if(brConsoleReader !=null){
                    brConsoleReader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}