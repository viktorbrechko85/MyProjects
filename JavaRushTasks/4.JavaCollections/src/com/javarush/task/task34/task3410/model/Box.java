package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    public Box(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX()+x);
        this.setY(this.getY()+y);
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D gr2d = (Graphics2D) graphics;
        gr2d.setColor(Color.YELLOW);
        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;

        gr2d.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        gr2d.setColor(Color.BLUE);
        gr2d.fillRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());


    }
}
