package life

class World {
    def currentCells

    World(currentCells = []) {
        this.currentCells = currentCells
    }

    World iterate() {
        def nextGenCells = []
        Set potentialNewCells = []
        currentCells.each { Cell cell ->
            potentialNewCells += getNeighboringLocations(cell)
            def nc = neighborCount(cell)
            if (nc in 2..3) {
                nextGenCells.add(cell)
            }
        }
        resurrectWorthyCells(potentialNewCells).each {
            nextGenCells += it
        }

        new World(nextGenCells)
    }

    Cell[] resurrectWorthyCells(potentialCells) {
        def worthyCells = []
        potentialCells.each { cell ->
            if (neighborCount(cell) == 3 && !cellIsAlive(cell)) {
                worthyCells.add(cell)
            }
        }
        worthyCells
    }

    boolean cellIsAlive(cell) {
        currentCells.find { it.isAt(cell.x, cell.y) }
    }

    int neighborCount(Cell cell) {
        def count = 0
        getNeighboringLocations(cell).each {
            if (cellIsAlive(it))
                count++
        }
        count
    }

    private static def getNeighboringLocations(Cell cell) {
        def neighbors = []
        def x = cell.x, y = cell.y

        for (nx in x - 1..x + 1) {
            for (ny in y - 1..y + 1) {
                addNeighborCellToList(cell, nx, ny, neighbors)
            }
        }
        neighbors
    }

    private static void addNeighborCellToList(Cell cell, int x, int y, neighbors) {
        if (!cell.isAt(x, y))
            neighbors.add(new Cell(x, y))
    }
}
