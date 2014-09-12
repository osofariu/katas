package vending;

import org.junit.Before;
import org.junit.Test;
import vending.currency.Coin;
import vending.currency.CoinProcessor;
import vending.currency.USCurrency;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class VendingMachineIntTest {
    VendingMachine vendingMachine;
    CoinProcessor coinProcessor;
    Display display;

    @Before
    public void setUp() {
        coinProcessor = new CoinProcessor(new USCurrency());
        display = new Display();
        vendingMachine = new VendingMachine(coinProcessor, display);
    }

    @Test
    public void  whenCustomerSelectsProductInsertsEnoughMoneyAndRequestsProductTheyWillBeThanked() {
        vendingMachine.selectProduct(Product.COLA);
        vendingMachine.acceptCoins("QUARTER", "QUARTER", "QUARTER", "QUARTER", "DIME", "PENNY");
        vendingMachine.dispenseProduct();

        assertEquals("THANK YOU", vendingMachine.showMessage());
        assertArrayEquals(new Coin[] { new Coin("DIME", 10, true), new Coin("PENNY", 1, false)}, vendingMachine.getCurrentChange().toArray());
    }

    @Test
    public void  whenCustomerInsertsEnoughMoneySelectsProductAndRequestsProductTheyWillBeThanked() {
        vendingMachine.acceptCoins("QUARTER", "QUARTER", "QUARTER", "QUARTER");
        vendingMachine.selectProduct(Product.COLA);
        vendingMachine.dispenseProduct();

        assertEquals("THANK YOU", vendingMachine.showMessage());
    }
}
