package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class GameObject {
    private int color; // Object color
    private int size;  // Object size
    private Point location; // Location of the object

    public GameObject(int color, int size) {
        this.color = color;
        this.size = size;
        this.location = new Point(0, 0); // Default location
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(float x, float y) {
        this.location.setX(x);
        this.location.setY(y);
    }
}
