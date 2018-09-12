package presentation.book;

import business.book.Book;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import control.book.BookPresenter;

public class AddEditBookWindow extends Window {

    private static final String ADD_CAPTION = "Dodaj książkę";
    private static final String EDIT_CAPTION = "Edytuj książkę";
    private static final String WRONG_DATA = "Uzupełnij Dane";

    private final BookPresenter bookPresenter;
    private final BookLayout bookLayout;
    private Book book;

    private HorizontalLayout buttonWindowContent;
    private HorizontalLayout horizontalFormWindowContent;
    private HorizontalLayout horizontaladdressFormWindowContent;
    private HorizontalLayout allFormContent;
    private VerticalLayout windowContent;
    private VerticalLayout formWindowContent;
    private VerticalLayout addressFormWindowContent;

    private Button cancelWindowButton;
    private Button confirmWindowButton;

    AddEditBookWindow(BookPresenter bookPresenter, BookLayout bookLayout) {
        super.setCaption(ADD_CAPTION);
        super.setWidth(35, Sizeable.Unit.PERCENTAGE);
        super.setHeight(60, Sizeable.Unit.PERCENTAGE);
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
        allFormContent = new HorizontalLayout();

        initValidator();

        confirmWindowButton = new Button("Zatwierdz", FontAwesome.CHECK);
        cancelWindowButton = new Button("Anuluj", FontAwesome.REMOVE);
    }


    private void initValidator() {

    }

    private void initLayout() {
        buttonWindowContent.addComponents(confirmWindowButton, cancelWindowButton);
        buttonWindowContent.setMargin(new MarginInfo(true, false, false, false));

        formWindowContent.addComponents();
        horizontalFormWindowContent.addComponent(formWindowContent);
        horizontalFormWindowContent.setMargin(new MarginInfo(false, true, false, false));

        addressFormWindowContent.addComponents();
        horizontaladdressFormWindowContent.addComponent(addressFormWindowContent);
        horizontaladdressFormWindowContent.setMargin(new MarginInfo(false, false, false, true));


        allFormContent.addComponents(horizontalFormWindowContent, horizontaladdressFormWindowContent);
        allFormContent.setComponentAlignment(horizontalFormWindowContent, Alignment.MIDDLE_LEFT);
        allFormContent.setComponentAlignment(horizontaladdressFormWindowContent, Alignment.MIDDLE_RIGHT);

        windowContent.addComponents(allFormContent, buttonWindowContent);
        windowContent.setMargin(new MarginInfo(true, false, true, false));
        windowContent.setComponentAlignment(allFormContent, Alignment.MIDDLE_CENTER);
        windowContent.setComponentAlignment(buttonWindowContent, Alignment.BOTTOM_CENTER);

        super.setContent(windowContent);
    }

    private void addListeners() {
    }


    private void fillFields() {


    }

    @Override
    public void close() {
        bookLayout.disableButtonsOnWindowClose();
        bookLayout.refreshTable();
        super.close();
    }
}
