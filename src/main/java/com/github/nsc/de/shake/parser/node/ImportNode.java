package com.github.nsc.de.shake.parser.node;

public class ImportNode implements Node {

    private final String[] imported;

    public ImportNode(String[] imported) {
        this.imported = imported;
    }

    public String[] getImport() {
        return imported;
    }
}
