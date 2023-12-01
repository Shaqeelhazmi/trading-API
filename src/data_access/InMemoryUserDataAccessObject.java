package data_access;

import entity.CommonUser;
import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {

    private final HashMap<String, CommonUser> accounts = new HashMap<>();

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void save(CommonUser user) {
        accounts.put(user.getUsername(), user);
    }
}
