package Experiments;

public class StringBuilderTesting {
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder("Computer Science Rocks");
        sb.delete(0,2);
        System.out.println(sb);
    }
}
