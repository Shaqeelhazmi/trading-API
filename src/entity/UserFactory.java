package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface UserFactory {
    User create(String username, String password, LocalDateTime ltd, ArrayList<String> favourites, Portfolio portfolio,
                ArrayList<Transaction> transactions);
}
