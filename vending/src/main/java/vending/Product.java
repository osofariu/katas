package vending;

public enum Product {
    COLA(100),
    CHIPS(50),
    CANDY(65),
    NONE(0), ;

    private final int value;

    Product(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
