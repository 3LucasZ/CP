package Experimental;

import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args){
        TreeSet<Pie> pies = new TreeSet<>((a,b)-> {
            if (a.B == b.B) {
                if (a.E == b.E) return a.id-b.id;
                return a.E - b.E;
            }
            return a.B-b.B;
        });
        pies.add(new Pie(1,3,4));
        pies.add(new Pie(2,3,4));
        pies.add(new Pie(3, 3,5));

        System.out.println(pies);
        System.out.println(pies.higher(new Pie(0,0,0)));
    }

    private static class Pie {
        int id;
        int B;
        int E;
        public Pie(int id, int B, int E){
            this.id=id;
            this.B=B;
            this.E=E;
        }
        public String toString(){
            return "["+id+": "+B+", "+E+"]";
        }
    }
}
