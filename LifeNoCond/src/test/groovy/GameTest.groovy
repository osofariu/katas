import org.junit.Test

import static org.junit.Assert.assertEquals

class GameTest {

    @Test
    public void whenIteratingOverGameWithNoCellsItRemainsEmpty() {
        Game game = new Game().iterate()
        assertEquals([], game.cells)
    }
}