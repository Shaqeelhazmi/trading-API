package data_access;

import entity.*;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

import java.time.LocalDateTime;
import java.util.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {
    private final JSONObject jsonObject;
    private final File jsonFile;

    private final HashMap<String, User> accounts = new HashMap<>();


    public FileUserDataAccessObject(String jsonPath) throws IOException {
        this.jsonFile = new File(jsonPath);

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            StringBuilder jsonStringBuilder = new StringBuilder();
            String row;
            while ((row = reader.readLine()) != null) {
                jsonStringBuilder.append(row);
            }
            String jsonString = jsonStringBuilder.toString();
            if (jsonString.isEmpty()) {
                this.jsonObject = new JSONObject();
            } else {
                this.jsonObject = new JSONObject(jsonString);
            }
        }
        if (jsonObject.isEmpty()) {
            save();
        } else {
            for (String username : jsonObject.keySet()) {
                JSONObject account = jsonObject.getJSONObject(username);
                String accountUsername = account.getString("username");
                String accountPassword = account.getString("password");
                LocalDateTime creationTime = LocalDateTime.parse(account.getString("creationTime"));
                List<String> favourites = toStringList(account.getJSONArray("favourites"));

                JSONObject portfolioJsonObject = account.getJSONObject("portfolio");
                HashMap<String, Integer> portfolioMap = toStringIntMap(portfolioJsonObject.getJSONObject("portfolio"));
                double accountBalance = portfolioJsonObject.getDouble("accountBalance");
                Portfolio portfolio = new Portfolio(portfolioMap, accountBalance);

                ArrayList<Transaction> transactionHistory = new ArrayList<>();
                JSONArray transactionHistoryJsonArray = account.getJSONArray("transactionHistory");
                for (int i = 0; i < transactionHistoryJsonArray.length(); i++) {
                    JSONObject transactionJsonObject = transactionHistoryJsonArray.getJSONObject(i);
                    LocalDateTime timestamp = LocalDateTime.parse(transactionJsonObject.getString("timestamp"));
                    String stock = transactionJsonObject.getString("stock");
                    String action = transactionJsonObject.getString("action");
                    double pricePerShare = transactionJsonObject.getDouble("pricePerShare");
                    int amountOfShares = transactionJsonObject.getInt("amountOfShares");
                    transactionHistory.add(new Transaction(timestamp, stock, action, pricePerShare, amountOfShares));
                }

                CommonUser user = new CommonUser(accountUsername, accountPassword, creationTime, favourites, portfolio, transactionHistory);
                accounts.put(username, user);

                }

            }

        }

    @Override
    public void save(CommonUser user) {
        JSONObject accountJsonObject = new JSONObject();

        accountJsonObject.put("username", user.getUsername());
        accountJsonObject.put("password", user.getPassword());
        accountJsonObject.put("creationTime", user.getCreationTime());
        accountJsonObject.put("favourites", new JSONArray(user.getFavourites()));

        Portfolio portfolio = user.getPortfolio();

        JSONObject portfolioJsonObject = new JSONObject();
        portfolioJsonObject.put("portfolio", portfolio.getPortfolio());
        portfolioJsonObject.put("accountBalance", portfolio.getAccountBalance());

        accountJsonObject.put("portfolio", portfolioJsonObject);

        JSONArray transactionHistoryJsonArray = new JSONArray();
        for (Transaction transaction : user.getTransactionHistory()) {
            JSONObject transactionJsonObject = new JSONObject(transaction.getTransactionMap());
            transactionHistoryJsonArray.put(transactionJsonObject);
        }

        accountJsonObject.put("transactionHistory", transactionHistoryJsonArray);

        accounts.put(user.getUsername(), user);
        jsonObject.put(user.getUsername(), accountJsonObject);
        this.save();
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (String username : accounts.keySet()) {
            users.add(accounts.get(username));
        }
        return users;
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    private void save() {
        try {
            FileWriter file = new FileWriter(jsonFile);
            String jsonString = jsonObject.toString();
            file.write(jsonString);
            file.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    public static HashMap<String, Object> toMap(JSONObject jsonobj)  throws JSONException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Iterator<String> keys = jsonobj.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            Object value = jsonobj.get(key);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }   return map;
    }
    public static HashMap<String, Integer> toStringIntMap(JSONObject jsonobj)  throws JSONException {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Iterator<String> keys = jsonobj.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            Integer value = jsonobj.getInt(key);
            map.put(key, value);
        }   return map;
    }
    public static List<String> toStringList(JSONArray array) throws JSONException {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < array.length(); i++) {
            String value = array.getString(i);
            list.add(value);
        }   return list;
    }
    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }
            else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }   return list;
    }

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }

}
