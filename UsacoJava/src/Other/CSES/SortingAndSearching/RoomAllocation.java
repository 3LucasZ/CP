package Other.USACOGuide.UsacoGuideSilver.MoreOpsOnSets;

import java.io.*;
import java.util.*;

/*
CSES Problem Set
Room Allocation
USACO Silver Guide - More ops on Sets - Normal
Concepts: PriorityQueue - adding, removing/peeking the lowest priority
Custom comparator for PriorityQueue
 */
public class RoomAllocation {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static Customer[] customers;
    static PriorityQueue<Room> rooms = new PriorityQueue<>((a,b)->a.end-b.end);
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        customers = new Customer[N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            customers[i] = new Customer(i, u, v);
        }
        Arrays.sort(customers, (a,b)->a.start-b.start);
        int roomID = 0;
        int ans[] = new int[N];
        for (int i=0;i<N;i++) {
            Room fastestRoom = rooms.peek();
            if (fastestRoom == null || fastestRoom.end >= customers[i].start){
                roomID++;
                ans[customers[i].id] = roomID;
                rooms.add(new Room(customers[i].end, roomID));
            }
            else {
                ans[customers[i].id] = fastestRoom.number;
                rooms.poll();
                rooms.add(new Room(customers[i].end, fastestRoom.number));
            }
        }
        out.println(roomID);
        for (int i=0;i<N;i++) {
            out.print(ans[i] + " ");
        }
        out.close();
    }
    private static class Customer {
        int start;
        int end;
        int id;
        public Customer(int i, int s, int e){
            id=i;
            start=s;
            end=e;
        }
        public String toString(){
            return "["+start+"--"+end+"]";
        }
    }
    private static class Room {
        int end;
        int number;
        public Room(int e, int n) {
            end=e;
            number=n;
        }
        public String toString(){
            return "["+number+": "+end+"]";
        }
    }
}
/*
10
6 6
5 5
6 6
10 10
7 7
10 10
6 6
8 8
3 3
9 9
 */
