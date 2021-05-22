package main.UserDB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import main.controller.Role;
import main.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class UserDB {

    private static UserDB instance;

    private final Map<String, User> MemoryDb;

    private UserDB() {
        MemoryDb = new HashMap<>();
        MemoryDb.put("admin", new User("admin", "admin", Role.ROLE_ADMIN));
        MemoryDb.put("users", new User("users", "users", Role.ROLE_USER));
        MemoryDb.put("support", new User("support", "support", Role.ROLE_SUPPORT));
    }

    public static UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

    public void add(User user) {
        String login = user.getLogin();
        MemoryDb.put(login, user);
    }

    public void remove(String login) {
        MemoryDb.remove(login);
    }

    public Optional<User> getUserByType(String login, String password) {
        return MemoryDb.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findAny();
    }

    public Optional<Role> getRole(String login) {
        return MemoryDb.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(user -> user.getLogin().equals(login))
                .map(User::getRole)
                .findFirst();
    }
}
