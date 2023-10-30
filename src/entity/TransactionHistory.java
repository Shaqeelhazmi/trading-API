package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionHistory implements TransactionHistoryInterface {
    private ArrayList<Transaction> purchaseHistory;
    private ArrayList<Transaction> sellHistory;

    public ArrayList<Transaction> getPurchaseHistory() {
        return purchaseHistory;
    }
    public ArrayList<Transaction> getSellHistory() {
        return sellHistory;
    }
}
