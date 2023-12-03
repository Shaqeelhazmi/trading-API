package use_case.portfolio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioInputDataTest {
    PortfolioInputData portfolioInputData = new PortfolioInputData("bob");

    @Test
    void getUserName() {
        assertEquals("bob", portfolioInputData.getUserName());
    }
}