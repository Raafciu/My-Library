package control.user;

import business.user.User;
import business.user.UserDAO;

import javax.inject.Inject;
import java.util.List;

public class UserPresenter {

    @Inject
    private UserDAO userDAO;

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public void persist(User user) {
        userDAO.persist(user);
    }

    public void merge(User user) {
        userDAO.merge(user);
    }

    public void remove(User user) {
        userDAO.remove(user);
    }
}
