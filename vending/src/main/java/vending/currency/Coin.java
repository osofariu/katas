package vending.currency;

public class Coin  {

    private String name;
    private int value;
    private boolean isAccepted;

    public Coin(String name, int value, boolean isAccepted) {
        this.name = name;
        this.value = value;
        this.isAccepted = isAccepted;

    }

    public int value() {
        return value;
    }

    public String name() {
        return name;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coin coin = (Coin) o;

        if (isAccepted != coin.isAccepted) return false;
        if (value != coin.value) return false;
        if (name != null ? !name.equals(coin.name) : coin.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + value;
        result = 31 * result + (isAccepted ? 1 : 0);
        return result;
    }
}
