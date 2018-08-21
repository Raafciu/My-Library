package presentation.user;

import business.user.User;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import control.user.UserPresenter;
import util.validator.AgeValidator;
import util.validator.NameValidator;
import util.validator.PeselValidator;

public class AddEditUserWindow extends Window {

    private static final String ADD_CAPTION = "Dodaj użytkownika";
    private static final String EDIT_CAPTION = "Edytuj użytkownika";
    private static final String WRONG_DATA = "Uzupełnij Dane";
    private static final String CONFIRM_MESSAGE = "Czy chcesz zatwierdzic formularz?";

    private final UserPresenter userPresenter;
    private final UserLayout userLayout;
    private User user;

    private HorizontalLayout buttonWindowContent;
    private HorizontalLayout horizontalFormWindowContent;
    private VerticalLayout windowContent;
    private VerticalLayout formWindowContent;

    private TextField peselField;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField ageField;

    private Button cancelWindowButton;
    private Button confirmWindowButton;

    AddEditUserWindow(UserPresenter userPresenter, UserLayout userLayout) {
        super.setCaption(ADD_CAPTION);
        super.setWidth(20, Unit.PERCENTAGE);
        super.setHeight(40, Unit.PERCENTAGE);
        super.setModal(true);
        super.setResizable(false);

        this.userPresenter = userPresenter;
        this.userLayout = userLayout;

        initComponents();
        initLayout();
        addListeners();
    }

    AddEditUserWindow(UserPresenter userPresenter, UserLayout userLayout, User user) {
        this(userPresenter, userLayout);
        super.setCaption(EDIT_CAPTION);
        this.user = user;

        fillFields();
    }

    private void initComponents() {
        windowContent = new VerticalLayout();
        buttonWindowContent = new HorizontalLayout();
        formWindowContent = new VerticalLayout();
        horizontalFormWindowContent = new HorizontalLayout();

        peselField = new TextField("Pesel");

        firstNameField = new TextField("Imie");
        lastNameField = new TextField("Nazwisko");
        ageField = new TextField("Wiek");

        initValidator();

        confirmWindowButton = new Button("Zatwierdz", FontAwesome.CHECK);
        cancelWindowButton = new Button("Anuluj", FontAwesome.REMOVE);
    }

    private void initValidator() {
        peselField.addValidator(new PeselValidator());
        peselField.setImmediate(true);

        firstNameField.addValidator(new NameValidator());
        firstNameField.setImmediate(true);

        lastNameField.addValidator(new NameValidator());
        lastNameField.setImmediate(true);

        ageField.addValidator(new AgeValidator());
        ageField.setImmediate(true);
    }

    private void initLayout() {
        buttonWindowContent.addComponents(confirmWindowButton, cancelWindowButton);
        buttonWindowContent.setMargin(new MarginInfo(true, false, false, false));
        formWindowContent.addComponents(peselField, firstNameField, lastNameField, ageField);
        horizontalFormWindowContent.addComponent(formWindowContent);

        windowContent.addComponents(horizontalFormWindowContent, buttonWindowContent);
        windowContent.setMargin(true);
        windowContent.setComponentAlignment(horizontalFormWindowContent, Alignment.MIDDLE_CENTER);
        windowContent.setComponentAlignment(buttonWindowContent, Alignment.BOTTOM_CENTER);

        peselField.focus();
        super.setContent(windowContent);
    }

    private void addListeners() {
        confirmWindowButton.addClickListener(event -> {
            if (peselField.isValid() && firstNameField.isValid()
                    && lastNameField.isValid() && ageField.isValid()) {
                try {
                    userPresenter.persist(user);
                    this.close();
                    Notification.show("Dziekujemy za wypelnienie formularza!");
                } catch (Exception e) {
                    Notification.show("Błąd podczas dodawania Użytkownika: " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            } else
                Notification.show(WRONG_DATA, Notification.Type.ERROR_MESSAGE);
        });

        cancelWindowButton.addClickListener(event -> super.close());
    }

    private void fillFields() {
        peselField.setValue(user.getPesel());
        firstNameField.setValue(user.getFirstName());
        lastNameField.setValue(user.getLastName());
        ageField.setValue(String.valueOf(user.getAge()));
    }

    @Override
    public void close() {
        userLayout.refreshTable();
        super.close();
    }
}
