import java.util.Arrays;
import java.util.Scanner;

public class NarrowArtGallery{
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        String[] s = reader.nextLine().split(" ");
        int n = Integer.parseInt(s[0]); //n = #gallery rows
        int k = Integer.parseInt(s[1]); //k = #closed doors

        while (n != 0) {
            //gallery stores the right and left room values of current row
            int[][] gallery = new int[n][2];
            int infinity = Integer.MIN_VALUE;  //invalid value
            for (int i = 0; i < n; i++) {
                s = reader.nextLine().split(" ");
                gallery[i][0] = Integer.parseInt(s[0]);
                gallery[i][1] = Integer.parseInt(s[1]);
            }

            /*  1st dim: #gallery rows
             *  2nd dim: #closed doors in current row
             *  3rd dim: 0/1/2 which indicate left/right/no room blocked of the current processing row
             */

            int[][][] arr = new int[n][k + 1][3];
            // assign array to negative infinity - everything invalid
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= k; j++) {
                    Arrays.fill(arr[i][j], infinity);
                }
            }
            //Base case: the first row with no doors closed
            arr[0][0][0] = infinity; //invalid case
            arr[0][0][1] = infinity; //invalid case
            arr[0][0][2] = gallery[0][0] + gallery[0][1]; //sum of the 2 rooms in current row

            //if no doors close for every row
            for (int i = 1; i < n; i++) {
                arr[i][0][0] = infinity;  //invalid case
                arr[i][0][1] = infinity;  //invalid case
                arr[i][0][2] = arr[i - 1][0][2] + gallery[i][0] + gallery[i][1]; //sum of the prev row rooms + cur row rooms
            }

            //if more than 1 door closed
            if (k > 0) {
                arr[0][1][0] = gallery[0][1]; //close left door = right room value stored
                arr[0][1][1] = gallery[0][0]; //close right door = left room value stored
                arr[0][1][2] = infinity;      //invalid case
            }


            // Iterate through all rows and possible values of closed doors
            for (int i = 1; i < n; i++) {
                for (int j = 1; j <= Math.min(i + 1, k); j++) {

                    /*
                    * at i row and j door closed:
                    * close left: right + max of (prev row's value of close right room and no room closed)
                    * close right: left + max of (prev row's value of close left room and no room closed)
                    * no door closed: right + left + max of (prev row's : close left / close right / no close)
                    */
                    arr[i][j][0] = gallery[i][1] + Math.max(arr[i - 1][j - 1][2], arr[i - 1][j - 1][0]);
                    arr[i][j][1] = gallery[i][0] + Math.max(arr[i - 1][j - 1][2], arr[i - 1][j - 1][1]);
                    arr[i][j][2] = gallery[i][0] + gallery[i][1] + Math.max(arr[i - 1][j][0], Math.max(arr[i - 1][j][1], arr[i - 1][j][2]));
                }
            }

            //result is the max value of the last row in array
            double output = Math.max(arr[n - 1][k][0], Math.max(arr[n - 1][k][1], arr[n - 1][k][2]));
            System.out.println(output);
            s = reader.nextLine().split(" ");
            n = Integer.parseInt(s[0]);
            k = Integer.parseInt(s[1]);
        }
    }
}
