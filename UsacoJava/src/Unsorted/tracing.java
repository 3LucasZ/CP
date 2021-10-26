package Unsorted;import java.io.*;
import java.util.*;

public class tracing {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("tracing.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tracing.out")));

        StringTokenizer a = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(a.nextToken());
        int T = Integer.parseInt(a.nextToken());

        List<String> finalInfectionsStringList = Arrays.asList(f.readLine().split(""));
        int finalInfections[] = new int[finalInfectionsStringList.size()];
        for(int i=0;i<finalInfectionsStringList.size();i++){
            finalInfections[i]=Integer.parseInt(finalInfectionsStringList.get(i));
        }

        List firstPatients = new ArrayList();

        int cowInfections[] = new int[N];
        int cowShakes[] = new int[N];

        List<List<Integer>> entries = new ArrayList();

        for (int i=0;i<T;i++){
            StringTokenizer S = new StringTokenizer(f.readLine());
            List entry = Arrays.asList(Integer.parseInt(S.nextToken()),Integer.parseInt(S.nextToken()),Integer.parseInt(S.nextToken()));
            entries.add(entry);
        }
        entries.sort(Comparator.comparing(l -> l.get(0)));
        //out.println(entries);
        //out.println(Arrays.toString(finalInfections));
        int minK = 251;
        int maxK = 0;

        for (int K=T;K>=0;K--) {
            for (int C=0;C<N;C++){
                Arrays.fill(cowShakes, K);
                Arrays.fill(cowInfections, 0);

                cowInfections[C] = 1;
                for (List<Integer> e : entries){
                    //out.println("Infections " + Arrays.toString(cowInfections));
                    //out.println("Shakes " + Arrays.toString(cowShakes));
                    int cow1 = e.get(1)-1;
                    int cow2 = e.get(2)-1;
                    if(cowInfections[cow1]==1) cowShakes[cow1] --;
                    if(cowInfections[cow2]==1) cowShakes[cow2] --;
                    if(cowInfections[cow1]==1 && cowShakes[cow1] >= 0) cowInfections[cow2] = 1;
                    if(cowInfections[cow2]==1 && cowShakes[cow2] >= 0) cowInfections[cow1] = 1;
                }
                if(Arrays.equals(cowInfections,finalInfections)){
                    if(!firstPatients.contains(C+1)){
                        firstPatients.add(C+1);
                    }
                    if (K < minK){
                        minK = K;
                    }
                    if (K > maxK){
                        maxK = K;
                    }
                }
            }
        }
        int count = firstPatients.size();

        if(minK >= T){
            out.println(count + " Infinity Infinity");
        }
        else if(maxK >= T){
            out.println(count + " " + minK + " Infinity");
        }
        else{
            out.println(count + " " + minK + " " + maxK);
        }
        out.close();
    }
}