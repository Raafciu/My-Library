package presentation.user.generator;

import business.address.Address;
import business.user.User;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

import java.util.Optional;

public class TableAddressGenerator implements Table.ColumnGenerator {

    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {
        User user = (User) itemId;
        String labelContent;
        Label label = new Label();

        Optional<Address> optionalAddress = Optional.ofNullable(user.getAddress());

        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            labelContent = address.getCountry() +
                    " ul. " + address.getStreet() +
                    " " + address.getNumberOfBuilding() +
                    " " + address.getPostalCode() +
                    " " + address.getCity();
        } else
            labelContent = "Brak";

        label.setValue(labelContent);

        return label;
    }
}

