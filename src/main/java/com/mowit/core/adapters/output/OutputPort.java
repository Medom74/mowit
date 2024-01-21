package com.mowit.core.adapters.output;

import com.mowit.core.model.LawnMower;

import java.util.List;

/**
 * OutputPort is an interface representing the output port for displaying the final positions
 * of lawn mowers. Implementations of this interface should provide a way to display the output.
 */
public interface OutputPort {
    /**
     * Displays the final positions of lawn mowers.
     *
     * @param mowers The list of lawn mowers with their final positions.
     */
    void displayOutput(List<LawnMower> mowers);
}
