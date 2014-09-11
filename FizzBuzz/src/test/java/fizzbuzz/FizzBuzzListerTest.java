package fizzbuzz;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class FizzBuzzListerTest {

    private FizzBuzzGenerator generatorMock = mock(FizzBuzzGenerator.class);
    private FizzBuzzLister lister = new FizzBuzzLister(generatorMock);

    @Test
    public void shouldReturnEmptyListWhenMaxIndexIsZero() {
        Assert.assertArrayEquals(new String[0], lister.list(0));
        verify(generatorMock, never()).compute(anyInt());
    }

    @Test
    public void shouldReturnFirstFiveItemsWhenGivenMaxIndexOfThree() {
        when(generatorMock.compute(1)).thenReturn("a");
        when(generatorMock.compute(2)).thenReturn("b");
        when(generatorMock.compute(3)).thenReturn("c");

        Assert.assertArrayEquals(new String[] {"a", "b", "c"}, lister.list(3));
    }
}
