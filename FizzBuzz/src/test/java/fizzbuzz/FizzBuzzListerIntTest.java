package fizzbuzz;

import org.junit.Assert;
import org.junit.Test;

public class FizzBuzzListerIntTest {

    private FizzBuzzLister fizzBuzzLister = new FizzBuzzLister(new FizzBuzzGenerator());

    @Test
    public void shouldListFirstFizzBuzzValueWhenGivenMaxIndexOf1() {
        Assert.assertArrayEquals(new String[] {"1"}, fizzBuzzLister.list(1));
    }

    @Test
    public void shouldListFirstFifteenFizzBuzzValuesWhenMaxIndexIs15() {
        Assert.assertArrayEquals(new String[] {"1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"}, fizzBuzzLister.list(15));
    }
}
