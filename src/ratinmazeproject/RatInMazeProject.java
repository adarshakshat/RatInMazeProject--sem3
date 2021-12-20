/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratinmazeproject;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
/**
 *
 * @author adarshakshat
 */
public class RatInMazeProject {
    
    public RatInMazeProject(){
        
    }

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        MazeMaker dataMgr = new MazeMaker();
        MazeTracer logicMgr = new MazeTracer();
        DisplayManager dispMgr = new DisplayManager();
        dataMgr.MakeMaze();
        int size = dataMgr.getSize();
        System.out.println("Maze Size - " + size);
        Maze[][] myMaze = new Maze[size][size];

        int i;
        int pathSize;
        for(i = 0; i < size; ++i) {
            for(pathSize = 0; pathSize < size; ++pathSize) {
                myMaze[i][pathSize] = new Maze();
            }
        }

        for(i = 0; i < size; ++i) {
            for(pathSize = 0; pathSize < size; ++pathSize) {
                myMaze[i][pathSize].storesurroundCell(dataMgr.getValue(i, pathSize));
                myMaze[i][pathSize].changeState(0);
            }
        }

        System.out.println("Maze-Cell Showing its surrounding");

        for(i = 0; i < size; ++i) {
            for(pathSize = 0; pathSize < size; ++pathSize) {
                System.out.print(myMaze[i][pathSize].getsurroundCell() + " ");
            }

            System.out.println();
        }

        myMaze[dataMgr.getX_end()][dataMgr.getY_end()].changeState(3);
        System.out.println("Maze-Cell Showing its initial state");

        for(i = 0; i < size; ++i) {
            for(pathSize = 0; pathSize < size; ++pathSize) {
                System.out.print(myMaze[i][pathSize].getState() + " ");
            }

            System.out.println();
        }

        if (logicMgr.pathGenerator(dataMgr.getX_start(), dataMgr.getY_start(), myMaze)) {
        }

        int[][] path = new int[10000][2];
        pathSize = logicMgr.pathSize();
        System.out.println("Maze-Cell Showing its final state");

        //int i;
        int j;
        for(i = 0; i < size; ++i) {
            for(j = 0; j < size; ++j) {
                System.out.print(myMaze[i][j].getState() + " ");
            }

            System.out.println();
        }

        System.out.println("The Path - ");

        for(i = 0; i < pathSize; ++i) {
            path[i][0] = logicMgr.getpathValue(i, 0);
            path[i][1] = logicMgr.getpathValue(i, 1);
        }

        for(i = 0; i < pathSize; ++i) {
            dispMgr.sendpathValue(path[i][0], path[i][1], i);
        }

        dispMgr.sendpathSize(pathSize);
        dispMgr.getSize(size);
        dispMgr.initialize(dataMgr.getX_end(), dataMgr.getY_end());

        for(i = 0; i < size; ++i) {
            for(j = 0; j < size; ++j) {
                String s2 = myMaze[i][j].getsurroundCell();
                dispMgr.creatingDecimalMaze(i, j, s2);
            }
        }

        JFrame frame = new JFrame("UI Screen");
        frame.setDefaultCloseOperation(3);
        frame.add(dispMgr);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setLocation(100, 100);
        frame.setBackground(Color.gray);
        frame.setForeground(Color.black);
        Font f = new Font("Calibri", 1, 45);
        frame.setFont(f);
        dispMgr.drawpath();
    }
    
    
}
