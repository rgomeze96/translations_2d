package Geometry2D;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JFrame;
import Math.Matrix3x3;
import Math.Point3;
/**
 *
 * @author htrefftz
 */
public class Transformations2D
        extends JPanel
        implements KeyListener {


    Line line0;
    Line line1;
    Line line2;
    Line line3;
    Line line4;
    Line line5;

    int height = 800;
    int width = 800;

    Point3[] housePoints = {
            new Point3(100, 100,1),
            new Point3(200, 100,1),
            new Point3(200,200,1),
            new Point3(150,250,1),
            new Point3(100,200,1),
    };

    double step = 20;
    double rotationAngle = Math.PI/12;
    double upScaleFactor = 1.2;
    double downScaleFactor = 0.8;

    public Transformations2D() {
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Reset list of lines to draw
        LinkedList<Line> linesToDraw;
        {
            linesToDraw = new LinkedList<>();
        }
        // Make lines using the points in the housePoints list
        line0 = new Line(housePoints[0].x, housePoints[0].y, housePoints[1].x, housePoints[1].y);
        line1 = new Line(housePoints[1].x, housePoints[1].y, housePoints[2].x, housePoints[2].y);
        line2 = new Line(housePoints[2].x, housePoints[2].y, housePoints[3].x, housePoints[3].y);
        line3 = new Line(housePoints[3].x, housePoints[3].y, housePoints[4].x, housePoints[4].y);
        line4 = new Line(housePoints[4].x, housePoints[4].y, housePoints[0].x, housePoints[0].y);
        line5 = new Line(housePoints[2].x, housePoints[2].y, housePoints[4].x, housePoints[4].y);
        // Add all the lines of the house to the list linesToDraw
        linesToDraw.add(0,line0);
        linesToDraw.add(1,line1);
        linesToDraw.add(2,line2);
        linesToDraw.add(3,line3);
        linesToDraw.add(4,line4);
        linesToDraw.add(5,line5);
        // Get 2D graphics to draw lines using doubles
        Graphics2D g2d = (Graphics2D) g;
        // Color of line will be black
        g.setColor(Color.BLACK);
        // Draw each line in the list linesToDraw
        for (Line lineToDraw : linesToDraw) {
            // Build a 2D line using the coordinates from the line
            Line2D.Double lineBuilder = new Line2D.Double(convertXToCartesian(lineToDraw.x1),
                    convertYToCartesian(lineToDraw.y1),convertXToCartesian(lineToDraw.x2),
                    convertYToCartesian(lineToDraw.y2));
            g2d.draw(lineBuilder);
        }
        // Draw axes in the center of the window
        myDrawAxes(g, -100, -100, 100, 100);
    }

    public double convertXToCartesian(double x1){
        return x1 + width / 2.0;
    }
    public double convertYToCartesian(double y1){
        return height / 2.0 - y1;
    }

    public double convertXToJava(double x1){
        return x1 - width / 2.0;
    }

    public double convertYToJava(double y1){
        return height / 2.0 - y1;
    }

    public void translate(double xStep, double yStep){
        Matrix3x3 translateMatrix = new Matrix3x3();
        translateMatrix.matrix[0][2] = xStep;
        translateMatrix.matrix[1][2] = yStep;
        for(int i = 0; i < this.housePoints.length; i++){
            Point3 newPoint = Matrix3x3.times(translateMatrix, this.housePoints[i]);
            this.housePoints[i] = newPoint;
        }
        repaint();
    }

    public void rotate(double rotationAngle){
        Matrix3x3 rotateMatrix = new Matrix3x3();
        rotateMatrix.matrix[0][0] = Math.cos(rotationAngle);
        rotateMatrix.matrix[0][1] = -Math.sin(rotationAngle);
        rotateMatrix.matrix[1][0] = Math.sin(rotationAngle);
        rotateMatrix.matrix[1][1] = Math.cos(rotationAngle);
        double oldX = this.housePoints[0].x;
        double oldY = this.housePoints[0].y;
        this.translate(-oldX, -oldY);
        for(int i = 0; i < this.housePoints.length; i++){
            Point3 newPoint = Matrix3x3.times(rotateMatrix, this.housePoints[i]);
            this.housePoints[i] = newPoint;
        }
        this.translate(oldX,oldY);
        repaint();
    }

    public void scaleHouse(double scaleFactor){
        Matrix3x3 scaleMatrix = new Matrix3x3();
        scaleMatrix.matrix[0][0] = scaleFactor;
        scaleMatrix.matrix[1][1] = scaleFactor;
        double oldX = this.housePoints[0].x;
        double oldY = this.housePoints[0].y;
        this.translate(-oldX, -oldY);
        for(int i = 0; i < this.housePoints.length; i++){
            Point3 newPoint = Matrix3x3.times(scaleMatrix, this.housePoints[i]);
            this.housePoints[i] = newPoint;
        }
        this.translate(oldX,oldY);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released:" + e.getKeyCode());
        int keyPressed = e.getKeyCode();
        if (keyPressed == 87) {
            this.translate(0, this.step);
        }
        if (keyPressed == 65) {
            this.translate(-this.step,0);
        }
        if (keyPressed == 83) {
            this.translate(0, -this.step);
        }
        if (keyPressed == 68) {
            this.translate(this.step,0);
        }
        if (keyPressed == 81) {
            this.rotate(rotationAngle);
        }
        if (keyPressed == 69) {
            this.rotate(-rotationAngle);
        }
        if (keyPressed == 38) {
            this.scaleHouse(upScaleFactor);
        }
        if (keyPressed == 40) {
            this.scaleHouse(downScaleFactor);
        }
    }
    private void myDrawAxes(Graphics g, int x1, int y1, int x2, int y2) {
        int x1p = x1 + width / 2;
        int x2p = x2 + width / 2;
        int y1p = height / 2 - y1;
        int y2p = height / 2 - y2;
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
        t2d.setBackground(Color.LIGHT_GRAY);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setFocusable(true);
    }
}
