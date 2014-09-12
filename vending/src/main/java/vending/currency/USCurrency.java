package vending.currency;

import java.util.Arrays;
import java.util.List;

public class USCurrency implements Currency {
    @Override
    public List<Coin> allCoins() {
        return Arrays.asList(
                new Coin("QUARTER", 25, true),
                new Coin("DIME", 10, true),
                new Coin("NICKEL", 5, true),
                new Coin("PENNY", 1, false)
        );
    }

    @Override
    public Coin getCoinByName(String designation) {
        for (Coin coin : allCoins()) {
            if (coin.name().equals(designation)) {
                return coin;
            }
        }
        return null;
    }

    @Override
    public Coin getCoinByValue(int value) {
        for (Coin coin : allCoins()) {
            if (coin.value() == value) {
                return coin;
            }
        }
        return null;
    }
}
