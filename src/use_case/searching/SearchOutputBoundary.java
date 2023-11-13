package use_case.searching;

public interface SearchOutputBoundary {
    void prepareNotAvailable();

    void prepareSuccessView(SearchOutputData searchOutputData);


}
