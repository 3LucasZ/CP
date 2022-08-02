import java.io.*;
import java.util.*;

/*
PROB: contact
LANG: JAVA
 */
public class contact {
    static boolean submission = true;
    static boolean debug = false;

    static int A,B,N,L;
    static ArrayList<Integer> seq = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //parse
        setup("contact");
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        String str;
        while ((str = br.readLine()) != null && str.length() > 0) for (char c : str.toCharArray()) seq.add(c-'0');
        L = seq.size();
        if (debug){
            System.out.println(seq);
            System.out.println(L);
            System.out.println();
        }

        //complete search
        //cnt[len][bin value]
        int cnt[][] = new int[13][1<<13];
        for (int len=A;len<=B;len++){
            for (int i=0;i<=L-len;i++){
                int val = 0;
                int pow = 1;
                for (int j=len-1;j>=0;j--){
                    val+=pow*seq.get(i+j);
                    pow*=2;
                }
                cnt[len][val]++;
            }
        }

        //ret
        ArrayList<Obj> ret = new ArrayList<>();
        for (int i=0;i<(1<<12);i++) for (int j=A;j<=B;j++) if (cnt[j][i]!=0) ret.add(new Obj(j,i,cnt[j][i]));
        Collections.sort(ret,(a,b)->{
            if (b.cnt==a.cnt){
                if (b.num==a.num){
                    //sort by smallest num (2);
                    return a.num-b.num;
                }
                //sort by smallest rep size (3);
                return a.len-b.len;
            }
            //sort by largest cnt (1)
            return b.cnt-a.cnt;
        });

        //object index
        int obj=0;
        //ith most frequent loop
        for (int i=0;i<N;i++){
            int freq = ret.get(obj).cnt;
            int online = 0;
            out.println(freq);

            while (ret.get(obj).cnt==freq){
                if (online%6==0) {if (online!=0) out.println();}
                else out.print(" ");

                ret.get(obj).print();
                obj++;
                online++;
                if (obj>=ret.size()) break;
            }
            out.println();
            if (obj>=ret.size()) break;
        }
        out.close();

    }
    private static class Obj {
        int len;
        int num;
        int cnt;
        public Obj(int l, int n, int c){
            len=l;
            num=n;
            cnt=c;
        }
        public void print(){
            String bin = Integer.toBinaryString(num);
            for (int j=0;j<len-bin.length();j++) out.print(0);
            out.print(bin);
        }
    }



















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
/*
1 4 10
1
 */