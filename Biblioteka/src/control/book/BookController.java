package control.book;

import business.Database;
import business.book.Book;
import control.IController;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Map;

public class BookController implements IController<Book> {

    @Override
    public Map<String,Book> getAll() {
        return Database.getInstance().getBooks();
    }

    @Override
    public void persist(Book book) {
        try {
            Database.getInstance().persist(book);
        } catch (KeyAlreadyExistsException e) {
            System.out.println("Jebło");
        }
        System.out.println("Dodałeś książkę: \n" + book.toString());
    }

    @Override
    public void merge(Book book) {
        Database.getInstance().merge(book);
    }

    @Override
    public void remove(Book book) {
        try{
            Database.getInstance().remove(book);
        }catch(KeyAlreadyExistsException e){
            System.out.println("Jebneło");
        }
        System.out.println("Usunales książkę");
    }

}
