/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratinmazeproject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
/**
 *
 * @author adarshakshat
 */
public class DisplayManager extends javax.swing.JPanel {

    /**
     * Creates new form DisplayManager
     */
    int c = 0;
    private int steps;
    private int[][] pos = new int[10000][2];
    private int size;
    private int[][] maze1 = new int[10000][10000];
    private static final long serialVersionUID = 1L;
    final int margin = 50;
    final int cellSize = 50;
    private int i1;
    private int j1;
    private int nCols;
    private int nRows;
    private int pRows;
    private int X_end;
    private int Y_end;
    int offset = 68;

    public DisplayManager() {
    }

    void sendpathValue(int x, int y, int i) {
        this.pos[i][0] = x;
        this.pos[i][1] = y;
        System.out.println(this.pos[i][0] + ", " + this.pos[i][1]);
    }

    void sendpathSize(int x) {
        this.steps = x;
        System.out.println("No. of Steps - " + x);
    }

    void getSize(int x) {
        this.size = x;
    }

    void creatingDecimalMaze(int x, int y, String s) {
        this.maze1[x][y] = Integer.parseInt(s, 2);
    }

    void initialize(int x, int y) {
        this.nCols = this.size;
        this.nRows = this.size;
        this.pRows = this.steps;
        this.X_end = x;
        this.Y_end = y;
    }

    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g1 = (Graphics2D)gg;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g1.setStroke(new BasicStroke(5.0F));
        g1.setColor(Color.black);

        int r;
        int c;
        for(r = 0; r < this.nRows; ++r) {
            for(c = 0; c < this.nCols; ++c) {
                int x = 50 + c * 50;
                int y = 50 + r * 50;
                switch(this.maze1[r][c]) {
                case 0:
                    g1.drawLine(x, y, x, y);
                    break;
                case 1:
                    g1.drawLine(x, y, x + 50, y);
                    break;
                case 2:
                    g1.drawLine(x, y, x, y + 50);
                    break;
                case 3:
                    g1.drawLine(x, y, x, y + 50);
                    g1.drawLine(x, y, x + 50, y);
                    break;
                case 4:
                    g1.drawLine(x, y + 50, x + 50, y + 50);
                    break;
                case 5:
                    g1.drawLine(x, y, x + 50, y);
                    g1.drawLine(x, y + 50, x + 50, y + 50);
                    break;
                case 6:
                    g1.drawLine(x, y + 50, x + 50, y + 50);
                    g1.drawLine(x, y, x, y + 50);
                    break;
                case 7:
                    g1.drawLine(x, y, x, y + 50);
                    g1.drawLine(x, y, x + 50, y);
                    g1.drawLine(x, y + 50, x + 50, y + 50);
                    break;
                case 8:
                    g1.drawLine(x + 50, y, x + 50, y + 50);
                    break;
                case 9:
                    g1.drawLine(x, y, x + 50, y);
                    g1.drawLine(x + 50, y, x + 50, y + 50);
                    break;
                case 10:
                    g1.drawLine(x, y, x, y + 50);
                    g1.drawLine(x + 50, y, x + 50, y + 50);
                    break;
                case 11:
                    g1.drawLine(x, y, x, y + 50);
                    g1.drawLine(x, y, x + 50, y);
                    g1.drawLine(x + 50, y, x + 50, y + 50);
                    break;
                case 12:
                    g1.drawLine(x, y + 50, x + 50, y + 50);
                    g1.drawLine(x + 50, y, x + 50, y + 50);
                    break;
                case 13:
                    g1.drawLine(x, y, x + 50, y);
                    g1.drawLine(x, y + 50, x + 50, y + 50);
                    g1.drawLine(x + 50, y, x + 50, y + 50);
                    break;
                case 14:
                    g1.drawLine(x, y, x, y + 50);
                    g1.drawLine(x, y + 50, x + 50, y + 50);
                    g1.drawLine(x + 50, y, x + 50, y + 50);
                    break;
                case 15:
                    g1.drawLine(x, y, x + 50, y);
                    g1.drawLine(x, y, x, y + 50);
                    g1.drawLine(x, y + 50, x + 50, y + 50);
                    g1.drawLine(x + 50, y, x + 50, y + 50);
                }
            }
        }

        g1.setColor(Color.green);
        r = this.offset + this.Y_end * 50;
        c = this.offset + this.X_end * 50;
        g1.fillOval(r, c, 15, 15);
        g1.setColor(Color.BLACK);
        g1.drawString("No of Steps : ", 25, 25);
        g1.drawString(String.valueOf(this.c), 105, 25);
        g1.setColor(Color.RED);
        g1.fillOval(this.offset + this.j1 * 50, this.offset + this.i1 * 50, 15, 15);
    }

    void sound(int x) {
        if (this.pos[x - 1][0] == this.pos[x + 1][0] && this.pos[x - 1][1] == this.pos[x + 1][1]) {
            File Clap = new File("hit.wav");
            this.PlaySound(Clap);
        }

    }

    void PlaySound(File Sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
        } catch (Exception var3) {
        }

    }

    void drawpath() {
        for(int i = 0; i < this.pRows; ++i) {
            this.i1 = this.pos[i][0];
            this.j1 = this.pos[i][1];
            if (i != 0) {
                this.sound(i);
            }

            try {
                Thread.sleep(500L);
            } catch (InterruptedException var3) {
            }

            ++this.c;
            this.repaint();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
