import java.util.Scanner;

public class Yoda {
    static Scanner read = new Scanner(System.in);

    public static void main(String[] args){
        String N = read.nextLine();
        String M = read.nextLine();
        String n = "", m = "";

        int diff = M.length() - N.length();
        if (diff > 0){
            for(int i = 0; i < diff; i++){
                N = "0" + N;
            }
        }
        else if (diff < 0){
            for(int i = diff; i < 0; i++){
                M = "0" + M;
            }
        }

        for(int i = 0; i < N.length(); i++){
            if (N.charAt(i) > M.charAt(i)){
                n = n + N.charAt(i);
            }
            else if (N.charAt(i) < M.charAt(i)){
                m = m + M.charAt(i);
            }
            else{
                n = n + N.charAt(i);
                m = m + M.charAt(i);
            }
        }

        if(!(n.equals(""))) System.out.println(Integer.parseInt(n));
        else System.out.println("YODA");
        if(!(m.equals(""))) System.out.println(Integer.parseInt(m));
        else System.out.println("YODA");

        read.close();
    }



}
