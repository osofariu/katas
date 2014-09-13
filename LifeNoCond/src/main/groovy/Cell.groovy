

class Cell {

    enum NeighborDirection {
        N, NE, E, SE, S, SW, W, NW
    }

    def neighbors = [] as HashMap
    def status = 0

    def nextStatusLookup = [0, 0, 1]

    Cell() {
        status = 1
    }

    Cell iterate() {
        status = nextStatusLookup[countAliveNeighbors()]
        this;
    }


    int countAliveNeighbors() {
        def alive = 0
        neighbors.values().each { cell ->
            alive += cell.status
        }
        alive
    }

    def setNeighborAliveTo(NeighborDirection neighborDirection, Cell cell) {
        neighbors.put(neighborDirection, cell)
    }

    @Override
    String toString() {
        "Cell with status: " + status
    }
}
