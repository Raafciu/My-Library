package presentation;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import presentation.page.UserPage;
import util.constans.BibliotekaUtil;

import javax.inject.Inject;

@CDIUI("")
@Theme(BibliotekaUtil.THEME)
@Widgetset(BibliotekaUtil.WIDGETSET)
public class BibliotekaUI extends UI {

    @Inject
    private CDIViewProvider cdiViewProvider;

    private ComponentContainerViewDisplay viewDisplay;
    private Navigator navigator;

    private VerticalLayout layout;

    @Override
    protected void init(VaadinRequest request) {
        layout = new VerticalLayout();
        layout.setSpacing(true);
        setContent(layout);
        initNavigator();
    }

    private void initNavigator() {
        viewDisplay = new ComponentContainerViewDisplay(layout);
        navigator = new Navigator(UI.getCurrent(), viewDisplay);
        navigator.addProvider(cdiViewProvider);
        navigator.addView(UserPage.VIEW_ID, new UserPage());

        UI.getCurrent().setNavigator(navigator);
        UI.getCurrent().getNavigator().navigateTo(UserPage.VIEW_ID);
    }
}


//
//private int clickCounter = 0;
//    private Label clickCounterLabel;
//
//    VerticalLayout layout = new VerticalLayout();
//        layout.setMargin(true);
//        layout.setSpacing(true);
//    setContent(layout);
//
//        layout.addComponent(new Label("Hello World!"));
//        layout.addComponent(new Label("Greetings from server."));
//        layout.addComponent(new Label("I have "
//                                              + Runtime.getRuntime().availableProcessors()
//                + " processors and "
//                        + (Runtime.getRuntime().totalMemory() / 1000000)
//            + " MB total memory."));
//
//    Button button = new Button("Click Me");
//        button.addClickListener((Button.ClickListener) event -> {
//        clickCounter++;
//        clickCounterLabel.setValue("Clicks: " + clickCounter);
//        Notification.show("Thank you for clicking.");
//    });
//
//    Button encjaButton = new Button("Pobierz użytkowników");
//        encjaButton.addClickListener(clickEvent -> {
//        List<User> userList = userPresenter.();
//        userList.forEach(user -> layout.addComponent(new Label(user.toString())));
//    });
//
//        layout.addComponents(button, encjaButton);
//        layout.addComponent(clickCounterLabel = new Label("Clicks: 0"));
//}