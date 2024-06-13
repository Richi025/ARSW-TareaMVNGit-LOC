package edu.escuelaing.arsw.ASE.app; // This is a sample file to test the program

public class Example2 {

    /**
     * Adds two integers and returns the result.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Adds three integers and returns the result.
     *
     * @param a the first integer
     * @param b the second integer
     * @param c the third integer
     * @return the sum of a, b, and c
     */
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    /**
     * Adds an array of integers and returns the result.
     *
     * @param numbers an array of integers to be added
     * @return the sum of all integers in the array
     */
    public int add(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}