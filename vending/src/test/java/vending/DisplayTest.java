package vending;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DisplayTest {

    Display display;

    @Before
    public void setUp() {
        display = new Display();
    }

    @Test
    public void whenDisplayInitializedItShowsMessageInsertCoins() {
        assertEquals("INSERT COINS", display.show());
    }

    @Test
    public void whenUserInsertsCoinsTotalCurrentAmountIsDisplayed() {
        display.updateAmount(25);
        assertEquals("0.25", display.show());
    }

    @Test
    public void whenUserSelectsProductThenPoductPriceIsDisplayed() {
        display.selectProductWithAmount(100);
        assertEquals("PRICE 1.00", display.show());
    }

    @Test
    public void whenNoMoneyIsInsertedAndUserSelectsProductAndDisplayShowsAgainInsertCoinsIsDisplayed() {
        display.selectProductWithAmount(100);
        display.show();

        display.show();
        assertEquals("INSERT COINS", display.show());
    }


    @Test
    public void whenProductIsDispensedThenThankYouIsDisplayed() {
        display.productDispensed();
        assertEquals("THANK YOU", display.show());
    }

    @Test
    public void whenUserPurchasesProductAndDisplayShowsAgainInsertCoinsIsDisplayed() {
        display.productDispensed();
        display.show();

        assertEquals("INSERT COINS", display.show());
    }
}
