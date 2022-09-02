package USACO.Bronze.RandomBronze;import java.io.*;
import java.util.*;
import java.lang.*;
public class circlecross {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("circlecross.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
        List<String> st = new ArrayList<String>(Arrays.asList(f.readLine().split("")));
        int sum = 0;
        //out.println(st);
        for (int i=0;i<26;i++) {
            int first = st.indexOf(String.valueOf((char)('A'+i)));
            int second = st.lastIndexOf(String.valueOf((char)('A'+i)));
            List betweener = st.subList(first, second);

            int counter = -1;
            for (Object point : betweener) {
                if (betweener.indexOf(point) == betweener.lastIndexOf(point)) {
                    counter++;
                }
            }
            sum += counter;

        }

        out.println((int) sum/2);
        out.close();
    }
}
