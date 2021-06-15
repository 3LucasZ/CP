import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class Acowdemia_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); //number of entries
        int N = Integer.parseInt(st.nextToken()); //number of cows
        HashMap<String, Integer> cowNames = new HashMap();
        String sortedCowNames[] = new String[N];
        StringTokenizer cows = new StringTokenizer(br.readLine());
        String answer[][] = new String[N][N];
        for(int i=0;i<N;i++){
            String cowName = cows.nextToken();
            cowNames.put(cowName,i);
            sortedCowNames[i] = cowName;
            Arrays.fill(answer[i],"?");
        }
        Arrays.sort(sortedCowNames);

        for(int i=0;i<N;i++){
            answer[i][i] = "B";
        }



        for(int i=0;i<K;i++){
            String[] entry = br.readLine().split(" ");
            ArrayList<String> namedCows = new ArrayList<>();
            for(int j=0;j<N;j++){
                if(j>0){
                    if(isLexicographical(sortedCowNames,entry[j-1],entry[j])) {

                    }
                    else{
                        for(int k=j;k<entry.length;k++) {
                            int elderNum = cowNames.get(entry[k]);
                            for (int l = 0; l < j; l++) {
                                int youngNum = cowNames.get(entry[l]);
                                answer[elderNum][youngNum] = "1";
                                answer[youngNum][elderNum] = "0";
                            }
                        }
                    }
                }
                namedCows.add(entry[j]);
            }

        }

        for(int i=0;i<N;i++){
            String base = "";
            for(int j=0;j<N;j++){
                base += answer[i][j];
            }
            out.println(base);
        }
        out.close();
    }
    public static Boolean isLexicographical(String sortedCowNames[], String cowName1,String cowName2){
        int cow1 = 0;
        int cow2 = 0;
        for(int i=0;i<sortedCowNames.length;i++){
            if(sortedCowNames[i].equals(cowName1)){
                cow1 = i;
            }
            if(sortedCowNames[i].equals(cowName2)){
                cow2 = i;
            }
        }
        if(cow1 < cow2) {
            return true;
        }
        else{
            return false;
        }
    }
}
