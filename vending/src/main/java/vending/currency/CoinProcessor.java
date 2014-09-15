package vending.currency;

import java.util.ArrayList;
import java.util.List;

public class CoinProcessor {

    private int balance = 0;
    private Currency currency;
    private List<Coin> coinReturns = new ArrayList<Coin>();

    public CoinProcessor(Currency currency) {
        this.currency = currency;
    }

    public int getBalance() {
        return balance;
    }

    public void acceptCoins(String... coinNames) {
        for (String name : coinNames) {
            acceptCoin(name);
        }
    }

    private void acceptCoin(String coinName) {
        Coin coin = currency.getCoinByName(coinName);
        if (coin.isAccepted()) {
            balance += coin.value();
        } else {
            coinReturns.add(coin);
        }
    }

    public void extractPayment(int paymentAmount) {
        if (balance >= paymentAmount)
            balance -= paymentAmount;
        else throw new RuntimeException("You haven't paid enough.. and we didn't catch you earlier.");

    }

    public List<Coin> returnBalance() {
        List<Coin> returnList = makeChange();
        returnList.addAll(coinReturns);
        resetOrder();
        return returnList;
    }

    private List<Coin> makeChange() {
        List<Coin> listOfChange = new ArrayList<Coin>();
        while (balance > 0) {
            for (Coin currentCoin : currency.allCoins()) {
                while (balance >= currentCoin.value()) {
                    listOfChange.add(currentCoin);
                    balance -= currentCoin.value();
                }
            }
        }
        return listOfChange;
    }

    private void resetOrder() {
        coinReturns = new ArrayList<Coin>();
        balance = 0;
    }
}
