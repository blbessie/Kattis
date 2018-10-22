import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class sofas {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine()); // n = number of test cases

        Map<Integer, ArrayList<Integer>> typeTable = new HashMap<>();
        Map<Integer, ArrayList<Integer>> colorTable = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int lines = Integer.parseInt(scanner.nextLine());
            for(int j = 0; j < lines; j++) {
                String[] line = scanner.nextLine().split(" ");
                int type = Integer.parseInt(line[0]);
                int color = Integer.parseInt(line[1]);

                if(typeTable.get(type) == null) {
                    typeTable.put(type, new ArrayList<>());
                }
                if(colorTable.get(color) == null) {
                    colorTable.put(color, new ArrayList<>());
                }
                typeTable.get(type).add(color);
                colorTable.get(color).add(type);
            }
            // this is the number of Sofas
            int numberSofas = 1;

            for (Map.Entry<Integer, ArrayList<Integer>> map : typeTable.entrySet())
            {
                int typeLength = map.getValue().size();
                for(Integer colorIndex: map.getValue()) {
                    int colorLength = colorTable.get(colorIndex).size();
                    if(typeLength >= colorLength && colorLength > numberSofas) {
                        numberSofas = colorLength;
                    }
                }
            }
            System.out.println(numberSofas);
            typeTable.clear();
            colorTable.clear();
        }
    }
}