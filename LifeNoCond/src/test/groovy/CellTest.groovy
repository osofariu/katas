import org.junit.Test

class CellTest {

    @Test
    public void whenAliveCellHasNoNeighborsItDiesInNextIteration() {
        Cell cell = new Cell();
        assert cell.iterate().statusValue == 0
    }

    @Test
    public void whenAliveCellHasTwoNeihnborsItStaysAliveInNextIteration() {
        Cell cell1 = new Cell(name: "1", statusValue: 1)
        Cell cell2 = new Cell(name: "2", statusValue: 1)
        Cell cell3 = new Cell(name: "3", statusValue: 1)
        cell1.makeAssociationWith cell2, 'W'
        cell1.makeAssociationWith cell3, 'E'
        assert cell1.iterate().statusValue == 1
    }

    @Test
    public void whenDeadCellHasTwoNeihnborsItDoesNotBecomeAliveInNextIteration() {
        Cell cell1 = new Cell(name: "1", statusValue: 0)
        Cell cell2 = new Cell(name: "2", statusValue: 1)
        Cell cell3 = new Cell(name: "3", statusValue: 1)
        cell1.makeAssociationWith cell2, 'W'
        cell1.makeAssociationWith cell3, 'E'
        assert cell1.iterate().statusValue == 0
    }

    @Test
    public void whenAliveCellHasOneNeighborItDiesInNextIteration() {
        Cell cell = new Cell(name: "1", statusValue: 1)
        cell.makeAssociationWith(new Cell(name: "2", statusValue: 1), 'W')
        assert cell.iterate().statusValue == 0
    }

    @Test
    public void whenAliveCellHasThreeNeighborsItStaysAliveInNextIteration() {
        Cell cell1 = new Cell(name: "1", statusValue: 1)
        Cell cell2 = new Cell(name: "2", statusValue: 1)
        Cell cell3 = new Cell(name: "3", statusValue: 1)
        Cell cell4 = new Cell(name: "4", statusValue: 1)
        cell1.makeAssociationWith cell2, 'W'
        cell1.makeAssociationWith cell3, 'E'
        cell1.makeAssociationWith cell4, 'S'
        assert cell1.iterate().statusValue == 1
    }

    @Test
    public void whenAliveCellHasFourNeighborsItStaysAliveInNextIteration() {
        Cell cell1 = new Cell(name: "1", statusValue: 1)
        Cell cell2 = new Cell(name: "2", statusValue: 1)
        Cell cell3 = new Cell(name: "3", statusValue: 1)
        Cell cell4 = new Cell(name: "4", statusValue: 1)
        Cell cell5 = new Cell(name: "4", statusValue: 1)
        cell1.makeAssociationWith cell2, 'W'
        cell1.makeAssociationWith cell3, 'E'
        cell1.makeAssociationWith cell4, 'S'
        cell1.makeAssociationWith cell5, 'N'
        assert cell1.iterate().statusValue == 0
    }

    @Test
    public void whenDeadCellHasThreeNeighborsItBecomesAliveInNextIteration() {
        Cell cell1 = new Cell(name: "1", statusValue: 0)
        Cell cell2 = new Cell(name: "2", statusValue: 1)
        Cell cell3 = new Cell(name: "3", statusValue: 1)
        Cell cell4 = new Cell(name: "4", statusValue: 1)
        cell1.makeAssociationWith cell2, 'W'
        cell1.makeAssociationWith cell3, 'E'
        cell1.makeAssociationWith cell4, 'S'
        assert cell1.iterate().statusValue == 1
    }
}
