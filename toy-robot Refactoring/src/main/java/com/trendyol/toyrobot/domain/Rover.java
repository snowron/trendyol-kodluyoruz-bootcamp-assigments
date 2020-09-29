package com.trendyol.toyrobot.domain;

import com.trendyol.toyrobot.domain.Compasses.CompassInterface;
import com.trendyol.toyrobot.domain.Compasses.North;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Rover {

    private String id;
    private int x;
    private int y;
    private CompassInterface compass;
    private List<Material> material;

    public Rover() {
        this.id = UUID.randomUUID().toString();
        this.x = 0;
        this.y = 0;
        this.compass = new North();
        this.compass.setRover(this);
        this.material = Arrays.asList(new Material("Ametist"));
    }

    public Rover(int x, int y, CompassInterface compass) {
        this.id = UUID.randomUUID().toString();
        this.x = x;
        this.y = y;
        compass.setRover(this);
        this.compass = compass;
        this.material = Arrays.asList(new Material("Iron"));
    }

    public void move() {
        this.compass.getNewCoordinates();
    }
    public void turnLeft() {
        this.compass.getLeftDirection();
    }

    public void turnRight() {
        this.compass.getRightDirection();
    }

    public void increaseX() {
        this.x += 1;
    }

    public void decreaseX() {
        this.x -= 1;
    }

    public void increaseY() {
        this.y += 1;
    }

    public void decreaseY() {
        this.y -= 1;
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public CompassInterface getCompass() {
        return this.compass;
    }

    public void setCompass(CompassInterface compass) {
        compass.setRover(this);
        this.compass = compass;
    }
}
