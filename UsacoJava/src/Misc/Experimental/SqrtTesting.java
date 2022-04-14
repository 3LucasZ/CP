package Misc.Experimental;

public class SqrtTesting {
    public static void main(String[] args){
        test(1000000);
        test(2000000);
    }

    public static void test(int i){
        System.out.println(Math.sqrt(i));
        System.out.println((int)Math.sqrt(i)+1);
        System.out.println();
    }
}
