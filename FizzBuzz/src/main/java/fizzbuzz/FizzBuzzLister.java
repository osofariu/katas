package fizzbuzz;

import fizzbuzz.FizzBuzzGenerator;

public class FizzBuzzLister {

    FizzBuzzGenerator fizzBuzzGenerator;
    public FizzBuzzLister(FizzBuzzGenerator generator) {
        fizzBuzzGenerator = generator;
    }

    public String[] list(int maxIndex) {
        String[] fizzBuzzList = new String[maxIndex];
        for (int index = 0; index < maxIndex; index++) {
            fizzBuzzList[index] = fizzBuzzGenerator.compute(index+1);
        }
        return fizzBuzzList;
    }
}
