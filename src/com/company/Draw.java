package com.company;

import javax.swing.*;
import java.awt.*;

public class Draw extends JFrame {

    private int mXPoint (Point p1, Point p2){
        return Math.abs(p1.x - p2.x) / 2;
    }

    private int mYPoint (Point p1, Point p2){
        return Math.abs(p1.y - p2.y) / 2;
    }

    private void paintLines (Point down, Point left, Point right, Graphics gr){
        gr.drawLine(left.x, left.y, down.x, down.y);
        gr.drawLine(down.x, down.y, right.x, right.y);
        gr.drawLine(left.x, left.y, right.x, right.y);
    }

    public boolean eqXPoints (Point p1, Point p2){
        return p1.x == p2.x;
    }

    public boolean eqYPoints (Point p1, Point p2) {
        return p1.y == p2.y;
    }

    public void serpinskyMain(Point upp, Point left, Point right, Graphics gr){

        Point down = new Point(left.x + mXPoint(left, right), left.y);
        Point dLeft = new Point(left.x + mXPoint(left, upp), left.y - mYPoint(left, upp));
        Point dRight = new Point(right.x - mXPoint(right, upp), right.y - mYPoint(right, upp));

        if (!eqXPoints(down, left) && !eqYPoints(dLeft, left) && !eqYPoints(dRight, right)) {

            paintLines(down, dLeft, dRight, gr);

            serpinskyMain(dLeft, left, down, gr);
            serpinskyMain(upp, dLeft, dRight, gr);
            serpinskyMain(dRight, down, right, gr);
        }

    }

    public void serpinsky(Point upp, Point left, Point right, Graphics gr) {

        gr.drawLine(left.x, left.y, upp.x, upp.y);
        gr.drawLine(left.x, left.y, right.x, right.y);
        gr.drawLine(upp.x, upp.y, right.x, right.y);

        serpinskyMain(upp, left, right, gr);

    }


    public Draw() {
        this.setTitle("Serpinsky");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(1000, 700);

    }

    public void paint (Graphics g){
        super.paint(g);

        Graphics2D gr = (Graphics2D) g;

        serpinsky(new Point(700, 100), new Point(100, 700), new Point(1300, 700), gr);

    }

}
