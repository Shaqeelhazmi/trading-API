package use_case.searching;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class SearchInputDataTest {

    SearchInputData searchInputData = new SearchInputData("TESLA");


    @Test
    void getStockname() {
        assertEquals("TESLA", searchInputData.getStockname());
    }

}