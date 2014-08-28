package life

class World {
    def cells

    World(cells = []) {
        this.cells = cells
    }

    World iterate() {
        def newCells = []
        Set potentialNewLife = []
        cells.each { Cell cell ->
            potentialNewLife = (potentialNewLife << getNeighboringLocations(cell)).flatten()
            if (neighborCount(cell) in 2..3)
                newCells.add(cell)
        }
        new World((newCells << resurrectWorthyCells(potentialNewLife)).flatten())
    }

    Cell[] resurrectWorthyCells(cellLocations) {
        def worthyCells = []
        cellLocations.each { coordinate ->
            def x = coordinate.x, y = coordinate.y
            Cell cell = new Cell(x, y)
            if (neighborCount(cell) == 3 && !cellAliveAt(coordinate.x, coordinate.y)) {
                worthyCells.add(cell)
            }
        }
        worthyCells
    }

    boolean cellAliveAt(x, y) {
        cells.find { it.isAt(x, y) }

    }

    int neighborCount(Cell cell) {
        def count = 0
        getNeighboringLocations(cell).each { coordinate ->
            if (cellAliveAt(coordinate.x, coordinate.y))
                count++
        }
        count
    }

    private static def getNeighboringLocations(Cell cell) {
        Set neighbors = []
        def x = cell.x, y = cell.y

        for (nx in x - 1..x + 1) {
            for (ny in y - 1..y + 1) {
                addLocationToList(cell, nx, ny, neighbors)
            }
        }
        return neighbors
    }

    private static void addLocationToList(Cell cell, int nx, int ny, Set neighbors) {
        if (!cell.isAt(nx, ny))
            neighbors.add([x: nx, y: ny])
    }
}
