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
- Account
- Portfolio
- Stock
- Catalogue
- TransactionHistory

## API

We use the Alpha Vantage API for our project. This API will allow us to get information about the price/price history of stocks, so the accurate stock prices are used in our app.
API documentation can be accessed through this link:
https://www.alphavantage.co/documentation/
