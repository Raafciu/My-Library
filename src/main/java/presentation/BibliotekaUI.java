package presentation;

import business.user.User;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import control.user.UserPresenter;
import util.constans.BibliotekaUtil;

import javax.inject.Inject;
import java.util.List;
import java.util.Locale;

@CDIUI("")
@Theme(BibliotekaUtil.THEME)
@Widgetset(BibliotekaUtil.WIDGETSET)
public class BibliotekaUI extends UI {


    @Inject
    private UserPresenter userPresenter;

    private VerticalLayout layout;
    private int clickCounter = 0;
    private Label clickCounterLabel;

    @Override
    protected void init(VaadinRequest request) {
        layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        layout.addComponent(new Label("Hello World!"));
        layout.addComponent(new Label("Greetings from server."));
        layout.addComponent(new Label("I have "
                + Runtime.getRuntime().availableProcessors()
                + " processors and "
                + (Runtime.getRuntime().totalMemory() / 1000000)
                + " MB total memory."));

        Button button = new Button("Click Me");
        button.addClickListener((Button.ClickListener) event -> {
            clickCounter++;
            clickCounterLabel.setValue("Clicks: " + clickCounter);
            Notification.show("Thank you for clicking.");
        });

        Button encjaButton = new Button("Pobierz użytkowników");
        encjaButton.addClickListener(clickEvent -> {
            List<User> userList = userPresenter.getAllUsers();
            userList.forEach(user -> layout.addComponent(new Label(user.toString())));
        });

        layout.addComponents(button, encjaButton);
        layout.addComponent(clickCounterLabel = new Label("Clicks: 0"));
    }
}
