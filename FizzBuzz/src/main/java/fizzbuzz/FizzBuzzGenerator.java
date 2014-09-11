package fizzbuzz;

public class FizzBuzzGenerator {

    public String compute(int index) {
        if (index % 15 == 0)
            return "FizzBuzz";
        else if (index % 3 == 0)
            return "Fizz";
        else if (index % 5 == 0)
            return "Buzz";
        else
            return String.valueOf(index);
    }
}
