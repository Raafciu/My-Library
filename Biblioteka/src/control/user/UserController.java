package control.user;

import business.Database;
import business.user.User;
import control.IController;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

public class UserController implements IController<User> {
    @Override
    public Map<String,User> getAll() {
        return Database.getInstance().getUsers();
    }

    @Override
    public void persist(User user) {
        Database.getInstance().persist(user);
    }

    @Override
    public void merge(User user) {
        Database.getInstance().merge(user);
    }

    @Override
    public void remove(User user) {
        Database.getInstance().remove(user);
    }
}
