package control.user;

import business.user.User;
import business.user.UserDAO;

import javax.inject.Inject;
import java.util.List;

public class UserPresenter {

    @Inject
    private UserDAO userDAO;

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
