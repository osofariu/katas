import org.junit.Test

import static org.junit.Assert.assertEquals

class GameTest {

    @Test
    public void startingWithNoCellsItRemainsEmpty() {
        Game game = new Game().iterate()
        assertEquals([], game.cells)
    }


    @Test
    public void havingThreeNeighboringCellsKeepsAtLeastOneAliveNextIteration() {
        Cell cell1 = new Cell()
        Cell cell2 = new Cell()
        Cell cell3 = new Cell()
        cell1.setNeighborAliveTo(Cell.NeighborDirection.W, cell2)
        cell1.setNeighborAliveTo(Cell.NeighborDirection.E, cell3)
        Game game = new Game([cell1, cell2, cell3])
        assertEquals(1, game.iterate().keepAliveCells().size())
    }
}