package entity;

import java.time.LocalDateTime;

public interface StockFactory {
    Stock create(String name, String TickerSymbol, String category, PriceHistory priceHistory);
}
