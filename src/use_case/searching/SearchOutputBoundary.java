package use_case.searching;

public interface SearchOutputBoundary {
    void prepareFailView(String message);

    void prepareSuccessView(SearchOutputData searchOutputData);


}
