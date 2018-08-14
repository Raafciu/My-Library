package presentation.page;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import control.user.UserPresenter;
import presentation.user.UserLayout;

import javax.inject.Inject;

@CDIView(UserPage.VIEW_ID)
public class UserPage extends VerticalLayout implements View {

    public static final String VIEW_ID = "uzytkownik";

    @Inject
    private UserPresenter userPresenter;

    public void init() {
        UserLayout userLayout = new UserLayout(userPresenter);
        addComponent(userLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init();
    }
}
