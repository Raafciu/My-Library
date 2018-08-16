package presentation.user;

import com.vaadin.ui.Table;
import control.user.UserPresenter;

public class UserTable extends Table {

    private static final String CAPTION = "Użytkownicy";

    public UserTable(UserPresenter userPresenter) {
        super.setCaption(CAPTION);
        super.setHeight(400, Unit.PIXELS);
        super.setWidth(80, Unit.PERCENTAGE);
        super.setImmediate(true);
    }

    public void refresh() {
        super.removeAllItems();
        //zaciaganie danych na nowo
    }
}
