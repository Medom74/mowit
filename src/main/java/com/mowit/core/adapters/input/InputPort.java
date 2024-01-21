package com.mowit.core.adapters.input;

import com.mowit.core.model.LawnMower;

import java.io.File;
import java.util.List;

/**
 * InputPort is an interface for components responsible for reading input data.
 * Implementing classes should provide a method to read input and return a list of LawnMower instances.
 */
public interface InputPort {
    /**
     * Reads input from a file and returns a list of LawnMower instances.
     *
     * @param inputFile The input file containing the lawn dimensions, mower's initial position, and instructions.
     * @return A list of LawnMower instances representing the initial state of mowers.
     */
    List<LawnMower> readInput(File inputFile);
}
