package Other.USACOGuide.UsacoGuideGold.BFS;
import java.util.*;
public class Tutorial {
    public static void main(String[] args){
        //java queue FIFO
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(1);
        q.add(35);
        q.add(5);
        System.out.println(q.peek());
        System.out.println(q);

        //java dequeue : stack + queue
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addLast(7);
        deque.removeFirst();
        deque.addFirst(1);
        deque.removeLast();
    }
}
