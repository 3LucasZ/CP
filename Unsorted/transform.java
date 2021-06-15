package Complete_Search;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: Complete_Search.transform
*/
import java.io.*;
import java.util.*;
public class transform {
    public static void main(String[] args) throws IOException {
        //storing information and input/output
        BufferedReader f = new BufferedReader(new FileReader("Complete_Search.transform.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Complete_Search.transform.out")));
        int gridSize = Integer.parseInt(f.readLine());
        ArrayList<ArrayList<String>> inputGrid =  new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> outputGrid = new ArrayList<ArrayList<String>>();
        for (int i=0;i<gridSize;i++) {
            ArrayList<String> group = new ArrayList(Arrays.asList(f.readLine().split("")));
            inputGrid.add(group);

        }
        for (int i=0;i<gridSize;i++) {
            ArrayList<String> group = new ArrayList(Arrays.asList(f.readLine().split("")));
            outputGrid.add(group);
        }

        Grid firstGrid = new Grid(inputGrid,gridSize);
        Grid secondGrid = new Grid(outputGrid,gridSize);

        /*out.println(firstGrid.grid);
        firstGrid.right90Degrees();
        out.println(firstGrid.grid);
        if (gridSize > 3) {
            out.println(firstGrid.reflectOnY().right90Degrees().grid);
        }
         */
        out.println(firstGrid.findNumber(secondGrid));
        out.close();

    }
}
class Grid {
    public ArrayList<ArrayList<String>> grid;
    public int size;
    public Grid(ArrayList<ArrayList<String>> ArrayList, int size) {
        this.grid = ArrayList;
        this.size = size;
    }
    public Grid right90Degrees() {
        ArrayList<ArrayList<String>> temp =  new ArrayList<ArrayList<String>>();
        for (int col=0;col<=size-1;col++) {
            ArrayList<String> group = new ArrayList<String>();
            for (int row=size-1;row>=0;row--) {
                group.add(this.grid.get(row).get(col));
            }
            temp.add(group);
        }
        return new Grid(temp,this.size);
    }
    public Grid reflectOnY() {
        ArrayList<ArrayList<String>> temp =  new ArrayList<ArrayList<String>>();
        for (int row=0;row<size;row++) {
            ArrayList<String> group = new ArrayList<String>();
            for (int col=size-1;col>=0;col--) {
                group.add(this.grid.get(row).get(col));
            }
            temp.add(group);
        }
        return new Grid(temp,this.size);
    }
    public int findNumber(Grid outputGrid) {
        if (this.grid == outputGrid.grid) {
            return 6;
        }
        else if (this.right90Degrees().grid.equals(outputGrid.grid)) {
            return 1;
        }
        else if (this.right90Degrees().right90Degrees().grid.equals(outputGrid.grid)) {
            return 2;
        }
        else if (this.right90Degrees().right90Degrees().right90Degrees().grid.equals(outputGrid.grid)) {
            return 3;
        }
        else if (this.reflectOnY().grid.equals(outputGrid.grid)) {
            return 4;
        }
        else if (this.reflectOnY().right90Degrees().grid.equals(outputGrid.grid) || this.reflectOnY().right90Degrees().right90Degrees().grid.equals(outputGrid.grid) || this.reflectOnY().right90Degrees().right90Degrees().right90Degrees().grid.equals(outputGrid.grid)){
            return 5;
        }
        else {
            return 7;
        }

    }
}
