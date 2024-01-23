package com.mowit.core.application;

import com.mowit.core.adapters.input.InputPort;
import com.mowit.core.adapters.output.ConsoleOutputAdapter;
import com.mowit.core.adapters.input.FileInputAdapter;
import com.mowit.core.adapters.output.OutputPort;
import com.mowit.core.domain.MowerService;
import com.mowit.core.domain.MowerServiceImpl;
import com.mowit.core.exception.InvalidFileFormatException;
import com.mowit.core.exception.InvalidInstructionException;
import com.mowit.core.exception.InvalidOrientationException;
import com.mowit.core.exception.InvalidPositionException;
import com.mowit.core.model.LawnMower;


import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main entry point for the application.
 * It assembles the necessary components, reads input from a file,
 * initializes the lawn and mowers, and displays the final positions
 * of the mowers after processing instructions.
 */

public class MowItApp {
    private static final Logger LOGGER = Logger.getLogger(MowItApp.class.getName());
    private static final String FILE_PATH = "./src/main/resources/file.txt";

    public static void main(String[] args) {
        // Assemble the components
        MowerService mowerService = new MowerServiceImpl();
        InputPort fileInputAdapter = new FileInputAdapter(mowerService);
        OutputPort consoleOutputAdapter = new ConsoleOutputAdapter();
        // Read the input file and initialize the lawn and mower
        try {
            List<LawnMower> mowers = fileInputAdapter
                    .readInput(new File(FILE_PATH));
            // Display the output
            consoleOutputAdapter.displayOutput(mowers);
        } catch (InvalidFileFormatException
                 | InvalidInstructionException
                 | InvalidOrientationException
                 | InvalidPositionException e) {
            LOGGER.log(Level.SEVERE, "Error occurred", e);
        }
    }
}
