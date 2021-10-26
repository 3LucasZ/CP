package Unsorted;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: Ad_hoc.friday
*/
import java.io.*;
import java.util.*;

public class friday {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("Ad_hoc.friday.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Ad_hoc.friday.out")));

        dayCalculator dc = new dayCalculator();
        int N = Integer.parseInt(f.readLine());
        //Day 1 1900 is Monday
        int dayOfWeek = 2;

        //Array List starts on Saturday, ends on Friday
        ArrayList<Integer> weekCount = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++) weekCount.add(0);

        while (dc.get_y() < 1900 + N) {

            if (dc.get_md() == 13) {
                weekCount.set(dayOfWeek, weekCount.get(dayOfWeek) + 1);
            }
            //out.println(dc.get_md());
            dc.next_day();
            dayOfWeek = (dayOfWeek + 1) % 7;
        }


        String answer = "";
        for (int element:weekCount) {
            answer = answer + element;
            answer += " ";
        }
        answer = answer.substring(0,answer.length()-1);
        out.println(answer);
        out.close();

    }
}
class dayCalculator {
    int m;
    int md;
    int d;
    int y;
    Integer[] monthDays;
    dayCalculator() {
        m = 0;
        md = 1;
        d = 1;
        y = 1900;
        monthDays = new Integer[]{31,28,31,30,31,30,31,31,30,31,30,31};
    }

    public void next_day() {
        if (is_leap_year()){
            monthDays[1] = 29;
        }
        else {
            monthDays[1] = 28;
        }

        if (md == monthDays[m]){
            if (m == 11) {
                m = 0;
                y += 1;
            }
            else {
                m += 1;
            }
            md = 1;
        }
        else {
            md += 1;
        }
        d += 1;

    }
    public int get_d(){
        return d;
    }
    public int get_md(){
        return md;
    }
    public int get_y(){
        return y;
    }
    public boolean is_leap_year(){
        int year = y;
        if (((( (year % 400) == 0) && (year) % 100 == 0)) || ((((year) % 4 == 0)) && (year) % 100 != 0)) {
            return true;
        }
        return false;
    }
}
