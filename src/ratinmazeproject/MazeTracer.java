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
public class MazeTracer {
     private int[][] path = new int[10000][2];
    private int ctr;
    int c = 0;
    private int flag = 0;

    public MazeTracer() {
    }

    Boolean checkPos(int x, int i2, int j2, Maze[][] m) {
        if (x == 0) {
            if (m[i2][j2 + 1].getState() == 0 || m[i2][j2 + 1].getState() == 3) {
                return true;
            }
        } else if (x == 1) {
            if (m[i2 + 1][j2].getState() == 0 || m[i2 + 1][j2].getState() == 3) {
                return true;
            }
        } else if (x == 2) {
            if (m[i2][j2 - 1].getState() == 0 || m[i2][j2 - 1].getState() == 3) {
                return true;
            }
        } else if (x == 3 && (m[i2 - 1][j2].getState() == 0 || m[i2 - 1][j2].getState() == 3)) {
            return true;
        }

        return false;
    }

    int getDirection(String s, int i2, int j2, Maze[][] m) {
        int x = 0;
        boolean f = false;

        for(int k = 0; k < s.length(); ++k) {
            char ch = s.charAt(k);
            if (ch == '0' || ch == '3') {
                x = k;
                if (this.checkPos(k, i2, j2, m)) {
                    f = true;
                    break;
                }
            }
        }

        if (f) {
            if (x == 0) {
                return 0;
            } else if (x == 1) {
                return 1;
            } else {
                return x == 2 ? 2 : 3;
            }
        } else {
            return 4;
        }
    }

    Boolean pathGenerator(int i1, int j1, Maze[][] m) {
        this.path[this.c][0] = i1;
        this.path[this.c++][1] = j1;
        if (m[i1][j1].getState() == 3) {
            this.flag = 1;
            this.ctr = this.c;
            return true;
        } else {
            m[i1][j1].changeState(2);
            String s = m[i1][j1].getsurroundCell();
            int dir = this.getDirection(s, i1, j1, m);
            if (dir == 0) {
                if (this.pathGenerator(i1, j1 + 1, m)) {
                    return true;
                }

                this.pathGenerator(i1, j1, m);
            } else if (dir == 1) {
                if (this.pathGenerator(i1 + 1, j1, m)) {
                    return true;
                }

                this.pathGenerator(i1, j1, m);
            } else if (dir == 2) {
                if (this.pathGenerator(i1, j1 - 1, m)) {
                    return true;
                }

                this.pathGenerator(i1, j1, m);
            } else if (dir == 3) {
                if (this.pathGenerator(i1 - 1, j1, m)) {
                    return true;
                }

                this.pathGenerator(i1, j1, m);
            }

            return false;
        }
    }

    int getpathValue(int x, int y) {
        return this.path[x][y];
    }

    int pathSize() {
        if (this.flag == 0) {
            this.ctr = this.c;
        }

        return this.ctr;
    }
    
}
