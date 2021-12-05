import java.io.*;
import java.util.*;

public class Testing {
    public static final int R_CHANGE[] = {0, 1, 0, -1};
    public static final int C_CHANGE[] = {1, 0, -1, 0};

    private static boolean[][] visited;
    private static char[][] building;
    private static int rowNum;
    private static int colNum;

    public static void floodfill(int row, int col) {
        // Note: you can also use a queue and pop from the front for a BFS-based approach
        Stack<Pos> frontier = new Stack<>();
        frontier.push(new Pos(row, col));
        while(!frontier.isEmpty()){
            Pos curr = frontier.pop();
            row = curr.row;
            col = curr.col;

            if (row < 0 || row >= rowNum || col < 0 || col >= colNum
                    || building[row][col] == '#' || visited[row][col])
                continue;

            visited[row][col] = true;
            for (int i = 0; i < 4; i++) {
                frontier.add(new Pos(row + R_CHANGE[i], col + C_CHANGE[i]));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer dims = new StringTokenizer(read.readLine());
        rowNum = Integer.parseInt(dims.nextToken());
        colNum = Integer.parseInt(dims.nextToken());

        building = new char[rowNum][colNum];
        for (int r = 0; r < rowNum; r++){
            String row = read.readLine();
            for (int j = 0; j < colNum; j++){
                building[r][j] = row.charAt(j);
            }
        }

        visited = new boolean[rowNum][colNum];
        int roomNum = 0;
        for (int i = 0; i < rowNum; i++){
            for (int j = 0; j < colNum; j++){
                if (building[i][j] == '.' && !visited[i][j]) {
                    floodfill(i, j);
                    roomNum++;
                }
            }
        }
        System.out.println(roomNum);
    }
}

class Pos {
    int row;
    int col;
    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }
}