package vending.currency;

import java.util.List;

public interface Currency {
    List<Coin> allCoins();
    Coin getCoinByName(String name);
    Coin getCoinByValue(int value);
}
