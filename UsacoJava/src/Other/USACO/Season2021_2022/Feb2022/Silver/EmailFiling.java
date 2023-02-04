package Other.USACO.Season2021_2022.Feb2022.Silver;

import java.io.*;
import java.util.*;

public class EmailFiling {
    static boolean debug = false;

    static int M,N,K;
    static int[] f;
    static int[] lastf;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        M = io.nextInt(); N = io.nextInt(); K = io.nextInt();
        f = new int[N+1];
        lastf = new int[M+1];
        for (int i=1;i<=N;i++){
            f[i]=io.nextInt();
            lastf[f[i]]=i;
        }
        if (debug){
            io.println("f: "+Arrays.toString(f));
            io.println("lastf: "+Arrays.toString(lastf));
        }
        //* simulate scroll emails down
        int topFolder = 1;
        TreeSet<Integer> emails = new TreeSet<>();
        ArrayList<Integer> lost = new ArrayList<>();
        for (int lastEmail=1;lastEmail<=N;lastEmail++){
            //ms already full, we are stuck and must move emails down
            if (emails.size()==K){
                lost.add(emails.pollFirst());
            }
            //last of topFolder has been reached
            if (lastf[topFolder]<=lastEmail){
                //move top folder
                topFolder++;
                //remove from ms all topFolder+K
                Iterator<Integer> it = emails.iterator();
                while (it.hasNext()) {
                    int next = it.next();
                    if (topFolder<=f[next] && f[next]<=topFolder+K-1) it.remove();
                }
                continue;
            }
            //file new email when you can
            if (topFolder<=f[lastEmail] && f[lastEmail]<=topFolder+K-1) continue;
            //add  new email
            emails.add(lastEmail);
        }
        if (debug){
            io.println("lost: "+lost);
            io.println("ending emails: "+emails);
        }

        //* simulate but can't scroll emails down
        Collections.reverse(lost);
        for (int lastEmail : lost) {
            //ms full
            while (emails.size() == K) {
                //move top folder
                topFolder++;
                //cant move anymore
                if (topFolder > M - K + 1) {
                    if (debug)
                    {
                        io.println("final topFolder: "+topFolder);
                        io.println("final ending emails: "+emails);
                    }
                    io.println("NO");
                    return;
                }
                //file what you can
                Iterator<Integer> it = emails.iterator();
                while (it.hasNext()) {
                    int next = it.next();
                    if (topFolder<=f[next] && f[next]<=topFolder+K-1) it.remove();
                }
            }
            //file when you can
            if (topFolder<=f[lastEmail] && f[lastEmail] <= topFolder+K-1) continue;
            //add until ms is full
            emails.add(lastEmail);
        }
        while (emails.size() != 0) {
            //move top folder
            topFolder++;
            //cant move anymore
            if (topFolder > M - K + 1) {
                if (debug) {
                    io.println("final topFolder: "+topFolder);
                    io.println("final ending emails: "+emails);
                }
                io.println("NO");
                return;
            }
            //file what you can
            Iterator<Integer> it = emails.iterator();
            while (it.hasNext()) {
                int next = it.next();
                if (topFolder<=f[next] && f[next]<=topFolder+K-1) it.remove();
            }
        }
        //win
        io.println("YES");
    }
    private static class Multiset {
        TreeMap<Integer, Integer> ms = new TreeMap<>();
        int sz = 0;
        public void add(int x){
            if (!ms.containsKey(x))ms.put(x,0);
            ms.put(x,ms.get(x)+1);
            sz++;
        }
        public void rem(int x){
            ms.put(x,ms.get(x)-1);
            if (ms.get(x)==0) ms.remove(x);
            sz--;
        }
        public int size(){
            return sz;
        }
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
    private static class IO {
    BufferedReader br;
    StringTokenizer st;
    PrintWriter out;
    boolean debug;
    public IO(boolean dbg)  {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        debug=dbg;
    }
    public IO(String fileName, boolean dbg) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(fileName));
        out = new PrintWriter(System.out);
        debug=dbg;
    }
    String next()
    {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    int nextInt() { return Integer.parseInt(next()); }
    long nextLong() { return Long.parseLong(next()); }
    double nextDouble() {return Double.parseDouble(next());}
    String nextLine() {
        String str = "";
        try {
            if(st.hasMoreTokens()){
                str = st.nextToken("\n");
            }
            else{
                str = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    void println(){
        if (debug) System.out.println();
        else out.println();
    }
    void println(Object obj){
        if (debug) System.out.println(obj);
        else out.println(obj);
    }
    void print(Object obj){
        if (debug) System.out.print(obj);
        else out.print(obj);
    }
    void close(){
        out.close();
    }
}}