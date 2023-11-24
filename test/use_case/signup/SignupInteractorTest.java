package use_case.signup;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.CommonUser;
import entity.Portfolio;
import entity.Transaction;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;
import java.time.LocalDateTime;
import java.util.*;
public class SignupInteractorTest {
    public void addUser() {
        FileUserDataAccessObject fudao;
        CommonUserFactory uf = new CommonUserFactory();
        try {
            fudao = new FileUserDataAccessObject("./users.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> favourites = new ArrayList<>();
        favourites.add("TSLA");
        HashMap<String, Integer> portfolioMap = new HashMap<>();
        portfolioMap.put("TSLA", 5);
        Portfolio portfolio = new Portfolio(portfolioMap, 100.00);
        Transaction transaction = new Transaction(LocalDateTime.now(), "TSLA", "buy", 100.00, 5);
        ArrayList<Transaction> transactionHistory = new ArrayList<>();
        transactionHistory.add(transaction);
        fudao.save(uf.create("bob", "123", LocalDateTime.now(), favourites, portfolio, transactionHistory));
    }
}

