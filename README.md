# The Wolf of Bay Street Stock Trading App

## Domain
financial literacy and stock trading

## Software Specification:
- Signup and Login with a username and the password
- Start with a certain amount of money
- Be able to buy shares of any stock they can afford and sell shares of stock they own
- Be able to search for stocks by name
- View their portfolio, see the amount of their stock shares

## Proposed Entities for the Domain
### CommonUser
- Describes the general information about a user and his account. 
- Has the following attribute(s): 
    - String username
    - String password
    - LocalDateTime creationTime
    - ArrayList<Stock> favourites
    - Portfolio portfolio
    - TransactionHistory transactionHistory
### CommonUserFactory
- Create user accounts
### Portfolio
- Specifies which stocks a user owns with shares owned, and tells the total assets of stocks owned.
- Has the following attribute(s):
    - Hashmap<Stock, Integer> portfolio, [key = Stock, value = amountOwned]
    - double accountBalance
### Stock
- Stores the information about an individual stock. 
- Has the following attribute(s):
    - String stockName [stock’s ticker]
### StockFactory
- Create stocks from the database
### PriceHistory
- Record the price history(in daily, weekly, and monthly) of a specific stock.
### Transaction
- Record the user's action(buy or sell) on a specific stock.
- Record the time of the action.
- Record the price of the stock at the time.
- Record the amount of shares involved in the action.
### TransactionHistory
- Record the PurchaseHistory of the user.
- Record the SellHistory of the user.

## API
We use the Alpha Vantage API for our project. This API will allow us to get information about the price/price history of stocks, so the accurate stock prices are used in our app.
API documentation can be accessed through this link:
https://www.alphavantage.co/documentation/
