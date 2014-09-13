class Game {
    ArrayList<Cell> cells

    Game(cells = []) {
        this.cells = cells
    }

    Game iterate() {
        cells.each { cell ->
            cell.iterate()
        }

        new Game(keepAliveCells())
    }

    def keepAliveCells() {
        cells.removeAll {
            it.status == 0
        }
        cells
    }
}