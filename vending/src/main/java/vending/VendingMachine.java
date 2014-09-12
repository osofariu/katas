package vending;

import vending.currency.Coin;
import vending.currency.CoinProcessor;

import java.util.List;

public class VendingMachine {

    Product productSelected = Product.NONE;
    CoinProcessor coinProcessor;
    List<Coin> currentChange;
    Display display;

    public VendingMachine(CoinProcessor coinProcessor, Display display) {
        this.coinProcessor = coinProcessor;
        this.display = display;
    }

    public void selectProduct(Product product) {
        productSelected = product;
        display.selectProductWithAmount(product.getValue());
    }

    public String showMessage() {
       return display.show();
    }


    public List<Coin> getCurrentChange() {
        return currentChange;

    }

    public void acceptCoins(String...coinNames) {
        coinProcessor.acceptCoins(coinNames);
        display.updateAmount(coinProcessor.getBalance());
    }

    public void dispenseProduct() {
        if (enoughMoneyInserted()) {
            coinProcessor.extractPayment(productSelected.getValue());
            currentChange = coinProcessor.returnBalance();
            productSelected = Product.NONE;
            display.productDispensed();
        } else {
            display.showCurrentlySelectedProduct();
        }
    }

    private boolean enoughMoneyInserted() {
        return coinProcessor.getBalance() >= productSelected.getValue();
    }
}