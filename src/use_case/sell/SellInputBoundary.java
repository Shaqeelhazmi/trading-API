package use_case.sell;

import java.io.IOException;

public interface SellInputBoundary {
    void sell(SellInputData sellInputData) throws IOException;
}
