package UnsortedTraining.BronzeTraining;
import java.io.*;
import java.util.*;

public class hoofball {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hoofball.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hoofball.out")));

        int n = Integer.parseInt(br.readLine());
        if(n==1){
            out.println(1);
        }
        else {
            int[] cowDistances = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                cowDistances[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(cowDistances);
            //0 = LEFT
            //1 = RIGHT
            int[] passDirections = new int[n];
            for (int i=0;i<n;i++){
                int dir = 0;
                if(i==0){
                    dir = 1;
                }
                else if(i==n-1){
                    dir = 0;
                }
                else{
                    if(cowDistances[i] - cowDistances[i-1] <= cowDistances[i+1] - cowDistances[i]){
                        dir = 0;
                    }
                    else{
                        dir = 1;
                    }
                }
                passDirections[i] = dir;
            }
            //out.println(Arrays.toString(passDirections));
            ArrayList<Integer> passChainDirections = new ArrayList<>();
            ArrayList<Integer> passChainLength = new ArrayList<>();

            int length = 1;
            int direction = passDirections[0];
            for(int i=1;i<n;i++){

                if(passDirections[i] == passDirections[i-1]){
                    length += 1;
                }
                else{
                    passChainDirections.add(direction);
                    passChainLength.add(length);
                    length = 1;
                    direction = 1 - direction;
                }
            }
            passChainDirections.add(direction);
            passChainLength.add(length);

            /*out.println(Arrays.toString(passDirections));
            out.println(passChainDirections);
            out.println(passChainLength);
             */

            if(passChainDirections.size() == 1){
                out.println(1);
            }
            else {
                int counter = 0;

                for(int i=0;i<=passChainDirections.size()-2;i+=2){
                    if(passChainLength.get(i) == 1 || passChainLength.get(i+1) == 1){
                        counter += 1;
                    }
                    else{
                        counter += 2;
                    }
                }

                out.println(counter);
            }
        }
        out.close();
    }
}
