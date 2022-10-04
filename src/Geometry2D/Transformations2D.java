package Geometry2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.JFrame;
/**
 *
 * @author htrefftz
 */
public class Transformations2D
        extends JPanel
        implements KeyListener {

    public LinkedList<Line> linesToDraw;

    {
        linesToDraw = new LinkedList<>();
    }

    Point point0;
    Point point1;
    Point point2;
    Point point3;
    Point point4;

    Line line0;
    Line line1;
    Line line2;
    Line line3;
    Line line4;
    Line line5;

    int height = 800;
    int width = 800;
    protected Boolean hasDrawnOnce;

    public Transformations2D() {
        point0 = new Point(100, 100);
        point1 = new Point(200, 100);
        point2 = new Point(200,200);
        point3 = new Point(150,250);
        point4 = new Point(100,200);
        line0 = new Line(point0.x,point0.y,point1.x,point1.y);
        line1 = new Line(point1.x,point1.y,point2.x,point2.y);
        line2 = new Line(point2.x,point2.y,point3.x,point3.y);
        line3 = new Line(point3.x,point3.y,point4.x,point4.y);
        line4 = new Line(point4.x,point4.y,point0.x,point0.y);
        line5 = new Line(point2.x,point2.y,point4.x,point4.y);
        hasDrawnOnce = false;
        linesToDraw.add(0,line0);
        linesToDraw.add(1,line1);
        linesToDraw.add(2,line2);
        linesToDraw.add(3,line3);
        linesToDraw.add(4,line4);
        linesToDraw.add(5,line5);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        height = this.getHeight();
        width = this.getWidth();

        g.setColor(Color.BLACK);
        for (Line lineToDraw : linesToDraw) {
            System.out.println(lineToDraw.x1 + "" + lineToDraw.y1 + "" + lineToDraw.x2 + "" + lineToDraw.y2);
            g.drawLine(convertXToCartesian(lineToDraw.x1), convertYToCartesian(lineToDraw.y1), convertXToCartesian(lineToDraw.x2), convertYToCartesian(lineToDraw.y2));
        }
        myDrawAxes(g, -100, -100, 100, 100);

    }

    public int convertXToCartesian(int x1){
        return x1 + width / 2;
    }
    public int convertYToCartesian(int y1){
        return height / 2 - y1;
    }

    public int convertXToJava(int x1){
        return x1 - width / 2;
    }

    public int convertYToJava(int y1){
        return height / 2 - y1;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        repaint();
        System.out.println("Key Released:" + e.getKeyCode());
        line0.x2 = 500;
    }
    private void myDrawAxes(Graphics g, int x1, int y1, int x2, int y2) {
        int x1p = x1 + width / 2;
        int x2p = x2 + width / 2;
        int y1p = height / 2 - y1;
        int y2p = height / 2 - y2;
        //System.out.println("Ejes " + x1p + " " + y1p + " " + x2p + " " + y2p);
        g.setColor(Color.BLACK);
        g.drawLine(x1p, height/2, x2p, height/2);
        g.drawLine(width/2, y1p, width/2, y2p);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Transformations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Transformations2D t2d = new Transformations2D();
        frame.add(t2d);
        frame.addKeyListener(t2d);
        t2d.setBackground(Color.WHITE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setFocusable(true);
    }
}
