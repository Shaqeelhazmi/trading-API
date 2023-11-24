package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface UserFactory {
    User create(String name, String password, LocalDateTime ltd, List<String> favourites, Portfolio portfolio, ArrayList<Transaction> transactionHistory);
}
