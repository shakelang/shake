package com.github.nsc.de.shake.preprocessor;

import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.Tree;
import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.parser.node.expression.*;
import com.github.nsc.de.shake.parser.node.factor.CharacterNode;
import com.github.nsc.de.shake.parser.node.factor.DoubleNode;
import com.github.nsc.de.shake.parser.node.factor.IntegerNode;
import com.github.nsc.de.shake.parser.node.factor.StringNode;

public class PreProcessor {

    private final PreProcessorSettings settings;

    private PreProcessor() {
        this.settings = new PreProcessorSettings();
    }

    private PreProcessor(PreProcessorSettings settings) {
        this.settings = settings;
    }

    private Node process(Node n, PreProcessorContext ctx) {
        if(n instanceof IntegerNode || n instanceof DoubleNode || n instanceof CharacterNode ||
                n instanceof StringNode) return n;
        if(n instanceof ValuedNode) return process((ValuedNode) n, ctx);
        if(n instanceof Tree) return process((Tree) n, ctx);
        throw new Error();
    }

    private ValuedNode process(ValuedNode n, PreProcessorContext ctx) {
        if(n instanceof IntegerNode || n instanceof DoubleNode || n instanceof CharacterNode ||
                n instanceof StringNode) return n;
        if(n instanceof AddNode) return process((AddNode) n, ctx);
        if(n instanceof SubNode) return process((SubNode) n, ctx);
        if(n instanceof MulNode) return process((MulNode) n, ctx);
        if(n instanceof DivNode) return process((DivNode) n, ctx);
        if(n instanceof ModNode) return process((ModNode) n, ctx);
        if(n instanceof PowNode) return process((PowNode) n, ctx);
        throw new Error();
    }

    private Tree process(Tree tree, PreProcessorContext ctx) {

        Node[] toProcess = tree.getChildren();
        Node[] children = new Node[toProcess.length];

        for(int i = 0; i < toProcess.length; i++) {
            children[i] = process(toProcess[i], ctx);
        }

        return new Tree(children);

    }

    private ValuedNode process(AddNode node, PreProcessorContext ctx) {

        ValuedNode left = (ValuedNode) process(node.getLeft(), ctx);
        ValuedNode right = (ValuedNode) process(node.getRight(), ctx);

        if(this.getSettings().shortenCalculations()
                && (left instanceof DoubleNode || left instanceof IntegerNode)
                && (right instanceof DoubleNode || right instanceof IntegerNode)) {

            if(left instanceof DoubleNode && right instanceof DoubleNode)
                return new DoubleNode(((DoubleNode) left).getNumber() + ((DoubleNode) right).getNumber());

            if(left instanceof DoubleNode && right instanceof IntegerNode)
                return new DoubleNode(((DoubleNode) left).getNumber() + ((IntegerNode) right).getNumber());

            if(right instanceof DoubleNode)
                return new DoubleNode(((IntegerNode) left).getNumber() + ((DoubleNode) right).getNumber());

            return new IntegerNode(((IntegerNode) left).getNumber() + ((IntegerNode) right).getNumber());

        }

        return new AddNode(left, right, node.getOperatorIndex());
    }

    private ValuedNode process(SubNode node, PreProcessorContext ctx) {

        ValuedNode left = (ValuedNode) process(node.getLeft(), ctx);
        ValuedNode right = (ValuedNode) process(node.getRight(), ctx);

        if(this.getSettings().shortenCalculations()
                && (left instanceof DoubleNode || left instanceof IntegerNode)
                && (right instanceof DoubleNode || right instanceof IntegerNode)) {

            if(left instanceof DoubleNode && right instanceof DoubleNode)
                return new DoubleNode(((DoubleNode) left).getNumber() - ((DoubleNode) right).getNumber());

            if(left instanceof DoubleNode && right instanceof IntegerNode)
                return new DoubleNode(((DoubleNode) left).getNumber() - ((IntegerNode) right).getNumber());

            if(right instanceof DoubleNode)
                return new DoubleNode(((IntegerNode) left).getNumber() - ((DoubleNode) right).getNumber());

            return new IntegerNode(((IntegerNode) left).getNumber() - ((IntegerNode) right).getNumber());

        }

        return new SubNode(left, right, node.getOperatorIndex());
    }

