/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratinmazeproject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author adarshakshat
 */
public class MazeMaker {
    
    
    private String[][] m = new String[100][100];
    private int size = 0;
    private int X_start;
    private int Y_start;
    private int X_end;
    private int Y_end;

    public MazeMaker() {
    }

    public void MakeMaze() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the File: (Give the Extension also!)");
            String fileName = sc.nextLine();
            File file = new File(fileName);
            int[][] ar = new int[1000][1000];

            String st;
            for(BufferedReader br = new BufferedReader(new FileReader(file)); (st = br.readLine()) != null; ++this.size) {
                this.MazeCreator(ar, st, this.size);
            }

            this.createMaze(ar);
        } catch (FileNotFoundException var8) {
            System.out.println("Error finding input file " + var8.getMessage());
            System.exit(0);
        } catch (IOException var9) {
            System.out.println("IO Error! " + var9.getMessage());
            var9.printStackTrace();
            System.exit(0);
        }

    }

    public void MazeCreator(int[][] ar, String s, int size) {
        boolean j = false;
        int k = 0;

        for(int m = 0; m < s.length(); m += 2) {
            char ch = s.charAt(m);
            if (ch == '+') {
                ar[size][k++] = 1;
            } else if (ch == '-') {
                ar[size][k++] = 1;
            } else if (ch == ' ') {
                ar[size][k++] = 0;
            } else if (ch == '|') {
                ar[size][k++] = 1;
            } else if (ch == 'S') {
                ar[size][k++] = 0;
                this.X_start = (size - 1) / 2;
                this.Y_start = (k - 2) / 2;
            } else if (ch == 'E') {
                ar[size][k++] = 0;
                this.X_end = (size - 1) / 2;
                this.Y_end = (k - 2) / 2;
            }
        }

    }

    public void createMaze(int[][] ar) {
        System.out.println("The Raw Matrix");

        int i;
        int j;
        for(i = 0; i < this.size - 2; ++i) {
            for(j = 0; j < this.size - 2; ++j) {
                System.out.print(ar[i][j] + " ");
            }

            System.out.println();
        }

        for(i = 0; i < this.size; ++i) {
            for(j = 0; j < this.size; ++j) {
                this.m[i][j] = this.checkNeighbourhood(i * 2 + 1, j * 2 + 1, ar);
            }
        }

    }

    public String checkNeighbourhood(int p, int q, int[][] ar) {
        String s1 = "";
        s1 = s1 + String.valueOf(ar[p][q + 1]);
        s1 = s1 + String.valueOf(ar[p + 1][q]);
        s1 = s1 + String.valueOf(ar[p][q - 1]);
        s1 = s1 + String.valueOf(ar[p - 1][q]);
        return s1;
    }

    String getValue(int i, int j) {
        return this.m[i][j];
    }

    int getSize() {
        this.size = (this.size - 3) / 2;
        return this.size;
    }

    int getX_start() {
        return this.X_start;
    }

    int getY_start() {
        return this.Y_start;
    }

    int getX_end() {
        return this.X_end;
    }

    int getY_end() {
        return this.Y_end;
    }
    
}
