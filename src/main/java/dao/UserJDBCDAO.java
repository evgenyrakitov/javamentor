package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDAO implements DAO {

    private Connection connection;

    public UserJDBCDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAll() {
        List <User> users = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("select * from users")) {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Long id = result.getLong("id");
                String login = result.getString("login");
                String email = result.getString("email");
                String pass = result.getString("password");
                String role = result.getString("role");
                users.add(new User(id, login, email, pass, role));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void create(User user) {
        PreparedStatement prstmt = null;
        try {
            prstmt = connection.prepareStatement("insert into users (login, email, password, role) values (?, ?, ?, ?)");
            prstmt.setString(1, user.getLogin());
            prstmt.setString(2, user.getEmail());
            prstmt.setString(3, user.getPassword());
            prstmt.setString(4, user.getRole());
            prstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                prstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void remove(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("select * from users where id=?")) {
            stmt.setLong(1, user.getId());
            stmt.executeUpdate();
        }  catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        User user = null;
        try (PreparedStatement stmt = connection.prepareStatement("select * from users where login=? and password=?")) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
            result.next();
            Long id = result.getLong("id");
            String email = result.getString("email");
            String role = result.getString("role");
            result.close();
            user = new User(id, login, email, password, role);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return user;
    }

    public void update(User user) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("update users set login=?, email=?, password=? where id=?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setLong(4, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User getUserForId(long id) {
        User user = new User();
        try (PreparedStatement stmt = connection.prepareStatement("select * from users where id=?")) {
            stmt.setLong(1, id);
            ResultSet result = stmt.executeQuery();
            result.next();
            String login = result.getString("login");
            String email = result.getString("email");
            String pass = result.getString("password");
            String role = result.getString("role");
            result.close();
            stmt.close();
            user.setId(id);
            user.setLogin(login);
            user.setEmail(email);
            user.setPassword(pass);
            user.setRole(role);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return user;
    }

    public void createTable() {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("create table if not exists users (id bigint auto_increment, login varchar(256), email varchar(256), password varchar(256), role varchar(256), primary key (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
