package RandomBronze;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: Complete_Search.namenum
*/
import java.io.*;
import java.util.*;
public class namenum {
    public static void main(String[] args) throws IOException {
        //storing information and input/output
        BufferedReader f = new BufferedReader(new FileReader("Complete_Search.namenum.in"));
        BufferedReader dict = new BufferedReader(new FileReader("dict.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Complete_Search.namenum.out")));
        long number = Long.parseLong(f.readLine());
        //check if name's corresponding num is same as serial number. if so, its a possible match and print it.
        String line;
        Boolean temp = false;
        while ((line = dict.readLine()) != null) {
            newString spec = new newString(line);
            if (spec.toNum() == number) {
                out.println(line);
                temp = true;
            }
        }
        if (temp == false) {
            out.println("NONE");
        }
        out.close();

    }
}
class newString {
    private String name;

    public newString(String str) {
        name = str;
    }
    public long toNum() {
        Integer[] ref= {2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,0,7,7,8,8,8,9,9,9,0};
        ArrayList<String> splittedName = new ArrayList(Arrays.asList(name.split("")));
        String num = "";
        for (String c : splittedName) {
            int n = ref[c.charAt(0)-65];
            num += n;
        }
        return Long.parseLong(num);
    }
}

