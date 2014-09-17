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
        cell1.associate(cell2, 'W')
        cell1.associate(cell3, 'E')
        Game game = new Game([cell1, cell2, cell3])
        assert game.iterate().countAliveCells() == 1
    }

    @Test
    public void havingThreeNeighboringCellsAsCornerKeepsAllAliveNextIteration() {
        Cell cell1 = new Cell(name: 1, statusValue: 1)
        Cell cell2 = new Cell(name: 2, statusValue: 1)
        Cell cell3 = new Cell(name: 3, statusValue: 1)
        cell1.associate(cell2, 'W')
        cell1.associate(cell3, 'S')
        Game game = new Game([cell1, cell2, cell3])
        assert game.iterate().countAliveCells() == 3
    }

    @Test
    public void stillLifeConfigurationPersistsOverTwoIterations() {
        Cell cell1 = new Cell(name: 1, statusValue: 1)
        Cell cell2 = new Cell(name: 2, statusValue: 1)
        Cell cell3 = new Cell(name: 3, statusValue: 1)
        Cell cell4 = new Cell(name: 4, statusValue: 1)
        cell1.associate(cell2, 'E')
        cell2.associate(cell3, 'S')
        cell3.associate(cell4, 'W')
        Game game = new Game([cell1, cell2, cell3, cell4])
        game.iterate()
        assert game.countAliveCells() == 4
        game.iterate()
        assert game.countAliveCells() == 4
    }
}