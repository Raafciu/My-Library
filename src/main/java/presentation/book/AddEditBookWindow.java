package presentation.book;

import business.book.Book;
import business.category.Category;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import control.book.BookPresenter;
import util.enums.YesNoEnum;
import util.validator.IsbnValidator;
import util.validator.NameValidator;
import util.validator.PagesValidator;
import util.validator.PriceValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddEditBookWindow extends Window {

    private static final String ADD_CAPTION = "Dodaj książkę";
    private static final String EDIT_CAPTION = "Edytuj książkę";
    private static final String WRONG_DATA = "Uzupełnij Dane";

    private final BookPresenter bookPresenter;
    private final BookLayout bookLayout;
    private Book book;

    private HorizontalLayout buttonWindowContent;
    private HorizontalLayout horizontalFormContent;
    private VerticalLayout windowContent;
    private VerticalLayout formContent;

    private Button cancelWindowButton;
    private Button confirmWindowButton;
    private OptionGroup isAvailableGroup;

    private TextField isbnField;
    private TextField titleField;
    private TextField authorField;
    private TextField pagesField;
    private TextField priceField;
    private ComboBox categoryComboBox;
    private List<Category> categoryList;


    AddEditBookWindow(BookPresenter bookPresenter, BookLayout bookLayout) {
        super.setCaption(ADD_CAPTION);
        super.setWidth(35, Sizeable.Unit.PERCENTAGE);
        super.setHeight(80, Sizeable.Unit.PERCENTAGE);
        super.setModal(true);
        super.setResizable(false);

        this.bookLayout = bookLayout;
        this.bookPresenter = bookPresenter;

        initComponents();
        initLayout();
        addListeners();
    }

    AddEditBookWindow(BookPresenter bookPresenter, BookLayout bookLayout, Book book) {
        this(bookPresenter, bookLayout);
        super.setCaption(EDIT_CAPTION);
        this.book = book;

        fillFields();
    }

    private void initComponents() {
        windowContent = new VerticalLayout();
        buttonWindowContent = new HorizontalLayout();
        formContent = new VerticalLayout();
        horizontalFormContent = new HorizontalLayout();

        initFormComponents();
        initValidator();

    }

    private void initFormComponents() {
        confirmWindowButton = new Button("Zatwierdz", FontAwesome.CHECK);
        cancelWindowButton = new Button("Anuluj", FontAwesome.REMOVE);

        isbnField = new TextField("ISBN");
        titleField = new TextField("Tytuł");
        authorField = new TextField("Autor");
        pagesField = new TextField("Ilość stron");
        priceField = new TextField("Cena");
        isAvailableGroup = new OptionGroup("Dostępność");
        isAvailableGroup.addItems(YesNoEnum.TAK.getReadableValue(), YesNoEnum.NIE.getReadableValue());
        isAvailableGroup.setValue(YesNoEnum.TAK.getReadableValue());

        categoryList = new ArrayList<>();
        categoryList = bookPresenter.getAllCategories();
        categoryComboBox = new ComboBox("Kategoria");
        categoryComboBox.addItems(categoryList);
        categoryComboBox.setNullSelectionAllowed(false);
        categoryList.forEach(category -> categoryComboBox.setItemCaption(category, category.getCategoryName()));
        categoryComboBox.setValue(categoryList.get(0));
    }

    private void initValidator() {
        isbnField.addValidator(new IsbnValidator());
        isbnField.setMaxLength(13);
        isbnField.setImmediate(true);

        titleField.addValidator(new NameValidator());
        titleField.setMaxLength(50);
        titleField.setImmediate(true);

        authorField.addValidator(new NameValidator());
        authorField.setMaxLength(50);
        authorField.setImmediate(true);

        priceField.addValidator(new PriceValidator());
        priceField.setImmediate(true);

        pagesField.addValidator(new PagesValidator());
        pagesField.setImmediate(true);

    }

    private void initLayout() {
        buttonWindowContent.addComponents(confirmWindowButton, cancelWindowButton);
        buttonWindowContent.setMargin(new MarginInfo(true, false, false, false));

        formContent.addComponents(isbnField, titleField, authorField, pagesField, priceField, isAvailableGroup, categoryComboBox);
        horizontalFormContent.addComponent(formContent);

        windowContent.addComponents(horizontalFormContent, buttonWindowContent);
        windowContent.setMargin(new MarginInfo(true, false, true, false));
        windowContent.setComponentAlignment(horizontalFormContent, Alignment.MIDDLE_CENTER);
        windowContent.setComponentAlignment(buttonWindowContent, Alignment.BOTTOM_CENTER);

        isbnField.focus();
        super.setContent(windowContent);
    }

    private void addListeners() {
        confirmWindowButton.addClickListener(event -> {
            if (isbnField.isValid() && titleField.isValid() && pagesField.isValid()
                    && priceField.isValid()) {
                try {
                    Optional<Book> optionalBook = Optional.ofNullable(book);
                    if (!optionalBook.isPresent()) {
                        Book newBook = prepareNewBook();
                        bookPresenter.persist(newBook);
                    } else {
                        book = mergeBook();
                        bookPresenter.merge(book);
                    }
                    this.close();
                    Notification.show("Dziękujemy za wypełnienie formularza", Notification.Type.TRAY_NOTIFICATION);
                } catch (Exception e) {
                    Notification.show("Błąd podczas przetwarzania danych: " + e.getMessage(), Notification.Type.ERROR_MESSAGE);

                }
            } else
                Notification.show(WRONG_DATA, Notification.Type.ERROR_MESSAGE);
        });

        cancelWindowButton.addClickListener(event -> super.close());
    }

    private Book prepareNewBook() {
        BigDecimal bdPrice = new BigDecimal(priceField.getValue());
        Book newBook = new Book();
        newBook.setIsbn(isbnField.getValue());
        newBook.setTitle(titleField.getValue());
        newBook.setAuthor(authorField.getValue());
        newBook.setPages(Integer.parseInt(pagesField.getValue()));
        newBook.setPrice(bdPrice);
        if (isAvailableGroup.getValue().equals(YesNoEnum.TAK.getReadableValue()))
            newBook.setIsAvailable(YesNoEnum.TAK);
        else
            newBook.setIsAvailable(YesNoEnum.NIE);
        newBook.setBookCategory((Category) categoryComboBox.getValue());
        return newBook;
    }

    private Book mergeBook() {
        BigDecimal bdPrice = new BigDecimal(priceField.getValue());

        book.setIsbn(isbnField.getValue());
        book.setTitle(titleField.getValue());
        book.setAuthor(authorField.getValue());
        book.setPages(Integer.parseInt(pagesField.getValue()));
        book.setPrice(bdPrice);
        if (isAvailableGroup.getValue().equals(YesNoEnum.TAK.getReadableValue()))
            book.setIsAvailable(YesNoEnum.TAK);
        else
            book.setIsAvailable(YesNoEnum.NIE);
        book.setBookCategory((Category) categoryComboBox.getValue());
        return book;
    }

    private void fillFields() {
        isbnField.setValue(book.getIsbn());
        titleField.setValue(book.getTitle());
        authorField.setValue(book.getAuthor());
        pagesField.setValue(String.valueOf(book.getPages()));
        priceField.setValue(String.valueOf(book.getPrice()));
        if (book.getIsAvailable().equals(YesNoEnum.TAK))
            isAvailableGroup.setValue(YesNoEnum.TAK.getReadableValue());
        else
            isAvailableGroup.setValue(YesNoEnum.NIE.getReadableValue());
        categoryComboBox.setValue(book.getBookCategory().getCategoryName());
    }

    @Override
    public void close() {
        bookLayout.disableButtonsOnWindowClose();
        bookLayout.refreshTable();
        super.close();
    }
}
