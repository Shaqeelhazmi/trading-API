package entity;

import java.time.LocalDateTime;

public interface StockFactory {
    Stock create(String stockSymbol, PriceHistory priceHistory);
}
