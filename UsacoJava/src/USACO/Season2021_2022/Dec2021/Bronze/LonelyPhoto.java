package USACO.Season2021_2022.Dec2021.Bronze;

import java.io.*;
import java.util.*;
public class LonelyPhoto {
    static BufferedReader f;
    static PrintWriter out;
    public static void main(String[] args) throws IOException {
        f=new BufferedReader(new InputStreamReader(System.in));
        out=new PrintWriter(System.out);
        int n = Integer.parseInt(f.readLine());
        boolean[] cowG = new boolean[n];
        ArrayList<Integer> length = new ArrayList<>();
        String str = f.readLine();
        char last = 'X';
        for(int i=0;i<n;i++) {
            //cowG[i]=str.charAt(i)=='G'?true:false;
            if(str.charAt(i)==last){
                length.set(length.size()-1,length.get(length.size()-1)+1);
            }
            else{
                length.add(1);
            }
            last = str.charAt(i);
        }
        //out.println(Arrays.toString(cows));
        out.println(length);
        int ans = 0;
        for (int i=0;i<length.size()-1;i++){
            if(length.get(i)>=2 && length.get(i+1)>=2){
                ans += (length.get(i)+length.get(i+1)-2);
            }
            else if (length.get(i)>=2 && length.get(i+1)==1) {
                int end;
                if (i+2>=length.size()) end = 0;
                else end=length.get(i+2);
                ans += (length.get(i)*end);
                i++;
            }
            else if (length.get(i)==1 && length.get(i+1)>=2) {
                int end;
                if (i+2>=length.size()) end = 0;
                else end=length.get(i+2);
                ans += (length.get(i)*end);
            }
            else if (length.get(i)==1 && length.get(i+1)==1){
                ans += length.get(i+2);
            }
        }
        out.println(ans);
        out.close();
    }
}
