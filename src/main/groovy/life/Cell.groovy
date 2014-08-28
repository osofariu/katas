package life

class Cell {
    int x
    int y

    Cell(x, y) {
        this.x = x
        this.y = y
    }

    public boolean isAt(int x, int y) {
        (this.x == x && this.y == y)

    }

    String toString() {
        return "Cell[$x,$y]"
    }
}
