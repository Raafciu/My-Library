package presentation.user;

import business.address.Address;
import business.position.Position;
import business.role.Role;
import business.user.User;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import control.user.UserPresenter;
import util.validator.address.AddressValidator;
import util.validator.address.NumberOfBuildingValidator;
import util.validator.address.PostalCodeValidator;
import util.validator.user.AgeValidator;
import util.validator.user.NameValidator;
import util.validator.user.PeselValidator;

import java.util.List;
import java.util.Optional;

public class AddEditUserWindow extends Window {

    private static final String ADD_CAPTION = "Dodaj użytkownika";
    private static final String EDIT_CAPTION = "Edytuj użytkownika";
    private static final String WRONG_DATA = "Uzupełnij Dane";
    private static final String CONFIRM_MESSAGE = "Czy chcesz zatwierdzic formularz?";
    private static final String ADDRESS_CAPTION = "Adres:";

    private final UserPresenter userPresenter;
    private final UserLayout userLayout;
    private User user;

    List<Position> positionList;
    List<Role> roleList;

    private HorizontalLayout buttonWindowContent;
    private HorizontalLayout horizontalFormWindowContent;
    private HorizontalLayout horizontaladdressFormWindowContent;
    private HorizontalLayout allFormContent;
    private VerticalLayout windowContent;
    private VerticalLayout formWindowContent;
    private VerticalLayout addressFormWindowContent;

    private TextField peselField;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField ageField;
    private TextField countryField;
    private TextField cityField;
    private TextField streetField;
    private TextField numberOfBuildingField;
    private TextField postalCodeField;

    private ComboBox positionComboBox;
    private ComboBox roleComboBox;

    private Button cancelWindowButton;
    private Button confirmWindowButton;

    AddEditUserWindow(UserPresenter userPresenter, UserLayout userLayout) {
        super.setCaption(ADD_CAPTION);
        super.setWidth(35, Unit.PERCENTAGE);
        super.setHeight(60, Unit.PERCENTAGE);
        super.setModal(true);
        super.setResizable(false);

        this.userPresenter = userPresenter;
        this.userLayout = userLayout;

        initComponents();
        initLayout();
        addListeners();
    }

    AddEditUserWindow(UserPresenter userPresenter, UserLayout userLayout, User user) {
        this(userPresenter, userLayout);
        super.setCaption(EDIT_CAPTION);
        this.user = user;

        fillFields();
    }

    private void initComponents() {
        windowContent = new VerticalLayout();
        buttonWindowContent = new HorizontalLayout();
        allFormContent = new HorizontalLayout();

        initFormComponents();
        initAddressFormComponents();
        initValidator();

        confirmWindowButton = new Button("Zatwierdz", FontAwesome.CHECK);
        cancelWindowButton = new Button("Anuluj", FontAwesome.REMOVE);
    }

    private void initFormComponents() {

        formWindowContent = new VerticalLayout();
        horizontalFormWindowContent = new HorizontalLayout();

        peselField = new TextField("Pesel");
        firstNameField = new TextField("Imie");
        lastNameField = new TextField("Nazwisko");
        ageField = new TextField("Wiek");

        positionList = userPresenter.getAllPositions();
        positionComboBox = new ComboBox("Stanowisko");
        positionComboBox.addItems(positionList);
        positionList.forEach(position -> positionComboBox.setItemCaption(position, position.getPositionName()));

        roleList = userPresenter.getAllRoles();
        roleComboBox = new ComboBox("Rola");
        roleComboBox.addItems(roleList);
        roleList.forEach(role -> roleComboBox.setItemCaption(role, role.getRoleName()));

    }

    private void initAddressFormComponents() {
        addressFormWindowContent = new VerticalLayout();
        horizontaladdressFormWindowContent = new HorizontalLayout();

        countryField = new TextField("Kraj");
        cityField = new TextField("Miasto");
        streetField = new TextField("Ulica");
        numberOfBuildingField = new TextField("Numer Budynku");
        postalCodeField = new TextField("Kod Pocztowy");
    }

    private void initValidator() {
        peselField.addValidator(new PeselValidator());
        peselField.setImmediate(true);

        firstNameField.addValidator(new NameValidator());
        firstNameField.setImmediate(true);

        lastNameField.addValidator(new NameValidator());
        lastNameField.setImmediate(true);

        ageField.addValidator(new AgeValidator());
        ageField.setImmediate(true);

        countryField.addValidator(new AddressValidator());
        countryField.setImmediate(true);

        cityField.addValidator(new AddressValidator());
        cityField.setImmediate(true);

        streetField.addValidator(new AddressValidator());
        streetField.setImmediate(true);

        numberOfBuildingField.addValidator(new NumberOfBuildingValidator());
        numberOfBuildingField.setImmediate(true);

        postalCodeField.addValidator(new PostalCodeValidator());
        postalCodeField.setImmediate(true);
    }

