package presentation.book;

import business.book.Book;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import control.book.BookPresenter;

import java.util.Optional;

public class BookLayout extends VerticalLayout {

    private final BookPresenter bookPresenter;

    private VerticalLayout containerLayout;
    private HorizontalLayout buttonLayout;

    private AddEditBookWindow window;
    private BookTable bookTable;

    private Button persistButton;
    private Button mergeButton;
    private Button removeButton;

    public BookLayout(BookPresenter bookPresenter) {
        this.bookPresenter = bookPresenter;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        createButtons();
        createTable();
    }

    private void initLayout() {

        buttonLayout = new HorizontalLayout();
        buttonLayout.addComponents(persistButton, mergeButton, removeButton);
        buttonLayout.setMargin(true);
        buttonLayout.setSpacing(true);
        containerLayout = new VerticalLayout();
        containerLayout.setMargin(true);
        containerLayout.setSpacing(true);
        containerLayout.addComponents(buttonLayout, bookTable);
        containerLayout.setComponentAlignment(bookTable, Alignment.MIDDLE_CENTER);
        addComponent(containerLayout);
    }

    private void addListeners() {
        persistButton.addClickListener(clickEvent -> {
            window = new AddEditBookWindow(bookPresenter, this);
            getUI().addWindow(window);
        });

        mergeButton.addClickListener(clickEvent -> {
            Book book = (Book) bookTable.getValue();
            window = new AddEditBookWindow(bookPresenter, this, book);
            getUI().addWindow(window);
        });

        removeButton.addClickListener(clickEvent -> {
            Book book = (Book) bookTable.getValue();
            try {
                bookPresenter.remove(book);
            } catch (Exception e) {
                Notification.show("Błąd podczas przetwarzania : " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
            refreshTable();
            disableButtonsOnWindowClose();
        });

        bookTable.addValueChangeListener(event -> {
            Optional<Object> optional = Optional.ofNullable(bookTable.getValue());
            if (optional.isPresent()) {
                persistButton.setEnabled(false);
                mergeButton.setEnabled(true);
                removeButton.setEnabled(true);
            } else {
                persistButton.setEnabled(true);
                mergeButton.setEnabled(false);
                removeButton.setEnabled(false);
            }
        });
    }

    private void createButtons() {
        persistButton = new Button("Dodaj", FontAwesome.PLUS);
        persistButton.setWidth(30, Unit.PERCENTAGE);

        mergeButton = new Button("Edytuj", FontAwesome.EDIT);
        mergeButton.setWidth(30, Unit.PERCENTAGE);
        mergeButton.setEnabled(false);

        removeButton = new Button("Usun", FontAwesome.REMOVE);
        removeButton.setWidth(30, Unit.PERCENTAGE);
        removeButton.setEnabled(false);
    }

    private void createTable() {
        bookTable = new BookTable(bookPresenter);
    }

    void refreshTable() {
        bookTable.refresh();
    }

    void disableButtonsOnWindowClose() {
        persistButton.setEnabled(true);
        mergeButton.setEnabled(false);
        removeButton.setEnabled(false);
    }
}
