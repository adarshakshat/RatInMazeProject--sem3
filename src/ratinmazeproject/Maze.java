/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratinmazeproject;

/**
 *
 * @author adarshakshat
 */
public class Maze {
    private String s;
    private int state;

    public Maze() {
    }

    public void storesurroundCell(String a) {
        this.s = a;
    }

    public String getsurroundCell() {
        return this.s;
    }

    public void changeState(int a) {
        this.state = a;
    }

    public int getState() {
        return this.state;
    }
}
