package vending;

import org.junit.Before;
import org.junit.Test;
import vending.currency.Coin;
import vending.currency.CoinProcessor;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class VendingMachineTest {

    VendingMachine vendingMachine;
    CoinProcessor coinProcessor;
    Display display;

    @Before
    public void setUp() {
        coinProcessor = mock(CoinProcessor.class);
        display = mock(Display.class);
        vendingMachine = new VendingMachine(coinProcessor, display);
    }

    // display-related tests

    @Test
    public void whenNoProductSelectedMachineShouldDisplayMessageProvidedByDisplay() {
        when(display.show()).thenReturn("INSERT COINS");

        assertEquals("INSERT COINS", vendingMachine.showMessage());
    }

    @Test
    public void whenEnoughMoneyInsertedToDispenseColaAndUserRequestsProductDisplayIsNotifiedAccordingly() {
        when(coinProcessor.getBalance()).thenReturn(100);
        vendingMachine.selectProduct(Product.COLA);

        vendingMachine.dispenseProduct();

        verify(display, times(1)).productDispensed();
    }

    @Test
    public void whenProductIsSelectedThenDisplayIsNotified() {
        when(coinProcessor.getBalance()).thenReturn(90);

        vendingMachine.selectProduct(Product.COLA);

        verify(display, times(1)).selectProductWithAmount(Product.COLA.getValue());
    }

    @Test
    public void whenEnoughMoneyInsertedToDispenseCHIPSThenDisplayIsNotified() {
        when(coinProcessor.getBalance()).thenReturn(50);

        vendingMachine.selectProduct(Product.CHIPS);
        vendingMachine.dispenseProduct();

        verify(display, times(1)).selectProductWithAmount(Product.CHIPS.getValue());
        verify(display, times(1)).productDispensed();
    }


    @Test
    public void whenEnoughMoneyInsertedToDispenseCANDYThenProductIsDispensed() {
        when(coinProcessor.getBalance()).thenReturn(65);

        vendingMachine.selectProduct(Product.CANDY);
        vendingMachine.dispenseProduct();

        verify(display, times(1)).selectProductWithAmount(Product.CANDY.getValue());
        verify(display, times(1)).productDispensed();
    }

    @Test
    public void whenNotEnoughMoneyInsertedForProductSelectedDisplayIsNotNotifiedOfDispense() {
        when(coinProcessor.getBalance()).thenReturn(65);

        vendingMachine.selectProduct(Product.COLA);
        vendingMachine.dispenseProduct();

        verify(display, times(1)).selectProductWithAmount(Product.COLA.getValue());
        verify(display, times(1)).showCurrentlySelectedProduct();
        verify(display, never()).productDispensed();
    }

    //
    // coin-processor related tests
    //

    @Test
    public void whenMoneyIsInsertedThenCoinProcessorIsNotified() {
        vendingMachine.acceptCoins("QUARTER", "NICKEL");

        verify(coinProcessor, times(1)).acceptCoins("QUARTER", "NICKEL");
    }

    @Test
    public void whenMoneyPlacedInMachineIsMoreThanProductPurchasedTheDifferenceIsReturned() {
        when(coinProcessor.getBalance()).thenReturn(125);
        when(coinProcessor.returnBalance()).thenReturn(Util.makeList(new Coin("QUARTER", 25, true)));

        vendingMachine.selectProduct(Product.COLA);
        vendingMachine.dispenseProduct();

        verify(coinProcessor, times(1)).extractPayment(100);
        verify(coinProcessor, times(1)).returnBalance();
        assertArrayEquals(Util.makeList(new Coin("QUARTER", 25, true)).toArray(), vendingMachine.getCurrentChange().toArray());
    }

    @Test
    public void dispenseProductWillResetCurrentProduct() {
        when(coinProcessor.getBalance()).thenReturn(125);
        vendingMachine.selectProduct(Product.COLA);

        vendingMachine.dispenseProduct();

        assertEquals(Product.NONE, vendingMachine.productSelected);
    }
}

