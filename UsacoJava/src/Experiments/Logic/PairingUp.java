package Experiments.Logic;

import java.io.*;
import java.util.*;

public class PairingUp {
    public static void main(String[] args) throws IOException {
        //* parse
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i=0;i<N;i++) A[i]=Integer.parseInt(st.nextToken());
        int K = 30;

        //* method1: Multisets
        Multiset ms = new Multiset();
        for (int i=0;i<N;i++) ms.add(A[i]);
        ArrayList<Integer> up = new ArrayList<>();
        while (ms.ms.size()!=0){
            Integer lo = ms.ms.firstKey();
            ms.rem(lo);

            Integer match = ms.ms.ceilingKey(K-lo);

            if (lo>=K || match==null){
                up.add(lo);
                continue;
            }
            ms.rem(match);
        }
        System.out.println("up1: "+up);

        //* method2: BS
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i<N;i++)list.add(A[i]);
        Collections.sort(list);
        ArrayList<Integer> up2 = new ArrayList<>();
        for (int i=0;i<N;i++){
            if (canRemoveIndex(i,list,K)) up2.add(list.get(i));
        }
        System.out.println("up2: "+up2);
    }
    static boolean canRemoveIndex(int index, ArrayList<Integer> list, int K){
        int l = 0; int r = list.size()-1;
        while (l<r){
            if (list.get(l)+list.get(r)<K) return false;
            l++;
            r--;
            if (l==index) l++;
            if (r==index) r--;
        }
        if (l==r) return list.get(l)>=K;
        return true;
    }
    private static class Multiset {
        TreeMap<Integer, Integer> ms = new TreeMap<>();
        public void add(int x){
            if (!ms.containsKey(x)) ms.put(x,1);
            else ms.put(x,ms.get(x)+1);
        }
        public void rem(int x){
            if (ms.get(x)==1) ms.remove(x);
            else ms.put(x,ms.get(x)-1);
        }
    }
}
/*
50
734	940	859	864	528	25	222	81	624	611	379	813	974	979	91	831	875	375	601	568	235	582	728	242	432	714	31	500	928	524	321	922	651	773	85	694	756	535	695	520	524	983	283	153	70	730	306	441	419	382

6
1 12 13 50 60 70
*/