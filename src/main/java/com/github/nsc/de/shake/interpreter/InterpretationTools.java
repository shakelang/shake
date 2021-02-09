package com.github.nsc.de.shake.interpreter;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;

public class InterpretationTools {

    private final PositionMap positionMap;

    public InterpretationTools(PositionMap positionMap) {
        this.positionMap = positionMap;
    }

    public PositionMap getPositionMap() {
        return positionMap;
    }
}
