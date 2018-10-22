import java.util.Scanner;

public class AllAboutThatBase {
    static String output="";

    public static String convertToBase(int toBase, String input){
        try{
            if(Integer.parseInt(input, toBase) > Integer.MAX_VALUE) return "-31";
        }catch(Exception e){
            return Integer.MIN_VALUE+""; //if exception, return a value that is very unlikely to get correct result
        }
        return  Integer.toString(Integer.parseInt(input, toBase), 10);
    }

    public static char largestAlpha(String s){
        char result = s.charAt(0);
        for(int i = 1; i < s.length(); i++)
            result = result > s.charAt(i) ? result : s.charAt(i);
        return result;
    }

    public static boolean isValidBase(String val1, String val2, String op, String r){
        int X = Integer.parseInt(val1);
        int Y = Integer.parseInt(val2);
        int result = Integer.parseInt(r);
        if(op.equals("+")){
            return ((X + Y) == result);
        }else if (op.equals("-")){
            return ((X - Y) == result);
        }else if (op.equals("/")){
            return ((X*1.0 / Y) == result);
        }else return ((X * Y) == result);

    }

    public static void testAllBases(String input){
        String tokens[] = input.split(" ");
        String val1 = tokens[0];
        String op = tokens[1];
        String val2 = tokens[2];
        String val3 = tokens[4];

        int val1Large = largestAlpha(val1);
        int val2Large = largestAlpha(val2);
        int val3Large = largestAlpha(val3);

        String X,Y,result;

        //get int rep from char
        //eg. 'a' means base 10
        int largestDigit = Character.getNumericValue(Math.max(Math.max(val1Large,val2Large),val3Large));

        //consider for edge cases of base 1
        boolean base1= true;
        for(int i = 0; i < val1.length(); i++){
            if(val1.charAt(i) != '1') base1 = false;
        }
        for(int i = 0; i < val2.length(); i++){
            if(val2.charAt(i) != '1') base1 = false;
        }
        for(int i = 0; i < val3.length(); i++){
            if(val3.charAt(i) != '1') base1 = false;
        }
        if(base1){
            String x = ""+val1.length();
            String y = ""+val2.length();
            String z = ""+val3.length();

            if(isValidBase(x,y,op,z)) output = output + 1;
        }


        //range of valid bases:
        for(int base = largestDigit + 1; base <= 36; base++){
            X = convertToBase(base,val1);
            Y = convertToBase(base,val2);
            result = convertToBase(base,val3);
            if(isValidBase(X,Y,op,result)){
                if(base >= 1 && base <= 9)  output = output + base;
                else if (base >= 10 && base <= 35) output = output + (char)(base+87);
                else if (base == 36) output = output + 0;
            }
        }
        if(output.equals("")) output = "invalid";

    }

    public static void main(String args[]){
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        String garbage = read.nextLine();
        for(int i = 0; i < n; i++){
            testAllBases(read.nextLine());
            System.out.println(output);
            output = "";
        }
        read.close();
    }
}
