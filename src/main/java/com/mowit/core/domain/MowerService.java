package com.mowit.core.domain;

import com.mowit.core.model.Lawn;
import com.mowit.core.model.LawnMower;

/**
 * Interface for managing lawn mowers on a lawn.
 */
public interface MowerService {
    /**
     * Moves a lawn mower on the specified lawn based on a sequence of instructions.
     *
     * @param lawn         The lawn where the mower moves.
     * @param mower        The initial state of the mower.
     * @param instructions The sequence of instructions for the mower.
     * @return The updated mower after applying the instructions.
     */
    LawnMower mow(Lawn lawn, LawnMower mower, String instructions);
}