    private ValuedNode process(MulNode node, PreProcessorContext ctx) {

        ValuedNode left = (ValuedNode) process(node.getLeft(), ctx);
        ValuedNode right = (ValuedNode) process(node.getRight(), ctx);

        if(this.getSettings().shortenCalculations()
                && (left instanceof DoubleNode || left instanceof IntegerNode)
                && (right instanceof DoubleNode || right instanceof IntegerNode)) {

            if(left instanceof DoubleNode && right instanceof DoubleNode)
                return new DoubleNode(((DoubleNode) left).getNumber() * ((DoubleNode) right).getNumber());

            if(left instanceof DoubleNode && right instanceof IntegerNode)
                return new DoubleNode(((DoubleNode) left).getNumber() * ((IntegerNode) right).getNumber());

            if(right instanceof DoubleNode)
                return new DoubleNode(((IntegerNode) left).getNumber() * ((DoubleNode) right).getNumber());

            return new IntegerNode(((IntegerNode) left).getNumber() * ((IntegerNode) right).getNumber());

        }

        return new MulNode(left, right, node.getOperatorIndex());
    }

    private ValuedNode process(DivNode node, PreProcessorContext ctx) {

        ValuedNode left = (ValuedNode) process(node.getLeft(), ctx);
        ValuedNode right = (ValuedNode) process(node.getRight(), ctx);

        if(this.getSettings().shortenCalculations()
                && (left instanceof DoubleNode || left instanceof IntegerNode)
                && (right instanceof DoubleNode || right instanceof IntegerNode)) {

            if(left instanceof DoubleNode && right instanceof DoubleNode)
                return new DoubleNode(((DoubleNode) left).getNumber() / ((DoubleNode) right).getNumber());

            if(left instanceof DoubleNode && right instanceof IntegerNode)
                return new DoubleNode(((DoubleNode) left).getNumber() / ((IntegerNode) right).getNumber());

            if(right instanceof DoubleNode)
                return new DoubleNode(((IntegerNode) left).getNumber() / ((DoubleNode) right).getNumber());

            return new IntegerNode(((IntegerNode) left).getNumber() / ((IntegerNode) right).getNumber());

        }

        return new DivNode(left, right, node.getOperatorIndex());
    }

    private ValuedNode process(ModNode node, PreProcessorContext ctx) {

        ValuedNode left = (ValuedNode) process(node.getLeft(), ctx);
        ValuedNode right = (ValuedNode) process(node.getRight(), ctx);

        if(this.getSettings().shortenCalculations()
                && (left instanceof DoubleNode || left instanceof IntegerNode)
                && (right instanceof DoubleNode || right instanceof IntegerNode)) {

            if(left instanceof DoubleNode && right instanceof DoubleNode)
                return new DoubleNode(((DoubleNode) left).getNumber() % ((DoubleNode) right).getNumber());

            if(left instanceof DoubleNode && right instanceof IntegerNode)
                return new DoubleNode(((DoubleNode) left).getNumber() % ((IntegerNode) right).getNumber());

            if(right instanceof DoubleNode)
                return new DoubleNode(((IntegerNode) left).getNumber() % ((DoubleNode) right).getNumber());

            return new IntegerNode(((IntegerNode) left).getNumber() % ((IntegerNode) right).getNumber());

        }

        return new ModNode(left, right, node.getOperatorIndex());
    }

    private ValuedNode process(PowNode node, PreProcessorContext ctx) {

        ValuedNode left = (ValuedNode) process(node.getLeft(), ctx);
        ValuedNode right = (ValuedNode) process(node.getRight(), ctx);

        if(this.getSettings().shortenCalculations()
                && (left instanceof DoubleNode || left instanceof IntegerNode)
                && (right instanceof DoubleNode || right instanceof IntegerNode)) {

            if(left instanceof DoubleNode && right instanceof DoubleNode)
                return new DoubleNode(Math.pow(((DoubleNode) left).getNumber(),  ((DoubleNode) right).getNumber()));

            if(left instanceof DoubleNode && right instanceof IntegerNode)
                return new DoubleNode(Math.pow(((DoubleNode) left).getNumber(), ((IntegerNode) right).getNumber()));

            if(right instanceof DoubleNode)
                return new DoubleNode(Math.pow(((IntegerNode) left).getNumber(), ((DoubleNode) right).getNumber()));

            return new IntegerNode((int) Math.pow(((IntegerNode) left).getNumber(), ((IntegerNode) right).getNumber()));

        }

        return new PowNode(left, right, node.getOperatorIndex());
    }



    public PreProcessorSettings getSettings() {
        return settings;
    }
}
