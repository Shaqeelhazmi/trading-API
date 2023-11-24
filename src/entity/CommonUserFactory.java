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
    public User create(String name, String password, LocalDateTime ltd, ArrayList<Stock> favourites, Portfolio portfolio, ArrayList<Transaction> transactionHistory) {

        return new CommonUser(name, password, ltd, favourites, portfolio, transactionHistory);
    }
}
