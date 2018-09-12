package presentation;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import presentation.page.BookPage;
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
    private Button userButton;
    private Button bookButton;

    @Override
    protected void init(VaadinRequest request) {
        initComponents();
        initLayout();
        addListeners();
    }

    private void userNavigator() {
        initNavigator();
        navigator.addView(UserPage.VIEW_ID, new UserPage());
        UI.getCurrent().setNavigator(navigator);
        UI.getCurrent().getNavigator().navigateTo(UserPage.VIEW_ID);
    }

    private void bookNavigator() {
        initNavigator();
        navigator.addView(BookPage.VIEW_ID, new BookPage());
        UI.getCurrent().setNavigator(navigator);
        UI.getCurrent().getNavigator().navigateTo(BookPage.VIEW_ID);
    }

    private void initNavigator() {
        viewDisplay = new ComponentContainerViewDisplay(layout);
        navigator = new Navigator(UI.getCurrent(), viewDisplay);
        navigator.addProvider(cdiViewProvider);
    }

    private void initLayout() {
        layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponents(userButton, bookButton);
        layout.setComponentAlignment(userButton, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(bookButton, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    private void initComponents() {

        userButton = new Button("Strefa dla Pracownika");
        bookButton = new Button("Biblioteka");
    }

    private void addListeners() {
        userButton.addClickListener(event -> userNavigator());
        bookButton.addClickListener(event -> bookNavigator());
    }
}