import java.util.Scanner;
import java.util.Arrays;

public class ExactChange {

    static String findChange(int[] values, int[] denominations, int coinsNum, int W, int price) {

        Integer[][] opt = new Integer[coinsNum + 1][W + 1];
        opt[0][0] = 0;  //base case

        // Each row is a different coin/bill
        for (int i = 1; i <= coinsNum; i++) {
            int itemIndex = i - 1;

            for (int w = 0; w <= W; w++) {
                // Null entries are skipped
                if (opt[i - 1][w] == null)
                    continue;
                // We try to not include the bill to minimize the #bills
                opt[i][w] = min(opt[i][w], opt[i - 1][w]);
                // if weight doesn't exceed the capacity, add to the bag
                int totalWeight = w + denominations[itemIndex];
                if (totalWeight <= W) {
                    //min(include item, exclude item)
                    opt[i][totalWeight] = min(opt[i - 1][w] + values[itemIndex], opt[i][totalWeight]);
                }
            }
        }

        // The first non-null entry is the optimal output
        for (int weight = price;; weight++)
            if (opt[coinsNum][weight] != null)
                return weight + " " + opt[coinsNum][weight];
    }

    // Return the minimum, null = invalid case
    static Integer min(Integer a, Integer b) {
        if (a == null) return b;
        else if (b == null) return a;
        return Math.min(a, b);
    }


    public static void main(String[] args){
        Scanner read = new Scanner(System.in);
        int n = Integer.parseInt(read.nextLine()); //n = #test cases

        while (n > 0) {

            int price = Integer.parseInt(read.nextLine());
            int coinsNum = Integer.parseInt(read.nextLine());
            int[] denominations = new int[coinsNum];
            for (int i = 0; i < coinsNum; i++)
                denominations[i] = Integer.parseInt(read.nextLine());
            int[] values = new int[coinsNum];
            Arrays.fill(values, 1);
            int W = 20001; //capacity of knapsack

            System.out.println(findChange(values, denominations, coinsNum, W, price));
            n--;

        }
    }
}