package library.control.book;

import library.business.Database;
import library.business.book.Book;
import library.control.IController;
import library.control.exception.KeyNotFoundException;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Map;
import java.util.Optional;

public class BookController implements IController<Book> {

    @Override
    public Map<String, Book> getAll() {
        return Database.getInstance().getBooks();
    }

    @Override
    public Optional<Book> getById(String isbn) {
        Optional<Book> optionalBook = Optional.empty();
        try {
            optionalBook = Optional.ofNullable(Database.getInstance().getBookById(isbn));
        } catch (KeyNotFoundException e) {
            System.out.println("Nie można wykonac operacji na  książce");
        }

        return optionalBook;
    }

    @Override
    public void persist(Book book) {
        try {
            Database.getInstance().persistBook(book);
        } catch (KeyAlreadyExistsException e) {
            System.out.println("Nie można dodać książki");
        }
        System.out.println("Dodałeś książkę: \n" + book.toString());
    }

    @Override
    public void merge(Book book) {
        try {
            Database.getInstance().mergeBook(book);
        } catch (KeyNotFoundException e) {
            System.out.println("Nie można edytować książki");
        }
        System.out.println("Zedytowałeś książke pomyślnie. Jej parametry: \n"+book.toString());
    }

    @Override
    public void remove(Book book) {
        try {
            Database.getInstance().removeBook(book);
        } catch (KeyNotFoundException e) {
            System.out.println("Nie można usunąć książki");
        }
        System.out.println("Usunales książkę");
    }

}
