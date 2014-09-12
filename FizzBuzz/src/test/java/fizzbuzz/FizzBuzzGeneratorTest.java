package fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FizzBuzzGeneratorTest {

    private FizzBuzzGenerator fizzBuzzGenerator = new FizzBuzzGenerator();

    @Test
    public void shouldReturn1ForFirstValue() {
        assertEquals("1", fizzBuzzGenerator.compute(1));
    }

    @Test
    public void shouldReturnFizzForFirstMultipleOfThree() {
        assertEquals("Fizz", fizzBuzzGenerator.compute(3));
    }

    @Test
    public void shouldReturnBuzzForFirstMultipleOfFive() {
        assertEquals("Buzz", fizzBuzzGenerator.compute(5));
    }

    @Test
    public void shouldReturnFizzBuzzForFirstMultipleOfThreeAndFive() {
        assertEquals("FizzBuzz", fizzBuzzGenerator.compute(15));
    }
}


