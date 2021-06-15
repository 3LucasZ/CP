package Simulation_Problems;
import java.io.*;
import java.lang.*;
import java.util.*;

class cow {
    String dir;
    public int start_x;
    public int start_y;
    boolean state = false;
    int grass_eaten = 0;
    int num;
    int meets = 0;


    cow(String d,int x,int y,int n) {
        dir = d;
        start_x = x;
        start_y = y;
        num = n;

    }
    public void find_east_intersection(ArrayList<cow> cows) {
        for (cow c : cows) {
            if (this.state == false && c.state == false && c.start_x < this.start_x && c.start_y > this.start_y) {
                if (this.start_x - c.start_x < c.start_y - this.start_y) { // if cow hits east cow
                    this.grass_eaten = c.start_y - this.start_y;
                    this.state = true;
                }
                else if (this.start_x - c.start_x > c.start_y - this.start_y) { // if east cow hits cow
                    c.grass_eaten = this.start_x - c.start_x - meets/2;
                    c.state = true;
                }
                else {
                    meets += 1;
                }
            }
        }
    }
    @Override
    public String toString() {
        return "cow " + num + ": start x: " + start_x + " start y: " + start_y + " ate " + grass_eaten;
    }

}
public class Stuck_in_a_Rut {
    //set up
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int numcows = Integer.parseInt(f.readLine());
        ArrayList<cow> ncowAL = new ArrayList<>();
        ArrayList<cow> ecowAL = new ArrayList<>();

        //set up ecowAL and ncowAL
        for (int i=0;i<numcows;i++) {
            StringTokenizer inputcow = new StringTokenizer(f.readLine());
            String dir = inputcow.nextToken();
            if (dir.equals("E")) {
                ecowAL.add(new cow(dir,Integer.parseInt(inputcow.nextToken()),Integer.parseInt(inputcow.nextToken()),i));
            }
            else {
                ncowAL.add(new cow(dir,Integer.parseInt(inputcow.nextToken()),Integer.parseInt(inputcow.nextToken()),i));
            }
        }
        //sort ncows by x and ecows by y
        Collections.sort(ncowAL, new Sortby_x());
        Collections.sort(ecowAL, new Sortby_y());
        for (cow c : ncowAL) {
            c.find_east_intersection(ecowAL);
        }
        ncowAL.addAll(ecowAL);
        Collections.sort(ncowAL, new Sortby_order());
        for (cow c: ncowAL) {
            if (c.state == false) {
                out.println("Infinity");
            }
            else {
                out.println(c.grass_eaten);
            }

            //out.println(c);
        }

        out.close();
    }
}
class Sortby_x implements Comparator<cow>
{
    public int compare(cow a, cow b)
    {
        return a.start_x - b.start_x;
    }
}
class Sortby_y implements Comparator<cow>
{
    public int compare(cow a, cow b)
    {
        return a.start_y - b.start_y;
    }
}
class Sortby_order implements Comparator<cow>
{
    public int compare(cow a, cow b)
    {
        return a.num - b.num;
    }
}
