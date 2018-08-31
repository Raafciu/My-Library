package control.user;

import business.address.Address;
import business.address.AddressDAO;
import business.group.Group;
import business.group.GroupDAO;
import business.position.Position;
import business.position.PositionDAO;
import business.role.Role;
import business.role.RoleDAO;
import business.user.User;
import business.user.UserDAO;

import javax.inject.Inject;
import java.util.List;

public class UserPresenter {

    @Inject
    private UserDAO userDAO;

    @Inject
    private PositionDAO positionDAO;

    @Inject
    private RoleDAO roleDAO;

    @Inject
    private AddressDAO addressDAO;

    @Inject
    private GroupDAO groupDAO;

    public List<User> getAllUsers() {
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

    public List<Position> getAllPositions() {
        return positionDAO.getAll();
    }

    public List<Role> getAllRoles() {
        return roleDAO.getAll();
    }

    public List<Address> getAllAddresses(){
        return addressDAO.getAll();
    }

    public List<Group> getAllGroups(){
        return groupDAO.getAll();
    }
}
