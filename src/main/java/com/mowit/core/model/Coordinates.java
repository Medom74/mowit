package com.mowit.core.model;

/**
 * Represents the coordinates (x, y) on a lawn.
 */

public record Coordinates(int x, int y) {
    @Override
    public String toString() {
        return x + " " + y;
    }
}
