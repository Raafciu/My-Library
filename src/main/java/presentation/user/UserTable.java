package presentation.user;

import business.user.User;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import control.user.UserPresenter;
import presentation.user.generator.TableAddressGenerator;
import presentation.user.generator.TableGroupGenerator;

public class UserTable extends Table {

    private final UserPresenter userPresenter;
    private static final String CAPTION = "Użytkownicy";

    private static final String PESEL = "pesel";
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String AGE = "age";
    private static final String POSITION = "position.positionName";
    private static final String ROLE = "role.roleName";
    private static final String ADDRESS = "address";
    private static final String GROUP = "group";

    private static final String PESEL_CAPTION = "pesel";
    private static final String FIRSTNAME_CAPTION = "Imie";
    private static final String LASTNAME_CAPTION = "Naziwsko";
    private static final String AGE_CAPTION = "Wiek";
    private static final String POSITION_CAPTION = "Stanowisko";
    private static final String ROLE_CAPTION = "Rola";
    private static final String ADDRESS_CAPTION = "Adres";
    private static final String GROUP_CAPTION = "Grupy";

    private BeanItemContainer<User> container;

    UserTable(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;

        super.setCaption(CAPTION);
        super.setHeight(400, Unit.PIXELS);
        super.setWidth(80, Unit.PERCENTAGE);
        super.setImmediate(true);

        initContainer();
    }

    private void initContainer() {
        container = new BeanItemContainer<>(User.class);
        container.addNestedContainerProperty(POSITION);
        container.addNestedContainerProperty(ROLE);
        super.addGeneratedColumn(GROUP, new TableGroupGenerator());
        super.addGeneratedColumn(ADDRESS, new TableAddressGenerator());
        container.addAll(userPresenter.getAll());
        setContainerDataSource(container);

        initColumns();

    }

    private void initColumns() {
        setVisibleColumns(
                PESEL,
                FIRSTNAME,
                LASTNAME,
                AGE,
                POSITION,
                ROLE,
                ADDRESS,
                GROUP
        );

        setColumnHeaders(
                PESEL_CAPTION,
                FIRSTNAME_CAPTION,
                LASTNAME_CAPTION,
                AGE_CAPTION,
                POSITION_CAPTION,
                ROLE_CAPTION,
                ADDRESS_CAPTION,
                GROUP_CAPTION
        );

    }

    public void refresh() {
        container.removeAllItems();
        container.addAll(userPresenter.getAll());
    }
}
