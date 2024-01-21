package com.mowit.core.adapters.input;

import com.mowit.core.domain.MowerService;
import com.mowit.core.exception.InvalidFileFormatException;
import com.mowit.core.exception.InvalidInstructionException;
import com.mowit.core.exception.InvalidOrientationException;
import com.mowit.core.exception.InvalidPositionException;
import com.mowit.core.model.Coordinates;
import com.mowit.core.model.Lawn;
import com.mowit.core.model.LawnMower;
import com.mowit.core.model.Orientation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class to read input from a file and initialize the lawn and mower.
 */
public class FileInputAdapter implements InputPort {
    private final MowerService mowerService;
    private static final String COORDINATES_REGEX = "\\d+ \\d+";
    private static final String POSITION_REGEX = "\\d+";
    private static final String ORIENTATION_REGEX = "[NESW]";
    private static final String INSTRUCTIONS_REGEX = "[GDA]+";

    public FileInputAdapter(MowerService mowerService) {
        this.mowerService = mowerService;
    }

    /**
     * Reads the input file, initializes the lawn and mower, and processes the mower's instructions.
     *
     * @param inputFile The input file containing the lawn dimensions, mower's initial position, and instructions.
     * @return the list of LawnMower updated
     */
    public List<LawnMower> readInput(File inputFile) {
        List<LawnMower> mowers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            // Read the first line to get the coordinates max of the lawn
            String coordinatesMaxInput = reader.readLine();
            validateCoordinatesMax(coordinatesMaxInput);
            String[] coordinatesMax = coordinatesMaxInput.split(" ");
            int lawnX = Integer.parseInt(coordinatesMax[0]);
            int lawnY = Integer.parseInt(coordinatesMax[1]);
            var lawn = new Lawn(new Coordinates(lawnX, lawnY));

            // Read lines for each mower and process its instructions
            String mowerPositionLine;
            while ((mowerPositionLine = reader.readLine()) != null) {
                String mowerInstructionsLine = reader.readLine();

                // Extract the mower's initial position
                String[] mowerPosition = mowerPositionLine.split(" ");
                validateMowerPositionLine(mowerPosition, lawn);
                int mowerX = Integer.parseInt(mowerPosition[0]);
                int mowerY = Integer.parseInt(mowerPosition[1]);
                Coordinates initialMowerPosition = new Coordinates(mowerX, mowerY);

                // Extract the mower's initial orientation
                validateMowerOrientation(mowerPosition[2]);
                Orientation initialMowerOrientation = Orientation.valueOf(mowerPosition[2]);

                // Initialize the mower with the extracted information
                var mower = new LawnMower(initialMowerPosition, initialMowerOrientation);

                // Process the mower's instructions and add the mowers updated to the list
                validateMowerInstructionsLine(mowerInstructionsLine);
                mowers.add(mowerService.mow(lawn, mower, mowerInstructionsLine));
            }
        } catch (IOException | NumberFormatException e) {
            throw new InvalidFileFormatException("Error reading input file");
        }
        return mowers;
    }

    private void validateCoordinatesMax(String coordinatesMax) {
        if (coordinatesMax == null || !coordinatesMax.matches(COORDINATES_REGEX)) {
            throw new InvalidFileFormatException("Invalid lawn coordinates");
        }
    }

    private void validateMowerPositionLine(String[] mowerPosition, Lawn lawn) {
        if (mowerPosition.length != 3
                || !mowerPosition[0].matches(POSITION_REGEX)
                || !mowerPosition[1].matches(POSITION_REGEX)) {
            throw new InvalidPositionException("Invalid mower position format");
        }

        int mowerX = Integer.parseInt(mowerPosition[0]);
        int mowerY = Integer.parseInt(mowerPosition[1]);
        Coordinates initialMowerPosition = new Coordinates(mowerX, mowerY);

        if (!lawn.isValidPosition(initialMowerPosition)) {
            throw new InvalidPositionException("Mower position is outside the lawn dimensions");
        }
    }

    private void validateMowerOrientation(String orientation) {
        if (!orientation.matches(ORIENTATION_REGEX)) {
            throw new InvalidOrientationException("Invalid mower orientation format");
        }
    }

    private void validateMowerInstructionsLine(String mowerInstructionsLine) {
        if (mowerInstructionsLine == null
                || mowerInstructionsLine.trim().isEmpty()
                || !mowerInstructionsLine.matches(INSTRUCTIONS_REGEX)) {
            throw new InvalidInstructionException("Mower instructions are missing");
        }

    }
}
