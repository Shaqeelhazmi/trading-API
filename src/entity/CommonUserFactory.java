package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */

    public CommonUser create(String name, String password, LocalDateTime ltd, List<String> favourites, Portfolio portfolio, ArrayList<Transaction> transactionHistory) {

        return new CommonUser(name, password, ltd, favourites, portfolio, transactionHistory);
    }
}
