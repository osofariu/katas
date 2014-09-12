package vending.currency;

import java.util.Arrays;
import java.util.List;

public class EUCurrency implements Currency {
    @Override
    public List<Coin> allCoins() {
        return Arrays.asList(
                new Coin("E2", 200, true),
                new Coin("E1", 100, true),
                new Coin("C50", 50, true),
                new Coin("C20", 20, true),
                new Coin("C10", 10, true),
                new Coin("C5", 5, true),
                new Coin("C2", 2, true),
                new Coin("C1", 1, false));
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
