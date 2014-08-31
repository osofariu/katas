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
        return "new Cell($x,$y)"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Cell cell = (Cell) o

        if (x != cell.x) return false
        if (y != cell.y) return false

        return true
    }

    int hashCode() {
        int result
        result = x
        result = 31 * result + y
        return result
    }
}
