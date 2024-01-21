package com.mowit.core.model;

/**
 * Represents cardinal directions (N, E, S, W).
 */
public enum Orientation {
    N, // North
    E, // East
    S, // South
    W; // West

    /**
     * Returns the orientation obtained by turning 90 degrees to the right.
     *
     * @return The new orientation after turning right.
     */
    public Orientation turnRight() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }

    /**
     * Returns the orientation obtained by turning 90 degrees to the left.
     *
     * @return The new orientation after turning left.
     */
    public Orientation turnLeft() {
        return switch (this) {
            case N -> W;
            case E -> N;
            case S -> E;
            case W -> S;
        };
    }
}