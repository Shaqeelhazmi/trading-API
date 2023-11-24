package use_case.signup;

import data_access.FileUserDataAccessObject;
import entity.StockFactory;
import entity.User;
import entity.UserFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;
import java.time.LocalDateTime;
import java.util.*;
public class SignupInteractorTest {

    public static void main(String[] args) {
        File file = new File("./users");
        JSONObject jo = new JSONObject();
        jo.put("1", "2");
    }

}

