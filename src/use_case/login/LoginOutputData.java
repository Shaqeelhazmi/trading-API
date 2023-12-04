package use_case.login;

public class LoginOutputData {

    private final String username;
    private boolean useCaseFailed;

    private double accountBalance;

    public LoginOutputData(String username, boolean useCaseFailed, double accountBalance) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.accountBalance = accountBalance;
    }

    public String getUsername() {
        return username;
    }

    public double getAccountBalance(){ return accountBalance;}

}
