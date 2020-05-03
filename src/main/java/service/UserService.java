package service;


import dao.UserJDBCDAO;
import model.User;

import util.DBHelper;
import util.UserDaoFactory;
import java.util.List;

public class UserService {

    private static UserService userService;

    private UserService() {

    }

    public static UserService getInstance() {
        if (userService == null) {
            synchronized (UserService.class) {
                if (userService == null) {
                    userService = new UserService();
                }
            }
        }
        return userService;
    }

    public List<User> getAllUser() {
        return new UserDaoFactory().getUserDaoFactory().getAll();
    }

    public void addUser(User user) {
        new UserDaoFactory().getUserDaoFactory().create(user);
    }

    public void  removeUser(User user) {
        new UserDaoFactory().getUserDaoFactory().remove(user);
    }

    public void updateUser(User user) {
        new UserDaoFactory().getUserDaoFactory().update(user);
    }

    public User getUserById(long id) {
        return new UserDaoFactory().getUserDaoFactory().getUserForId(id);
    }

    public String setRoleByLogin(String login) {
        String role;
        if(login == "admin") {
            role = "admin";
        } else {
            role = "user";
        }
        return role;
    }

    public void createTable() {
        getUserJDBCDAO().createTable();
    }

    public UserJDBCDAO getUserJDBCDAO() {
        return new UserJDBCDAO(DBHelper.getConnection());
    }




    public User getUserByLoginAndPassword(String login, String password) {
        return new UserDaoFactory().getUserDaoFactory().getUserByLoginAndPassword(login, password);
    }
}

