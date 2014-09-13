import org.junit.Test

import static Cell.NeighborDirection.E
import static Cell.NeighborDirection.W
import static org.junit.Assert.assertEquals

class CellTest {

    @Test
    public void whenCellHasNoNeighborsItDiesInNextIteration() {
        Cell cell = new Cell();
        assertEquals 0, cell.iterate().status
    }

    @Test
    public void whenCellHasTwoNeihnborsItStaysAliveInNextIteration() {
        Cell cell1 = new Cell()
        Cell cell2 = new Cell()
        Cell cell3 = new Cell()
        cell1.setNeighborAliveTo W, cell2
        cell1.setNeighborAliveTo E, cell3
        assertEquals 1, cell1.iterate().status
    }
}
