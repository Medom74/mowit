package com.mowit.core.model;

/**
 * Represents the lawn where mowers can move.
 */
public class Lawn {
    private final Coordinates dimensions;

    /**
     * Constructs a lawn with the specified dimensions.
     *
     * @param dimensions The dimensions of the lawn.
     */
    public Lawn(Coordinates dimensions) {
        this.dimensions = dimensions;
    }
    /**
     * Checks if a given position is valid within the lawn dimensions.
     *
     * @param position The position to check.
     * @return True if the position is valid, false otherwise.
     */
    public boolean isValidPosition(Coordinates position) {
        return position.x() >= 0 && position.x() <= dimensions.x()
                && position.y() >= 0 && position.y() <= dimensions.y();
    }
}