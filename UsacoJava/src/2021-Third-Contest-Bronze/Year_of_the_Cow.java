import java.io.*;
import java.util.*;

public class Year_of_the_Cow {
    //set up
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static String zodia[] = {"Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Goat", "Monkey", "Rooster", "Dog", "Pig", "Rat"};
    static ArrayList<String> zodiac = new ArrayList<>(Arrays.asList(zodia));
    public static void main(String[] args) throws IOException {
        Map<String,Integer> cows = new HashMap<>();
        int N = Integer.parseInt(f.readLine());

        cows.put("Bessie",0);
        for (int i=0;i<N;i++){
            List<String> entry = new ArrayList<String>(Arrays.asList(f.readLine().split(" ")));
            String newCow = entry.get(0);
            String direction = entry.get(3);
            String animal = entry.get(4);
            String relation = entry.get(7);
            cows.put(newCow,cows.get(relation) + years(direction,get_animal(cows.get(relation)),animal));
        }
        out.println(Math.abs(cows.get("Elsie") - cows.get("Bessie")));
        out.close();

    }
    static int years(String direction, String curA, String newA) {
        int index = zodiac.indexOf(curA);
        int years = 0;
        while (!zodiac.get(index).equals(newA) || years == 0) {
            if(direction.equals("next")) {
                years += 1;
                index += 1;
                if (index == 12) {
                    index = 0;
                }
            }
            else {
                years -= 1;
                index -= 1;
                if (index == -1) {
                    index = 11;
                }
            }
        }
        return years;
    }
    static String get_animal(int year) {
        return zodiac.get((year % 12 + 12)%12);
    }
}
