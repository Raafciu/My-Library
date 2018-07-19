package control.user;

import business.user.User;
import control.IController;

import java.util.List;

public class UserController implements IController<User> {
    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void persist(User user) {

    }

    @Override
    public void merge(User user) {

    }

    @Override
    public void remove(User user) {

    }
}
