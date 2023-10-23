# The Wolf of Bay Street Stock Trading App

## Domain
financial literacy and stock trading

## Software Specification:
- Start with a certain amount of money
- Be able to buy shares of any stock they can afford and sell shares of stock they own
- View their portfolio, see the amount of their stock shares

## Proposed Entities for the Domain
### Account
- Describes the general information about a user and his account. 
- Has the following attributes: Float balance, Portfolio portfolio.
### Account Factory
- Create accounts
### Portfolio
- Specifies which stocks a user owns with shares owned, and tells the total assets of stocks owned.
- Has the following attributes: HashMap<Stock, int> stockList [key = Stock, value = amountOwned], float total_Assets
### Stock
- Stores the information about an individual stock. 
- Has the following attributes: String stockName [stock’s ticker]
### Stock Factory
- Create stocks from the database

## API
We use the Alpha Vantage API for our project. This API will allow us to get information about the price/price history of stocks, so the accurate stock prices are used in our app.
API documentation can be accessed through this link:
https://www.alphavantage.co/documentation/
