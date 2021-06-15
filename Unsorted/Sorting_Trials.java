import java.util.*;

public class Sorting_Trials {
    public static void main(String[] args){
        List<List<Integer>> entries = new ArrayList<>();
        entries.add(Arrays.asList(0,1,2));
        entries.add(Arrays.asList(2,1,2));
        entries.add(Arrays.asList(1,1,2));
        entries.sort((l1,l2) -> l1.get(0).compareTo(l2.get(0)));
        System.out.println(entries);
    }

}
