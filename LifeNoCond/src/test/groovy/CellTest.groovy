import org.junit.Test

import static org.junit.Assert.assertEquals

class CellTest {

    @Test
    public void whenCellHasNoNeighborsItDiesInNextIteration() {
        Cell cell = new Cell();
        assertEquals 0, cell.iterate().statusValue
    }

    @Test
    public void whenCellHasTwoNeihnborsItStaysAliveInNextIteration() {
        Cell cell1 = new Cell(name: "1")
        Cell cell2 = new Cell(name: "2")
        Cell cell3 = new Cell(name: "3")
        cell1.makeAssociationWith 'W', cell2
        cell1.makeAssociationWith 'E', cell3
        assertEquals 1, cell1.iterate().statusValue
    }
}
