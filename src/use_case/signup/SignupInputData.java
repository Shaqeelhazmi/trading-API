package use_case.signup;

public class SignupInputData {
    final private String username;
    final private String password;
    final private String repeatPassword;

    final private double balance;

    public SignupInputData(String username, String password, String repeatPassword, double balance) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.balance = balance;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public double getBalance() {return balance;}
}
