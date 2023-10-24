package entity;

import java.util.ArrayList;

public interface TransactionHistoryInterface {
    public ArrayList<Transaction> getPurchaseHistory();
    public ArrayList<Transaction> getSellHistory();
}
