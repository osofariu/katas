import org.junit.Test

import static org.junit.Assert.assertEquals

class GameTest {

    @Test
    public void startingWithNoCellsItRemainsEmpty() {
        Game game = new Game().iterate()
        assertEquals([], game.cells)
    }


    @Test
    public void havingThreeNeighboringCellInLinesKeepsOneAliveNextIteration() {
        Cell cell1 = new Cell(name: 'cell1', statusValue: 1)
        Cell cell2 = new Cell(name: 'cell2', statusValue: 1)
        Cell cell3 = new Cell(name: 'cell3', statusValue: 1)
        cell1.associate('W', cell2)
        cell1.associate('E', cell3)
        Game game = new Game([cell1, cell2, cell3])
        assertEquals(1, game.iterate().keepAliveCells().size())
    }

    @Test
    public void havingThreeNeighboringCellsAsCornerKeepsAllAliveNextIteration() {
        Cell cell1 = new Cell(name: 1, statusValue: 1)
        Cell cell2 = new Cell(name: 2, statusValue: 1)
        Cell cell3 = new Cell(name: 3, statusValue: 1)
        cell1.associate('W', cell2)
        cell1.associate('S', cell3)
        Game game = new Game([cell1, cell2, cell3])
        assertEquals(3, game.iterate().keepAliveCells().size())
    }
}