import java.util.Scanner;

public class OddBinomialCoefficients{
    /* Sierpinski triangle = fist N rows of pascal triangle mod 2
    *  reference: https://en.wikipedia.org/wiki/Sierpinski_triangle - wikipedia
    */
    static long numOdd(long n) {
        if (n < 2) {
            //for the first 2 levels, T(n) = n
            return n;
        } else {
            /*
            * find the number of odds in the spierpinski triangle which is equivalent to finding 
            * the number of ones in the binary representation of the number
            * each proper triangle contains 3^k ones, where k is the number of recursion levels
            * reference: http://ecademy.agnesscott.edu/~lriddle/ifs/siertri/pascal.htm
            */

            /*
            * The first 2^L rows are occupied by a level L triangle, where L is the highest bit in n. 
            * Recurse on the bottom n − 2 rows.
            */
            long numOnes = (long) 3 * numOdd(n >> 1);
            
            // if the level is multiple of 2, then it is a perfect sierpinski gasket triangle
            if(n % 2 == 0) {
                return numOnes;
            } else {
                // need to include the remaining odds
                return numOnes +  (1L << Long.bitCount(n - 1));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        System.out.println(numOdd(n));
    }
}
