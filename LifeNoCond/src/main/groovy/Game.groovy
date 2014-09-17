class Game {
    ArrayList<Cell> cells

    Game(cells = []) {
        this.cells = cells
    }

    Game iterate() {
        cells.each { cell ->
            cell.iterate()
        }
    }

    def countAliveCells() {
        cells.inject(0) { acc, cell ->
            acc += cell.statusValue
        }
    }
}