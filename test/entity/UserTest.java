package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private CommonUser user;
    private HashMap<String, Integer> stocksOwned;
    private Portfolio portfolio;

    @BeforeEach
    void init(){
        // creates the favourites
        List<String> favourites = new ArrayList<>(5);
        favourites.add("TSLA");
        favourites.add("AMZN");

        // creates the portfolio
        stocksOwned = new HashMap<>();
        stocksOwned.put("TSLA", 100);
        portfolio = new Portfolio(stocksOwned, 10000);

        // creates the transactions
        Transaction transaction1 = new Transaction(LocalDateTime.now(), "Tesla Inc",
                "Sold Tesla Inc", 5.0, 10);
        Transaction transaction2 = new Transaction(LocalDateTime.now(), "Amazon.com Inc",
                "Bought Amazon.com Inc", 10.0, 20);
        ArrayList<Transaction> transaction = new ArrayList<>(5);
        transaction.add(transaction1);
        transaction.add(transaction2);

        // creates the user
        user = new CommonUser("John", "richboy", LocalDateTime.now(), favourites,
                portfolio, transaction);
    }

    @Test
    void getUsername() {
        assertEquals("John", user.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("richboy", user.getPassword());
    }

    @Test
    void getCreationTime() {
        assert true;
    }
}
