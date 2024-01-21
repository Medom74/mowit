package com.mowit.core.domain;

import com.mowit.core.model.Coordinates;
import com.mowit.core.model.Instruction;
import com.mowit.core.model.Lawn;
import com.mowit.core.model.LawnMower;

/**
 * Service class for managing lawn mowers on a lawn.
 */

public class MowerService {

    /**
     * Moves a lawn mower on the specified lawn based on a sequence of instructions.
     *
     * @param lawn         The lawn where the mower moves.
     * @param mower        The initial state of the mower.
     * @param instructions The sequence of instructions for the mower.
     * @return The updated mower after applying the instructions.
     */
    public LawnMower mow(Lawn lawn, LawnMower mower, String instructions) {
        for (char instruction : instructions.toCharArray()) {
            Instruction currentInstruction = Instruction.valueOf(String.valueOf(instruction));
            mower = applyInstruction(lawn, mower, currentInstruction);
        }
        return mower; // Return the lawn mower updated
    }

    private LawnMower applyInstruction(Lawn lawn, LawnMower mower, Instruction instruction) {
        return switch (instruction) {
            case D -> mower.turnRight();
            case G -> mower.turnLeft();
            case A -> {
                LawnMower newMower = mower.advance();
                if (isValidMove(lawn, newMower)) {
                    mower = newMower;
                }
                yield mower;
            }
        };
    }

    private boolean isValidMove(Lawn lawn, LawnMower mower) {
        Coordinates newPosition = mower.position();
        return lawn.isValidPosition(newPosition);
    }
}
