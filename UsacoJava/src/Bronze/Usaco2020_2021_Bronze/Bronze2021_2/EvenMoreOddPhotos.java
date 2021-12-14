package Bronze.Usaco2020_2021_Bronze.Bronze2021_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EvenMoreOddPhotos {
    //param
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //parse input
        n = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        //logic
        int odds = 0;
        int evens = 0;
        for (int i=0;i<n;i++) {
            if (Integer.parseInt(st.nextToken()) % 2 == 0) evens ++;
            else odds ++;
        }
        if (evens > odds) {
            out.println(2*odds+1);
        }
        else if (evens == odds) {
            out.println(2*odds);
        }
        else {
            int add = 0;
            if ((odds - evens) % 3 == 2) {
                add = 1;
            }
            if ((odds - evens) % 3 == 1) {
                add = -1;
            }
            out.println((evens * 2) + (2 * ((odds - evens)/ 3)) + add);
        }
        out.close();
        f.close();
    }
}
