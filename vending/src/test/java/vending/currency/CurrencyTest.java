package vending.currency;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CurrencyTest {

    Currency currency;

    @Before
    public void setUp() {
        currency = new USCurrency();
    }

    @Test
    public void whenIAskForAListOfEUCoinsIGetExpectedResults() {
        Currency currency = new EUCurrency();
        assertArrayEquals(new Integer[]{200, 100, 50, 20, 10, 5, 2, 1}, makeValueList(currency.allCoins()).toArray());
    }

    @Test
    public void whenGettingCoinByValue25CoinProcessorReturnsQUARTER() {
        Coin coin = currency.getCoinByValue(25);
        assertEquals("QUARTER", coin.name());
    }


    @Test
    public void whenIAskForAListOfUSCoinsIGetExpectedResults() {
        assertArrayEquals(Arrays.asList("QUARTER", "DIME", "NICKEL", "PENNY").toArray(), makeDesignationList(currency.allCoins()).toArray());
        assertArrayEquals(Arrays.asList(25, 10, 5, 1).toArray(), makeValueList(currency.allCoins()).toArray());
    }



    private List<Integer> makeValueList(List<Coin> coins) {
        List<Integer> values = new ArrayList<>();
        for (Coin coin : coins) {
            values.add(coin.value());
        }
        return values;
    }

    private List<String> makeDesignationList(List<? extends Coin> coins) {
        List<String> designations = new ArrayList<>();
        for (Coin coin : coins) {
            designations.add(coin.name());
        }
        return designations;
    }
}
