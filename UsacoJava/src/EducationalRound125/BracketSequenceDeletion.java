package EducationalRound125;

import java.io.*;
import java.util.*;

public class BracketSequenceDeletion {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    public static void solve() throws IOException {
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        ArrayList<Boolean> buf = new ArrayList<>();
        int ops = 0;
        for (int i=0;i<N;i++){
            boolean C = str.charAt(i)=='(';
            buf.add(C);

            if ((buf.size()>2 && !C) ||
                    buf.size()==2 && !(!buf.get(0)&&buf.get(1))){
                ops++;
                buf = new ArrayList<Boolean>();
            }
        }
        out.println(ops+" "+buf.size());
    }
}
