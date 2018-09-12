package presentation.book;

import business.book.Book;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import control.book.BookPresenter;

public class BookTable extends Table {

    private final BookPresenter bookPresenter;
    private static final String CAPTION = "Książki";

    private BeanItemContainer<Book> container;

    private static final String ISBN = "isbn";
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String PAGES = "pages";
    private static final String PRICE = "price";
    private static final String IS_AVAILABLE = "isAvailable.readableValue";
    private static final String CATEGORY = "bookCategory.categoryName";

    private static final String ISBN_CAPTION = "isbn";
    private static final String TITLE_CAPTION = "Tytul";
    private static final String AUTHOR_CAPTION = "Autor";
    private static final String PAGES_CAPTION = "Ilość stron";
    private static final String PRICE_CAPTION = "Cena";
    private static final String IS_AVAILABLE_CAPTION = "Dostępność";
    private static final String CATEGORY_CAPTION = "Kategoria";

    BookTable(BookPresenter bookPresenter) {
        this.bookPresenter = bookPresenter;

        super.setCaption(CAPTION);
        super.setHeight(400, Unit.PIXELS);
        super.setWidth(80, Unit.PERCENTAGE);
        super.setImmediate(true);

        initContainer();
    }

    private void initContainer() {
        container = new BeanItemContainer<>(Book.class);
        container.addNestedContainerProperty(CATEGORY);
        container.addNestedContainerProperty(IS_AVAILABLE);
        container.addAll(bookPresenter.getAllBooks());
        setContainerDataSource(container);

        initColumns();
    }

    private void initColumns() {
        setVisibleColumns(
                ISBN,
                TITLE,
                AUTHOR,
                PAGES,
                PRICE,
                IS_AVAILABLE,
                CATEGORY
        );

        setColumnHeaders(
                ISBN_CAPTION,
                TITLE_CAPTION,
                AUTHOR_CAPTION,
                PAGES_CAPTION,
                PRICE_CAPTION,
                IS_AVAILABLE_CAPTION,
                CATEGORY_CAPTION
        );
    }

    public void refresh() {
        container.removeAllItems();
        container.addAll(bookPresenter.getAllBooks());
    }
}
