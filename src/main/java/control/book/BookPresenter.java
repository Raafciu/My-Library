package control.book;

import business.book.Book;
import business.book.BookDAO;
import business.category.Category;
import business.category.CategoryDAO;

import javax.inject.Inject;
import java.util.List;

public class BookPresenter {

    @Inject
    private BookDAO bookDAO;

    @Inject
    private CategoryDAO categoryDAO;

    public List<Book> getAllBooks() {
        return bookDAO.getAll();
    }

    public void persist(Book book) {
        bookDAO.persist(book);
    }

    public void merge(Book book) {
        bookDAO.merge(book);
    }

    public void remove(Book book) {
        bookDAO.remove(book);
    }

    public List<Category> getAllCategories(){
        return categoryDAO.getAll();
    }
}
