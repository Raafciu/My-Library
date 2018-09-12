package presentation.page;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import control.book.BookPresenter;
import presentation.book.BookLayout;

import javax.inject.Inject;

@CDIView(BookPage.VIEW_ID)
public class BookPage extends VerticalLayout implements View {

    public static final String VIEW_ID = "Ksiazki";

    @Inject
    private BookPresenter bookPresenter;

    private void init() {
        BookLayout bookLayout = new BookLayout(bookPresenter);
        addComponent(bookLayout);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init();
    }
}