    private void initLayout() {
        buttonWindowContent.addComponents(confirmWindowButton, cancelWindowButton);
        buttonWindowContent.setMargin(new MarginInfo(true, false, false, false));

        formWindowContent.addComponents(peselField, firstNameField, lastNameField, ageField, positionComboBox, roleComboBox);
        horizontalFormWindowContent.addComponent(formWindowContent);
        horizontalFormWindowContent.setMargin(new MarginInfo(false, true, false, false));

        addressFormWindowContent.addComponents(countryField, cityField, streetField, numberOfBuildingField, postalCodeField);
        horizontaladdressFormWindowContent.addComponent(addressFormWindowContent);
        horizontaladdressFormWindowContent.setMargin(new MarginInfo(false, false, false, true));


        allFormContent.addComponents(horizontalFormWindowContent, horizontaladdressFormWindowContent);
        allFormContent.setComponentAlignment(horizontalFormWindowContent, Alignment.MIDDLE_LEFT);
        allFormContent.setComponentAlignment(horizontaladdressFormWindowContent, Alignment.MIDDLE_RIGHT);

        windowContent.addComponents(allFormContent, buttonWindowContent);
        windowContent.setMargin(new MarginInfo(true, false, true, false));
        windowContent.setComponentAlignment(allFormContent, Alignment.MIDDLE_CENTER);
        windowContent.setComponentAlignment(buttonWindowContent, Alignment.BOTTOM_CENTER);

        peselField.focus();
        super.setContent(windowContent);
    }

    private void addListeners() {
        confirmWindowButton.addClickListener(event -> {
            if (peselField.isValid() && firstNameField.isValid() && lastNameField.isValid() && ageField.isValid()
                    && countryField.isValid() && cityField.isValid() && streetField.isValid()
                    && numberOfBuildingField.isValid() && postalCodeField.isValid()) {
                try {
                    Optional<User> optionalUser = Optional.ofNullable(user);
                    if (!optionalUser.isPresent()) {
                        User newUser = prepareNewUser();

                        userPresenter.persist(newUser);
                    } else {
                        user = mergeUser();
                        userPresenter.merge(user);
                    }
                    this.close();
                    Notification.show("Dziekujemy za wypelnienie formularza!");
                } catch (Exception e) {
                    Notification.show("Błąd podczas przetwarzania : " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            } else
                Notification.show(WRONG_DATA, Notification.Type.ERROR_MESSAGE);
        });

        cancelWindowButton.addClickListener(event -> super.close());
    }


    private User prepareNewUser() {

        Address newAddress = new Address();
        newAddress.setCountry(countryField.getValue());
        newAddress.setCity(cityField.getValue());
        newAddress.setStreet(streetField.getValue());
        newAddress.setNumberOfBuilding(Integer.parseInt(numberOfBuildingField.getValue()));
        newAddress.setPostalCode(postalCodeField.getValue());

        User newUser = new User();
        newUser.setPesel(peselField.getValue());
        newUser.setFirstName(firstNameField.getValue());
        newUser.setLastName(lastNameField.getValue());
        newUser.setAge(Integer.parseInt(ageField.getValue()));
        newUser.setPosition((Position) positionComboBox.getValue());
        newUser.setRole((Role) roleComboBox.getValue());
        newUser.setAddress(newAddress);

        return newUser;
    }

    private User mergeUser() {
        user.setPesel(peselField.getValue());
        user.setFirstName(firstNameField.getValue());
        user.setLastName(lastNameField.getValue());
        user.setAge(Integer.parseInt(ageField.getValue()));
        user.setPosition((Position) positionComboBox.getValue());
        user.setRole((Role) roleComboBox.getValue());
        user.getAddress().setCountry(countryField.getValue());
        user.getAddress().setCity(cityField.getValue());
        user.getAddress().setStreet(streetField.getValue());
        user.getAddress().setNumberOfBuilding(Integer.parseInt(numberOfBuildingField.getValue()));
        user.getAddress().setPostalCode(postalCodeField.getValue());

        return user;
    }


    private void fillFields() {

        String country = user.getAddress().getCountry();
        String city = user.getAddress().getCity();
        String street = user.getAddress().getStreet();
        String numberOfBuilding = String.valueOf(user.getAddress().getNumberOfBuilding());
        String postalCode = user.getAddress().getPostalCode();

        peselField.setValue(user.getPesel());
        firstNameField.setValue(user.getFirstName());
        lastNameField.setValue(user.getLastName());
        ageField.setValue(String.valueOf(user.getAge()));
        countryField.setValue(country);
        cityField.setValue(city);
        streetField.setValue(street);
        numberOfBuildingField.setValue(numberOfBuilding);
        postalCodeField.setValue(postalCode);
    }

    @Override
    public void close() {
        userLayout.disableButtonsOnWindowClose();
        userLayout.refreshTable();
        super.close();
    }
}
