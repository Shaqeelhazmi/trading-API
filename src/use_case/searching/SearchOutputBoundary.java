package use_case.searching;

public interface SearchOutputBoundary {
    void prepareNotAvailable(String message);

    void prepareSuccessView(SearchOutputData searchOutputData);


}
