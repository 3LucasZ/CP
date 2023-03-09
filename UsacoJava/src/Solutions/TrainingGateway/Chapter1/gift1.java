package Solutions.TrainingGateway.Chapter1;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: Ad_hoc.gift1
*/
import java.io.*;
import java.util.*;

public class gift1 {
    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("Ad_hoc.gift1.in"));

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Ad_hoc.gift1.out")));
        int people = Integer.parseInt(f.readLine());

        ArrayList<String> names = new ArrayList<String>();
        Hashtable<String, Integer> money_dict = new Hashtable<String, Integer>();
        for(int i = 0; i < people; i++) {
            String key = f.readLine();
            money_dict.put(key, 0);
            names.add(key);
        }

        for(int i = 0; i < people; i++) {
            String giver = f.readLine();
            StringTokenizer st = new StringTokenizer(f.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            if (num2 != 0) {
                money_dict.put(giver, money_dict.get(giver)-num1);
                int personReceives = num1/num2;
                int kept = num1 % num2;
                money_dict.put(giver, money_dict.get(giver)+kept);
                for(int j = 0; j < num2; j++){
                    String receiver = f.readLine();
                    money_dict.put(receiver, money_dict.get(receiver)+personReceives);
                }
            }
        }
        for (String name: names) {
            out.println(name + " " + money_dict.get(name));
        }
        out.close();
    }
}