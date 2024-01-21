package com.mowit.core.model;

/**
 * Represents a lawn mower with a position and orientation.
 */
public record LawnMower(Coordinates position, Orientation orientation) {


    /**
     * Turns the mower 90 degrees to the right.
     *
     * @return A new LawnMower instance with the updated orientation.
     */
    public LawnMower turnRight() {
        return new LawnMower(position, orientation.turnRight());
    }

    /**
     * Turns the mower 90 degrees to the left.
     *
     * @return A new LawnMower instance with the updated orientation.
     */
    public LawnMower turnLeft() {
        return new LawnMower(position, orientation.turnLeft());
    }

    /**
     * Moves the mower one step forward in its current orientation.
     *
     * @return A new LawnMower instance with the updated position.
     */
    public LawnMower advance() {
        return new LawnMower(calculateNewPosition(), orientation);
    }

    private Coordinates calculateNewPosition() {
        int x = position.x();
        int y = position.y();
        return switch (orientation) {
            case N -> new Coordinates(x, y + 1);
            case E -> new Coordinates(x + 1, y);
            case S -> new Coordinates(x, y - 1);
            case W -> new Coordinates(x - 1, y);
        };
    }
}
