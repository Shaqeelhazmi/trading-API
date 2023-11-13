package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface UserFactory {
    User create(String username, String password, LocalDateTime ltd, ArrayList<Stock> favourites, Portfolio portfolio);
}
