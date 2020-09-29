package com.trendyol.toyrobot.domain.Compasses;

import com.trendyol.toyrobot.domain.Rover;

public class East implements CompassInterface {
    private String direction = "EAST";
    private Rover rover;

    @Override
    public String getDirection() {
        return this.direction;
    }


    @Override
    public void setRover(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void getLeftDirection() {
        this.rover.setCompass(new North());
    }

    @Override
    public void getRightDirection() {
        this.rover.setCompass(new South());
    }

    @Override
    public void getNewCoordinates() {
        this.rover.increaseX();
    }
}
