package use_case.portfolio;

public class PortfolioInputData {
    private final String userName;

    public PortfolioInputData(String userName){
        this.userName = userName;
    }

    String getUserName() {
        return userName;
    }
}
