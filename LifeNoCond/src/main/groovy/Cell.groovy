

class Cell {
    Status status = Status.ALIVE
    List<Cell> neighbors
    def nextStatus = [Status.DEAD, Status.DEAD, Status.ALIVE] as Status[]

    Cell(Cell...cells) {
          neighbors = Arrays.asList(cells)
    }

    Cell iterate() {
        updateStatus();
        this;
    }

    def updateStatus() {
        status = nextStatus[neighbors.size()]
    }

    enum Status {DEAD, ALIVE}
}
