package Helpers.Logging;

public class print2d {
    private static class Printer {
        public static void print(int[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    String str = "" + arr[r][c];
                    while (str.length() < 5) str += " ";
                    System.out.print(str);
                }
                System.out.println();
            }
            System.out.println();
        }
        public static void print(boolean[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    System.out.print(arr[r][c] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        public static void print(long[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    String str = "" + arr[r][c];
                    while (str.length() < 5) str += " ";
                    System.out.print(str);
                }
                System.out.println();
            }
            System.out.println();
        }
        public static void print(double[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    String str = "" + arr[r][c];
                    while (str.length() < 5) str += " ";
                    System.out.print(str);
                }
                System.out.println();
            }
            System.out.println();
        }
        public static void print(char[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    System.out.print(arr[r][c]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        public static void print(String[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    String str = "" + arr[r][c];
                    while (str.length() < 5) str += " ";
                    System.out.print(str);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
