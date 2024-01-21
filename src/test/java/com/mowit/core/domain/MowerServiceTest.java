package com.mowit.core.domain;

import com.mowit.core.model.Coordinates;
import com.mowit.core.model.Lawn;
import com.mowit.core.model.LawnMower;
import com.mowit.core.model.Orientation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MowerServiceTest {

    @ParameterizedTest
    @MethodSource("mowerAndInstructionAndUpdatedMower")
    void testMow(LawnMower mower, String instruction, LawnMower updatedMower) {
        Lawn lawn = new Lawn(new Coordinates(5, 5));
        MowerService mowerService = new MowerService();
        LawnMower result = mowerService.mow(lawn, mower, instruction);
        assertNotNull(result);
        assertEquals(updatedMower, result);
    }

    static Stream<Arguments> mowerAndInstructionAndUpdatedMower() {
        return Stream.of(
                arguments(new LawnMower(new Coordinates(1, 2), Orientation.N), "GAGAGAGAA",
                        new LawnMower(new Coordinates(1, 3), Orientation.N)),
                arguments(new LawnMower(new Coordinates(3, 3), Orientation.E), "AADAADADDA",
                        new LawnMower(new Coordinates(5, 1), Orientation.E)),
                arguments(new LawnMower(new Coordinates(1, 4), Orientation.W), "AAAAAAAAAA",
                        new LawnMower(new Coordinates(0, 4), Orientation.W)));
    }
}