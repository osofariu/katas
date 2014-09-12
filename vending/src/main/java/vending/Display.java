package vending;

public class Display {

    State state;
    private int productCost;
    private int amountInserted;

    enum State {START, PRODUCT_SELECTED, PRODUCT_DISPENSED, SHOW_MONEY_ADDED}

    Display() {
        state = State.START;
    }

    public String show() {
        String message;

        switch (state) {
            case START:
                message = "INSERT COINS"; break;
            case SHOW_MONEY_ADDED:
                message =  Util.formatAmount(amountInserted); break;
            case PRODUCT_SELECTED:
                message = "PRICE " + Util.formatAmount(productCost);
                state = (amountInserted == 0) ? State.START : State.SHOW_MONEY_ADDED;
                break;
            case PRODUCT_DISPENSED:
                message = "THANK YOU";
                state = State.START; break;
            default:
                throw new UnsupportedOperationException("State: " + state);
        }
        return message;
    }

    public void updateAmount(int currentAmount) {
        this.amountInserted = currentAmount;
        state = State.SHOW_MONEY_ADDED;
    }

    public void selectProductWithAmount(int amount) {
        this.productCost = amount;
        state = State.PRODUCT_SELECTED;
    }

    public void showCurrentlySelectedProduct() {
        state = State.PRODUCT_SELECTED;
    }

    public void productDispensed() {
        this.state = State.PRODUCT_DISPENSED;
    }

}
