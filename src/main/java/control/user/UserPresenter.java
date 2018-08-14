package control.user;

import business.user.User;
import business.user.UserDAO;
import util.interfaces.ICRUDOperation;

import javax.inject.Inject;
import java.util.List;

public class UserPresenter implements ICRUDOperation<User> {

    @Inject
    private UserDAO userDAO;

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public void persist(User user) {
        userDAO.persist(user);
    }

    @Override
    public void merge(User user) {
        userDAO.merge(user);
    }

    @Override
    public void remove(User user) {
        userDAO.remove(user);
    }
}
