package use_case.sell;

public interface SellOutputBoundary {
    void prepareSuccessView(SellOutputData sellOutputData);

    void prepareNotAvailable(String message);

    void prepareNotEnough(String message);
}
