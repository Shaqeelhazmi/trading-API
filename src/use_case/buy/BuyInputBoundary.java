package use_case.buy;

import java.io.IOException;

public interface BuyInputBoundary {
    void buy(BuyInputData buyInputData) throws IOException;
}
