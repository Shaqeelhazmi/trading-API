package data_access;

import entity.CommonUser;
import entity.CommonUserFactory;
import entity.Portfolio;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FileUserDataAccessObjectTest {

    @Test
    void save() {
        FileUserDataAccessObject fudao;
        UserFactory uf = new CommonUserFactory();
        try {
            fudao = new FileUserDataAccessObject("./users.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CommonUser testUser = uf.create(
                "test",
                "1234",
                LocalDateTime.now(),
                new ArrayList<>(),
                new Portfolio(new HashMap<>(), 100),
                new ArrayList<>()
                );
        fudao.save(testUser);
    }

    @Test
    void getUsers() {
        FileUserDataAccessObject fudao;
        try {
            fudao = new FileUserDataAccessObject("./users.json");
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        System.out.println(fudao.getUsers());
    }

    @Test
    void get() {
        FileUserDataAccessObject fudao;
        try {
            fudao = new FileUserDataAccessObject("./users.json");
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        CommonUser testUser = fudao.get("test");
        System.out.println(testUser.getUsername());
        System.out.println(testUser.getPassword());
        System.out.println(testUser.getCreationTime());
        System.out.println(testUser.getFavourites());
        System.out.println(testUser.getPortfolio().getPortfolio());
        System.out.println(testUser.getPortfolio().getAccountBalance());
        System.out.println(testUser.getTransactionHistory().get(0).getTransactionMap());

    }

    @Test
    void existsByName() {
    }
}