package life
import org.junit.Test

import static org.junit.Assert.*

class WorldTest {

    @Test
    public void newWorldHasNoCells() {
        assertEquals(0, new World().size())
    }

    @Test
    public void afterOneIterationEmptyWorldRemainsEmpty() {
        World newWorld = new World().iterate()
        assertEquals(0, newWorld.size())
    }

    @Test
    public void cellWithTwoNeighborsSouthAndWestStaysAliveAfterOneIteration() {
        World world = new World([new Cell(2, 2), new Cell(2, 3), new Cell(1, 2)])
        World newWorld = world.iterate()
        assertTrue(isAlive(newWorld, 2, 2))
    }


    @Test
    public void cellWithTwoNeighborsSouthAndNorthStaysAliveAfterOneIteration() {
        World world = new World([new Cell(2, 2), new Cell(2, 3), new Cell(2, 1)])
        World newWorld = world.iterate()
        assertTrue(isAlive(newWorld, 2, 2))
    }

    @Test
    public void cellWithTwoNeighborsNEAndSWAliveAfterOneIteration() {
        World world = new World([new Cell(1, 1), new Cell(2, 2), new Cell(3, 3)])
        World newWorld = world.iterate()
        assertTrue(isAlive(newWorld, 2, 2))
    }

    @Test
    public void cellWithThreeNeighborsRemainsAliveAfterOneIteration() {
        World world = new World([new Cell(1, 1), new Cell(2, 2), new Cell(3, 3), new Cell(2, 3)])
        World newWorld = world.iterate()
        assertTrue(isAlive(newWorld, 2, 2))
    }

    @Test
    public void cellWithFourNeighborsDiesAfterOneIteration() {
        World world = new World([new Cell(1, 1), new Cell(2, 2), new Cell(3, 3), new Cell(2, 3), new Cell(3, 2)])
        World newWorld = world.iterate()
        assertFalse(isAlive(newWorld, 2, 2))
    }

    @Test
    public void cellWithOneNeighborsDiesAfterOneIteration() {
        World world = new World([new Cell(2, 2), new Cell(2, 3)])
        World newWorld = world.iterate()
        assertFalse(isAlive(newWorld, 2, 2))
    }

    @Test
    public void deadCellWithThreeNeighborsComesAlive() {
        World world = new World([new Cell(1, 1), new Cell(2, 1), new Cell(3, 1)])
        World newWorld = world.iterate()
        assertTrue(isAlive(newWorld, 2, 2))
        assertTrue(isAlive(newWorld, 2, 0))
    }

    @Test
    public void deadCellWithFourNeighborsDiesAfterOneIteration() {
        World world = new World([new Cell(1, 1), new Cell(3, 3), new Cell(2, 3), new Cell(3, 2)])
        World newWorld = world.iterate()
        assertFalse(isAlive(newWorld, 2, 2))
    }

    @Test
    public void aCellCanOnlyBeResurrectedOnce() {
        World world = new World([new Cell(2, 2), new Cell(3, 2), new Cell(3, 3)])
        World newWorld = world.iterate()
        assertEquals(new World([new Cell(2,2), new Cell(3,2), new Cell(2,3), new Cell(3,3)]), newWorld)
    }

    private static boolean isAlive(world, x, y) {
        return world.cellIsAlive(new Cell(x, y))
    }
}
