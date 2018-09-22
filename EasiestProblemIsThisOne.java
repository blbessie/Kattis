import java.util.Scanner;
public class EasiestProblemIsThisOne{

    public static void main(String []args){
        Scanner scan = new Scanner(System.in);
        int N = Integer.parseInt(scan.nextLine());
        int n = digitsSum(N);
        int p = 11;
        while(N != 0){
            while(digitsSum(p*N) != n ){
                p = p + 1;
            }
            System.out.println(p);
            p = 11;
            N = Integer.parseInt(scan.nextLine());
            n = digitsSum(N);
        }
    }

    public static int digitsSum(int n){
        String s = Integer.toString(n);
        int m = 0;
        for(int i = 0; i < s.length(); i++){
            m = m + s.charAt(i) - '0';
        }
        return m;
    }
}