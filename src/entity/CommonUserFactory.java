package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */


    @Override
    public User create(String username, String password, LocalDateTime ltd, ArrayList<String> favourites, Portfolio portfolio, ArrayList<Transaction> transactions) {
        return new CommonUser(username, password, ltd, favourites, portfolio, transactions);
    }
}
