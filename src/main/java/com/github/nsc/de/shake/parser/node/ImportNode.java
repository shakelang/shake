package com.github.nsc.de.shake.parser.node;

import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class ImportNode extends Node {

    public static final String EVERYTHING = "*";

    private final String[] imported;

    public ImportNode(PositionMap map, String[] imported) {
        super(map);
        this.imported = imported;
    }

    public String[] getImport() {
        return imported;
    }
}
