package Misc.Experimental;

import java.util.Arrays;

public class ReferenceType {
    public static void main(String[] args){
        int N = 10;
        Number[] numbers = new Number[N];

        for (int i=0;i<N;i++) numbers[i] = new Number(i);

        System.out.println(Arrays.toString(numbers));

        Number target;
        for (int i=0;i<N;i++) target = numbers[i];

        System.out.println(Arrays.toString(numbers));
    }
    private static class Number {
        int x;
        public Number(int x){
            this.x=x;
        }
        public String toString(){
            return ""+x;
        }
    }
}
