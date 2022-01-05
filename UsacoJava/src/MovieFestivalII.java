import java.io.*;
import java.util.*;

/*
CSES Problem Set
Movie Festival II
USACO Silver Guide - Greedy + Sorting - Normal
 */
public class MovieFestivalII {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int K;
    static Movie[] movies = new Movie[N];
    static Queue<Movie> active = new LinkedList<Movie>();
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        movies = new Movie[N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            movies[i] = new Movie(a,b);
        }
        //logic
        Arrays.sort(movies, (a,b)->{
            if (a.end==b.end) return a.start-b.start;
            return a.end-b.end;
        });
        active.add(movies[0]);
        int ans = 1;
        for (int i=1;i<N;i++) {
            Movie watching = active.peek();
            if (movies[i].start < watching.end) {
                if (active.size() < K) {
                    active.add(movies[i]);
                    ans++;
                }
                else {

                }
            }
            else {
                active.poll();
                active.add(movies[i]);
                ans++;
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    private static class Movie {
        int start;
        int end;
        public Movie(int a, int b){
            start=a;
            end=b;
        }
    }
}
