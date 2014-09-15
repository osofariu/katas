package vending;

import org.junit.Test;
import vending.currency.Coin;
import vending.currency.CoinProcessor;
import vending.currency.Currency;
import vending.currency.USCurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CoinProcessorTest {

    Currency currency = new USCurrency();
    CoinProcessor coinProcessor = new CoinProcessor(currency);

    @Test
    public void getValueInsertCoinWhenNoCoinsInserted() {
        assertEquals(0, coinProcessor.getBalance());
    }

    @Test
    public void whenAddingADimeItWillBeAvailableForRetrieval() {
        Currency currency = new USCurrency();
        CoinProcessor coinProcessor = new CoinProcessor(currency);
        coinProcessor.acceptCoins("DIME");
        assertEquals(Arrays.asList(currency.getCoinByName("DIME")), coinProcessor.returnBalance());
    }

    @Test
    public void getValue5WhenNickelInserted() {
        coinProcessor.acceptCoins("NICKEL");
        assertEquals(5, coinProcessor.getBalance());
    }

    @Test
    public void getValue10WhenDimeInserted() {
        coinProcessor.acceptCoins("DIME");
        assertEquals(10, coinProcessor.getBalance());
    }

    @Test
    public void getValue25WhenQuarterInserted() {
        coinProcessor.acceptCoins("QUARTER");
        assertEquals(25, coinProcessor.getBalance());
    }

    @Test
    public void getValue35WhenDimeAndQuarterInserted() {
        coinProcessor.acceptCoins("DIME", "QUARTER");
        assertEquals(35, coinProcessor.getBalance());
    }

    @Test
    public void getValue15WhenDimeAndNickenInserted() {
        coinProcessor.acceptCoins("DIME", "NICKEL");
        assertEquals(15, coinProcessor.getBalance());
    }

    @Test
    public void coinReturnContainsPennyWhenPennyInserted() {
        coinProcessor.acceptCoins("PENNY");
        assertCoinListsAreTheSame(Util.makeList(currency.getCoinByValue(1)), coinProcessor.returnBalance());
    }

    @Test
    public void coinReturnContainsPennyAndAdditionalChangeWhenPaymentIsAccepted() {
        coinProcessor.acceptCoins("PENNY");
        coinProcessor.acceptCoins("QUARTER");
        coinProcessor.acceptCoins("DIME");

        coinProcessor.extractPayment(25);

        assertCoinListsAreTheSame(makeCoinListFromNames("DIME", "PENNY"), coinProcessor.returnBalance());
    }


    @Test
    public void currentValueIs5WhenPaymentExtractedWithOverPaymentOf5cents() {
        coinProcessor.acceptCoins("QUARTER", "NICKEL");
        coinProcessor.extractPayment(25);

        assertEquals(5, coinProcessor.getBalance());
    }


    @Test
    public void currentValueIsResetAndCorrectCoinsAreReturnedForCurrentAmount() {
        coinProcessor.acceptCoins("QUARTER", "NICKEL");
        coinProcessor.extractPayment(25);

        assertCoinListsAreTheSame(makeCoinListFromNames("NICKEL"), coinProcessor.returnBalance());
        assertEquals(0, coinProcessor.getBalance());
    }

    @Test
    public void whenReturnBalanceIsCalledWith35CentsItReturnsAppropriateCoins() {
        coinProcessor.acceptCoins("QUARTER", "DIME");

        assertCoinListsAreTheSame(makeCoinListFromNames("QUARTER", "DIME"), coinProcessor.returnBalance());
    }

    @Test
    public void whenReturnBalanceIsCalledWith30CentsItReturnsAppropriateCoins() {
        coinProcessor.acceptCoins("NICKEL", "QUARTER");

        assertCoinListsAreTheSame(makeCoinListFromNames("QUARTER", "NICKEL"), coinProcessor.returnBalance());
    }

    @Test
    public void whenReturnBalanceIsCalledWith40CentsBalanceItReturnsAppropriateCoins() {
        coinProcessor.acceptCoins("NICKEL", "DIME", "QUARTER");
        assertCoinListsAreTheSame(makeCoinListFromNames("QUARTER", "DIME", "NICKEL"), coinProcessor.returnBalance());
    }

    @Test
    public void whenReturnBalanceIsCalledWit80CentsItReturnsAppropriateCoins() {
        coinProcessor.acceptCoins("QUARTER", "QUARTER", "QUARTER", "NICKEL");
        assertCoinListsAreTheSame(makeCoinListFromNames("QUARTER", "QUARTER", "QUARTER", "NICKEL"), coinProcessor.returnBalance());
    }

    @Test
    public void whenReturnBalanceIsCalledAndAPennyWasInsertedItIsAlsoReturned() {
        coinProcessor.acceptCoins("PENNY", "DIME");
        assertCoinListsAreTheSame(makeCoinListFromNames("DIME", "PENNY"), coinProcessor.returnBalance());
    }

    @Test(expected = RuntimeException.class)
    public void whenTryingToExtractPaymentGreaterThanCurrentBalanceThenAnExceptionIsThrown() {
        coinProcessor.acceptCoins("QUARTER", "PENNY");   // penny cannot be applied to payment
        coinProcessor.extractPayment(26);
    }


    //
    // helpers
    //

    private <T> void assertCoinListsAreTheSame(List<T> expectedList, List<T> actualList) {
        assertArrayEquals(expectedList.toArray(), actualList.toArray());
    }

    private List<Coin> makeCoinListFromNames(String...coinNames) {
        List<Coin> coins = new ArrayList<Coin>();
        for (String coinName: coinNames) {
            coins.add(currency.getCoinByName(coinName));
        }
        return coins;
    }


}
