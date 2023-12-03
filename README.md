# The Wolf of Bay Street Stock Trading App

## Domain
financial literacy and stock trading.

## Software Specification:
- Signup and Login with a username and the password.
- Start as a user with a certain amount of money.
- Be able to buy shares of any stock they can afford.
- Be able to sell shares of stock they own.
- Be able to search for stocks by symbol.
- Be able to view their portfolio.
     - see the balance of their account
     - see the amount of their stock shares

## Proposed Entities for the Domain

### 1) CommonUser
- Describes the general information about a user and his account. 
- Has the following attribute(s): 
     - String username
     - String password
     - LocalDateTime creationTime
     - ArrayList<Stock> favourites
     - Portfolio portfolio
     - TransactionHistory transactionHistory

### 2) CommonUserFactory
- Create user accounts.

### 3) Portfolio
- Specifies which stocks a user owns with shares owned, and tells the total assets of stocks owned.
- Has the following attribute(s):
     - Hashmap<Stock, Integer> portfolio, [key = Stock, value = amountOwned]
     - double accountBalance

### 3) Stock
- Stores the information about an individual stock. 
- Has the following attribute(s):
     - String stockName [stock’s ticker]

### 4) StockFactory
- Create stocks from the database.

### 5) PriceHistory
- Record the price history(in daily, weekly, and monthly) of a specific stock.

### 6) Transaction
- Record the user's action(buy and sell) on a specific stock.
- Record the time when the action occurred.
- Record the current price of the stock.
- Record the amount of shares of the stock involved in the action.

### 7) TransactionHistory
- Record the PurchaseHistory(BuyHistory) of the user.
- Record the SellHistory of the user.

## API
We use the Alpha Vantage API for our project. 
This API allows us to get information about stocks.
API documentation can be accessed through the link below:
    - https://www.alphavantage.co/documentation/

- The first endpoint is in the StockDataAccessObject.
  The function are "TIME_SERIES_DAILY", "TIME_SERIES_WEEKLY", "TIME_SERIES_MONTHLY".
  To get the data of those stocks, including the date and the open value.

- The second endpoint is in the Searching Use Case.
  The function is "SYMBOL_SEARCH".
  To search for stocks with the given input symbol.