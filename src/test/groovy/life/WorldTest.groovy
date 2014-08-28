package life
import org.junit.Test

import static org.junit.Assert.*

class WorldTest {

    @Test
    public void newWorldHasNoCells() {
        assertEquals([], new World().cells)
    }

    @Test
    public void afterOneIterationEmptyWorldRemainsEmpty() {
        World newWorld = new World().iterate()
        assertEquals([], newWorld.cells)
    }

    @Test
    public void cellWithTwoNeighborsSouthAndWestStaysAliveAfterOneIteration() {
        World world = new World([new Cell(2, 2), new Cell(2, 3), new Cell(1, 2)])
        World newWorld = world.iterate()
        assertTrue(newWorld.cellAliveAt(2, 2))
    }


    @Test
    public void cellWithTwoNeighborsSouthAndNorthStaysAliveAfterOneIteration() {
        World world = new World([new Cell(2, 2), new Cell(2, 3), new Cell(2, 1)])
        World newWorld = world.iterate()
        assertTrue(newWorld.cellAliveAt(2, 2))
    }

    @Test
    public void cellWithTwoNeighborsNEAndSWAliveAfterOneIteration() {
        World world = new World([new Cell(1, 1), new Cell(2, 2), new Cell(3, 3)])
        World newWorld = world.iterate()
        assertTrue(newWorld.cellAliveAt(2, 2))
    }

    @Test
    public void cellWithThreeNeighborsRemainsAliveAfterOneIteration() {
        World world = new World([new Cell(1, 1), new Cell(2, 2), new Cell(3, 3), new Cell(2, 3)])
        World newWorld = world.iterate()
        assertTrue(newWorld.cellAliveAt(2, 2))
    }

    @Test
    public void cellWithFourNeighborsDiesAfterOneIteration() {
        World world = new World([new Cell(1, 1), new Cell(2, 2), new Cell(3, 3), new Cell(2, 3), new Cell(3, 2)])
        World newWorld = world.iterate()
        assertFalse(newWorld.cellAliveAt(2, 2))
    }

    @Test
    public void cellWithOneNeighborsDiesAfterOneIteration() {
        World world = new World([new Cell(2, 2), new Cell(2, 3)])
        World newWorld = world.iterate()
        assertFalse(newWorld.cellAliveAt(2, 2))
    }

    @Test
    public void deadCellWithThreeNeighborsComesAlive() {
        World world = new World([new Cell(1, 1), new Cell(2, 1), new Cell(3, 1)])
        World newWorld = world.iterate()
        assertTrue(newWorld.cellAliveAt(2, 2))
    }

    @Test
    public void aCellCanOnlyBeResurrectedOnce() {
        World world = new World ([new Cell(2,2), new Cell(3,2), new Cell(3,3) ])
        World newWorld = world.iterate()
        assertEquals(4, newWorld.cells.size())
    }
}

/*

0 1 2 3 4 5 6  (x)
1
2   x x
3   0 x
4
5
6
(y)
 */