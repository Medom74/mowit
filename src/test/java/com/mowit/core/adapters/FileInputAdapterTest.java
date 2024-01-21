package com.mowit.core.adapters;

import com.mowit.core.adapters.input.FileInputAdapter;
import com.mowit.core.domain.MowerService;
import com.mowit.core.exception.InvalidFileFormatException;
import com.mowit.core.exception.InvalidInstructionException;
import com.mowit.core.exception.InvalidOrientationException;
import com.mowit.core.exception.InvalidPositionException;
import com.mowit.core.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FileInputAdapterTest {
    private MowerService mowerServiceMock;
    private FileInputAdapter fileInputAdapterMock;

    private static String NOMINAL_SCENARIO_FILE_NAME = "nominal_scenario.txt";
    private static String ERROR_SCENARIO_FILE_NAME = "error_scenario.txt";
    private static String INVALID_LAWN_SCENARIO_FILE_NAME = "invalid_lawn_scenario.txt";
    private static String INCOMPLETE_LAWN_SCENARIO_FILE_NAME = "incomplete_lawn_scenario.txt";
    private static String MISSING_INSTRUCTIONS_SCENARIO_FILE_NAME = "missing_instructions_scenario.txt";
    private static String INVALID_MOWER_ORIENTATION_FILE_NAME = "invalid_mower_orientation_scenario.txt";
    private static String INVALID_INSTRUCTION_LINE_FILE_NAME = "invalid_instruction_line.txt";
    private static String INVALID_MOWER_POSITION_SCENARIO_FILE_NAME = "invalid_mower_position_scenario.txt";
    private static String EMPTY_FILE_NAME = "empty_file.txt";
    private static String MOWER_OUTSIDE_LAWN_FILE_NAME = "mower_outside_lawn_scenario.txt";


    @BeforeEach
    void init() {
        mowerServiceMock = new MowerService();
        fileInputAdapterMock = new FileInputAdapter(mowerServiceMock);
    }

    @Test
    void testNominalScenario() {
        File inputFile = getFile(NOMINAL_SCENARIO_FILE_NAME);
        LawnMower firstMowerUpdated = new LawnMower(new Coordinates(1, 3), Orientation.N);
        LawnMower secondMowerUpdated = new LawnMower(new Coordinates(5, 1), Orientation.E);

        var mowers = fileInputAdapterMock.readInput(inputFile);

        assertNotNull(mowers);
        assertEquals(2, mowers.size());
        assertEquals(firstMowerUpdated.position(), mowers.get(0).position());
        assertEquals(firstMowerUpdated.orientation(), mowers.get(0).orientation());
        assertEquals(secondMowerUpdated.position(), mowers.get(1).position());
        assertEquals(secondMowerUpdated.orientation(), mowers.get(1).orientation());
    }

    @ParameterizedTest
    @MethodSource("getFileNameAndExceptionClass")
    void testReadFileInputWithException(String fileName, Class exceptionClass) {
        File inputFile = getFile(fileName);
        assertThrows(exceptionClass, () -> fileInputAdapterMock.readInput(inputFile));
    }

   private static Stream<Arguments> getFileNameAndExceptionClass() {
        return Stream.of(
                arguments(ERROR_SCENARIO_FILE_NAME, InvalidInstructionException.class),
                arguments(INVALID_LAWN_SCENARIO_FILE_NAME, InvalidFileFormatException.class),
                arguments(INCOMPLETE_LAWN_SCENARIO_FILE_NAME, InvalidFileFormatException.class),
                arguments(MISSING_INSTRUCTIONS_SCENARIO_FILE_NAME, InvalidInstructionException.class),
                arguments(INVALID_MOWER_ORIENTATION_FILE_NAME, InvalidOrientationException.class),
                arguments(INVALID_INSTRUCTION_LINE_FILE_NAME, InvalidInstructionException.class),
                arguments(INVALID_MOWER_POSITION_SCENARIO_FILE_NAME, InvalidPositionException.class),
                arguments(MOWER_OUTSIDE_LAWN_FILE_NAME, InvalidPositionException.class),
                arguments(EMPTY_FILE_NAME, InvalidFileFormatException.class)
        );
    }
    private File getFile(String fileName) {
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }
}