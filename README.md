# The Wolf of Bay Street Stock Trading App

## Domain
This is a financial literacy and entertainment app that allows users to use fake money and practice investing 
in the stock market.

## Software Specification: 
- Create an account and login
- Start with a certain amount of money
- Be able to see the list of all stocks, then filter stocks by their sectors (tech, biotech, food, etc.)
- Be able to see the prices of stocks and their history (hour/day/week/month/ytd)
- Be able to buy shares of any stock they can afford and sell shares of stock they own
- View their portfolio, see the history of their stock including when they bought it, what price they bought it, and the percent change in price since they bought it.
- View a summary of their portfolio, net worth, net profit, composite of their portfolio in the form of a pie chart (tech, biotech, food, etc.)
- Be able to “favourite”/track stocks they are interested in, even if they have not yet bought shares
- Be able to access a resources page with links to helpful tutorials
- Be able to do automated buy-ins and exits. Set prices that you automatically buy or sell at
- Be able to set a default currency (CAD, USD, GBP, etc.) 

## Proposed Entities for the Domain
### Account
- Describes the general information about a user and his account. 
- Has the following attributes: String username, String password, ArrayList<Stock> favourites etc.
### Portfolio
- Specifies which stocks a user owns with shares owned described and tells the total worth of stocks owned and liquid cash.
- Has the following attributes: HashMap<Stock, int> stockList [key = Stock, value = amountOwned], float accountBalance
### Stock
- Stores the information about an individual stock. 
- Has the following attributes: String stockName [stock’s ticker], String category [e.g. tech, finance, energy etc.], HashMap<String, list<float>> priceHistory
### Catalogue
- A collection of stocks that’s featured in either the main page or in the user’s favourites list. 
- Has the following attributes: ArrayList<Stock> defaultStocks, ArrayList<Stock> favouriteStocks
### TransactionHistory
- Describes all the information on the user’s past trades. 
- Has the following attributes: HashMap<datetime, list<Object>> purchaseHistory [key = time of purchase, value = {Stock, pricePerShare, amountShares}], HashMap<datetime, list<Object>> sellingHistory [key = time of selling, value = {Stock, pricePerShare, amountShares}]


## API

We use the Alpha Vantage API for our project. This API will allow us to get information about the price/price history of stocks, so the accurate stock prices are used in our app.
API documentation can be accessed through this link:
https://www.alphavantage.co/documentation/
