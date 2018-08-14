package presentation.user;

import com.vaadin.server.ClassResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import control.user.UserPresenter;

public class UserLayout extends VerticalLayout {

    private final UserPresenter userPresenter;
    private final String BASEPATH = "C:\\Users\\Rafciu\\IdeaProjects\\Biblioteka";
    private HorizontalLayout buttonLayout;
    private HorizontalLayout buttonWindowContent;
    private VerticalLayout layout;
    private VerticalLayout windowContent;
    private VerticalLayout formWindowContent;

    private Table userTable;
    private Window persistWindow;

    private Button persistButton;
    private Button mergeButton;
    private Button removeButton;
    private Button cancelWindowButton;
    private Button confirmWindowButton;

    private TextField tfPesel;
    private TextField tffirstName;
    private TextField tfLastName;
    private TextField tfAge;

    public UserLayout(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        createButtons();
        createTextFields();
        createTable();
        createWindow();
    }

    private void initLayout() {
        buttonLayout = new HorizontalLayout();
        buttonLayout.setWidth(80, Unit.PERCENTAGE);
        buttonLayout.addComponents(persistButton, mergeButton, removeButton);
        buttonLayout.setMargin(true);
        buttonLayout.setSizeFull();
        buttonLayout.setComponentAlignment(persistButton, Alignment.MIDDLE_CENTER);
        buttonLayout.setComponentAlignment(mergeButton, Alignment.MIDDLE_CENTER);
        buttonLayout.setComponentAlignment(removeButton, Alignment.MIDDLE_CENTER);
        layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponents(buttonLayout, userTable);
        layout.setComponentAlignment(userTable, Alignment.MIDDLE_CENTER);
        addComponent(layout);
    }

    private void addListeners() {

        /**
         * Buton Listeners
         */
        persistButton.addClickListener(clickEvent -> {
            if (persistWindow.getParent() != null) {
                Notification.show(
                        "Okno juz jest otwarte", Notification.Type.WARNING_MESSAGE);
            } else {
                persistWindow.center();
                getUI().addWindow(persistWindow);
            }
            //userPresenter.persist();
            //Notification.show("Persist new User");
        });
        mergeButton.addClickListener(clickEvent -> {
            Notification.show("Merge new User");
        });
        removeButton.addClickListener(clickEvent -> Notification.show("Remove new User"));
        /**
         * Button Window Listeners
         */
        cancelWindowButton.addClickListener(event -> persistWindow.close());

        confirmWindowButton.addClickListener(event -> {
            persistWindow.close();
            Notification.show("Dziekujemy za wypelnienie formularza!");
        });
    }

    private void createButtons() {
        /**
         * Main Buttons
         */
        persistButton = new Button("Persist");
        mergeButton = new Button("Merge");
        removeButton = new Button("Remove");
        persistButton.setWidth(30, Unit.PERCENTAGE);
        mergeButton.setWidth(30, Unit.PERCENTAGE);
        mergeButton.setEnabled(false);
        removeButton.setWidth(30, Unit.PERCENTAGE);
        removeButton.setEnabled(false);
        persistButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
        persistButton.setIcon(new ClassResource("C:\\Users\\Rafciu\\IdeaProjects\\Biblioteka\\vaadin-icons-master\\assets\\png\\plus.png"));
        /**
         * Window Buttons
         */
        confirmWindowButton = new Button("Zatwierdz");
        cancelWindowButton = new Button("Anuluj");
    }

    private void createTextFields() {
        /**
         * Window TextFields
         */
        tfPesel = new TextField("Pesel");
        tffirstName = new TextField("Imie");
        tfLastName = new TextField("Nazwisko");
        tfAge = new TextField("Wiek");
    }

    private void createTable() {
        userTable = new Table("Uzytkownicy");
        userTable.setHeight(400, Unit.PIXELS);
        userTable.setWidth(80, Unit.PERCENTAGE);

        userTable.addContainerProperty("ID", Long.class, null);
        userTable.addContainerProperty("Pesel",String.class,null);
        userTable.addContainerProperty("Imie",String.class,null);
        userTable.addContainerProperty("Nazwisko",String.class,null);
        userTable.addContainerProperty("Wiek",Integer.class,null);

        userTable.addItem(userPresenter);

    }

    private void createWindow() {
        windowContent = new VerticalLayout();
        buttonWindowContent = new HorizontalLayout();
        formWindowContent = new VerticalLayout();

        persistWindow = new Window("Dodaj Uzytkownika");
        persistWindow.setWidth(20, Unit.PERCENTAGE);
        persistWindow.setHeight(40, Unit.PERCENTAGE);
        persistWindow.setContent(windowContent);

        buttonWindowContent.addComponents(confirmWindowButton, cancelWindowButton);
        formWindowContent.addComponents(tfPesel, tffirstName, tfLastName, tfAge);

        windowContent.addComponents(formWindowContent, buttonWindowContent);
        windowContent.setMargin(true);
        windowContent.setComponentAlignment(formWindowContent, Alignment.MIDDLE_CENTER);
        windowContent.setComponentAlignment(buttonWindowContent, Alignment.BOTTOM_CENTER);
    }
}
