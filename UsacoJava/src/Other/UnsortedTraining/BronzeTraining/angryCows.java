package Other.UnsortedTraining.BronzeTraining;import java.io.*;
import java.util.*;
public class angryCows {
    public static void main(String[] args) throws IOException {
        // initialize file I/O
        BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));

        // read in N
        int n = Integer.parseInt(br.readLine());
        // read in the locations into an array
        int[] locations = new int[n];
        for(int i = 0; i < n; i++) {
            locations[i] = Integer.parseInt(br.readLine());
        }

        // sort the locations for convenience
        Arrays.sort(locations);

        int answer = 1;

        // loop over every possible hay bale, simulating the explosion starting from that hay bale
        for(int start = 0; start < n; start++) {
            // figure out the farthest hay bale to the left that explodes
            int leftMostExplosion = getExplosionIndex(locations, start, true);

            // figure out the farthest hay bale to the right that explodes
            int rightMostExplosion = getExplosionIndex(locations, start, false);

            // count the number of hay bales that explode
            int numExploded = rightMostExplosion - leftMostExplosion + 1;

            // if the number that explodes, and update our answer if we explode more hay bales
            if(numExploded > answer) {
                answer = numExploded;
            }
        }

        // print the answer
        pw.println(answer);

        // close I/O
        pw.close();
    }
    public static int getExplosionIndex(int[] location, int startIndex, boolean goLeft) {
        int lastExplosionIndex = startIndex;
        int currentRadius = 1;
        // if the last hay bale that explodes is not either the left most haybale or the right hay bale, continue simulating explosions.
        while(lastExplosionIndex > 0 && lastExplosionIndex < location.length-1) {

            // lastExplosionIndex stores the index of the hay bale that we are currently simulating an explosion for
            int direction = 0;

            // figure out which direction to inspect hay bales
            if(goLeft) {
                direction = -1;
            }
            else {
                direction = 1;
            }

            /*
             * check if the next closest hay bale is within the range of the explosion
             * the next hay bale to check is at index (nextIndex + direction)
             * to avoid an index out of bounds exception on the array, we must first check that it is betwen 0 and location.length-1.
             * After that, we check if the distance between the two points is less than or equal to the explosion radius.
             */
            int nextIndex = lastExplosionIndex;
            while(nextIndex + direction >= 0 && nextIndex + direction < location.length && Math.abs(location[nextIndex + direction] - location[lastExplosionIndex]) <= currentRadius) {
                nextIndex += direction;
            }

            /*
             * At the end of the while loop, "nextIndex" stores the farthest hay bale that exploded due to the current one exploding.
             * If no other hay bale explodes because of the current explosion, we break out of the while loop
             */

            if(nextIndex == lastExplosionIndex) {
                break;
            }

            /*
             * Otherwise, we now have a new hay bale to simulate an explosion for.
             */

            lastExplosionIndex = nextIndex;
            currentRadius++;
        }
        return lastExplosionIndex;
    }

}