package data_access;

import entity.CommonUser;
import entity.CommonUserFactory;
import entity.Portfolio;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void get() {
    }

    @Test
    void existsByName() {
    }
}