package com.mowit.core.adapters.output;

import com.mowit.core.model.LawnMower;

import java.util.List;

/**
 * ConsoleOutputAdapter is responsible for displaying the final positions of lawnmowers on the console.
 */

public class ConsoleOutputAdapter implements OutputPort {
    /**
     * Displays the final positions of lawnmowers on the console.
     *
     * @param mowers The list of lawnmowers with their final positions.
     */
    @Override
    public void displayOutput(List<LawnMower> mowers) {
        if (!mowers.isEmpty()) {
            mowers.forEach(mower -> System.out.print(mower.position() + " " + mower.orientation() + " "));
        }
    }
}
