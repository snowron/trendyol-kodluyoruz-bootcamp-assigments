
package com.trendyol.toyrobot.domain.Compasses;

import com.trendyol.toyrobot.domain.Rover;
import lombok.Getter;

@Getter
public class North implements CompassInterface {
    private String direction = "NORTH";
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
        this.rover.setCompass(new West());
    }

    @Override
    public void getRightDirection() {
        this.rover.setCompass(new East());
    }

    @Override
    public void getNewCoordinates() {
        this.rover.increaseY();
    }
}