package interface_adapter.logged_in;

public class LoggedInState {
    private String username = "";

    private double accountBalance = 0.0;

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        accountBalance = copy.accountBalance;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }
}
