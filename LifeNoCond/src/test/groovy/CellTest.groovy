import org.junit.Test

import static Cell.Status.ALIVE
import static Cell.Status.DEAD
import static org.junit.Assert.assertEquals

class CellTest {

    @Test
    public void whenCellHasNoNeighborsItDiesInNextIteration() {
        Cell cell = new Cell();
        assertEquals DEAD, cell.iterate().status
    }

    @Test
    public void whenCellHasTwoNeihnborsItStaysAliveInNextIteration() {
        Cell cell = new Cell(new Cell(), new Cell());
        assertEquals ALIVE, cell.iterate().status

    }
}
