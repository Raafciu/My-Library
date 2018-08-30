package presentation.user;

import business.user.User;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import control.user.UserPresenter;

import java.util.Optional;

public class UserLayout extends VerticalLayout {

    private final UserPresenter userPresenter;

    private HorizontalLayout buttonLayout;
    private VerticalLayout layout;

    private AddEditUserWindow window;
    private UserTable userTable;

    private Button persistButton;
    private Button mergeButton;
    private Button removeButton;

    public UserLayout(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;

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
        layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponents(buttonLayout, userTable);
        layout.setComponentAlignment(userTable, Alignment.MIDDLE_CENTER);
        addComponent(layout);
    }

    private void addListeners() {
        persistButton.addClickListener(clickEvent -> {
            window = new AddEditUserWindow(userPresenter, this);
            getUI().addWindow(window);
        });

        mergeButton.addClickListener(clickEvent -> {
            User user = (User) userTable.getValue();
            window = new AddEditUserWindow(userPresenter, this, user);
            getUI().addWindow(window);
        });

        removeButton.addClickListener(clickEvent -> {
            User user = (User) userTable.getValue();
            try {
                userPresenter.remove(user);
            } catch (Exception e) {
                Notification.show("Błąd podczas przetwarzania : " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
            refreshTable();
            disableButtonsOnWindowClose();
        });

        userTable.addValueChangeListener(event -> {
            Optional<Object> optional = Optional.ofNullable(userTable.getValue());
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
        userTable = new UserTable(userPresenter);
    }

    void refreshTable() {
        userTable.refresh();
    }

    void disableButtonsOnWindowClose() {
        persistButton.setEnabled(true);
        mergeButton.setEnabled(false);
        removeButton.setEnabled(false);
    }
}
