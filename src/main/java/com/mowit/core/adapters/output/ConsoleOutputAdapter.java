package com.mowit.core.adapters.output;

import com.mowit.core.model.LawnMower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

/**
 * ConsoleOutputAdapter is responsible for displaying the final positions of lawnmowers on the console.
 */

public class ConsoleOutputAdapter implements OutputPort {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleOutputAdapter.class);

    /**
     * Displays the final positions of lawnmowers on the console.
     *
     * @param mowers The list of lawnmowers with their final positions.
     */
    @Override
    public void displayOutput(List<LawnMower> mowers) {
        if (!mowers.isEmpty()) {
            mowers.forEach(mower -> logger.info(mower.position() + " " + mower.orientation() + " "));
        }
    }
}
