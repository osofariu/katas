package life

class World {
    HashSet  currentCells

    World(currentCells = []) {
        this.currentCells = currentCells
    }

    World iterate() {
        new World (updateCurrentCells() +  resurrectWorthyCells())
    }

    def size() {
        return currentCells.size()
    }

    Set updateCurrentCells() {
        def nextGenCells = [] as Set
        currentCells.each { Cell currentCell ->
            if (neighborCount(currentCell) in 2..3) {
                nextGenCells += currentCell
            }
        }
        nextGenCells
    }

    int neighborCount(Cell cell) {
        def count = 0
        makeNeighborCells(cell).each {
           count += cellIsAlive(it) ? 1 : 0
        }
        count
    }

    boolean cellIsAlive(cell) {
        currentCells.contains(cell)
    }

    Set resurrectWorthyCells() {
        Set worthyCells = []
        generatePotentialNewCells().each {Cell cell ->
            if (neighborCount(cell) == 3 && !cellIsAlive(cell)) {
                worthyCells.add(cell)
            }
        }
        worthyCells
    }

    Set generatePotentialNewCells() {
        Set potentialNewCells = []
        currentCells.each { Cell currentCell ->
            potentialNewCells +=  makeNeighborCells(currentCell)
        }
        potentialNewCells
    }

    private static Set makeNeighborCells(Cell cell) {
        def x = cell.x, y = cell.y
        Set neighborCells = []
        for (nx in x - 1..x + 1) {
            for (ny in y - 1..y + 1) {
                neighborCells += new Cell(nx, ny)
            }
        }
        neighborCells.remove(cell)
        neighborCells
    }
}
