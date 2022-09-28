package USACO.Season2021_2022.Dec2021.Bronze;

import java.io.*;
import java.util.*;
public class LonelyPhoto3 {
    static BufferedReader f;
    static PrintWriter out;
    public static void main(String[] args) throws IOException {
        f=new BufferedReader(new InputStreamReader(System.in));
        out=new PrintWriter(System.out);
        int n = Integer.parseInt(f.readLine());
        ArrayList<Integer> length = new ArrayList<>();
        String str = f.readLine();
        char last = 'X';
        for(int i=0;i<n;i++) {
            if(str.charAt(i)==last){
                length.set(length.size()-1,length.get(length.size()-1)+1);
            }
            else{
                length.add(1);
            }
            last = str.charAt(i);
        }
        //out.println(length);
        long ans = 0;
        for (int i=0;i<length.size();i++) {
            if (length.get(i)==1){
                if (i!=n-1&&length.get(i+1)>=2) {
                    ans += (long) (length.get(i+1)-1);
                }
                if (i!=0&&length.get(i-1)>=2) {
                    ans += (long) (length.get(i-1)-1);
                }
                if (i!=0&&i!=n-1) {
                    ans += (long) ((long) length.get(i + 1) * (long) length.get(i - 1));
                }
            }
        }
        for (int i=0;i<length.size()-1;i++) {
            if(length.get(i)>1 && length.get(i+1)>1) {
                ans += (long)(length.get(i)+length.get(i+1)-2);
            }
        }
        out.println(ans);
        out.close();
    }
}
