package library.control.user;

import library.business.Database;
import library.business.user.User;
import library.control.IController;
import library.control.exception.KeyNotFoundException;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Map;
import java.util.Optional;

public class UserController implements IController<User> {

    @Override
    public Map<String, User> getAll() {
        return Database.getInstance().getUsers();
    }

    @Override
    public Optional<User> getById(String id) {
        return null;
    }

    @Override
    public void persist(User user) {
        try {
            Database.getInstance().persistUser(user);
        } catch (KeyAlreadyExistsException e) {
            System.out.println("Nie mozna dodac uzytkownika");
        }
    }

    @Override
    public void merge(User user) {
        try {
            Database.getInstance().mergeUser(user);
        } catch (KeyNotFoundException e) {
            System.out.println("Nie mozna edytowac uzytkownika");
        }
    }

    @Override
    public void remove(User user) {
        try {
            Database.getInstance().removeUser(user);
        } catch (KeyNotFoundException e) {
            System.out.println("Nie mozna usunac uzytkownika");
        }
    }
}
