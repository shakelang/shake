package com.github.nsc.de.shake.preprocessor;

import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.Tree;
import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.parser.node.expression.*;
import com.github.nsc.de.shake.parser.node.factor.DoubleNode;
import com.github.nsc.de.shake.parser.node.factor.IntegerNode;

public class PreProcessor {

    private final PreProcessorSettings settings;

    private PreProcessor() {
        this.settings = new PreProcessorSettings();
    }

    private PreProcessor(PreProcessorSettings settings) {
        this.settings = settings;
    }

    private Node process(Node t, PreProcessorContext ctx) {
        if(t instanceof Tree) return process((Tree) t, ctx);
        if(t instanceof IntegerNode) return process((IntegerNode) t);
        if(t instanceof DoubleNode) return process((DoubleNode) t);
        if(t instanceof AddNode) return process((AddNode) t, ctx);
        if(t instanceof SubNode) return process((SubNode) t, ctx);
        if(t instanceof MulNode) return process((MulNode) t, ctx);
        if(t instanceof DivNode) return process((DivNode) t, ctx);
        if(t instanceof ModNode) return process((ModNode) t, ctx);
        if(t instanceof PowNode) return process((PowNode) t, ctx);
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

    private IntegerNode process(IntegerNode node) {
        return node;
    }

    private DoubleNode process(DoubleNode node) {
        return node;
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

        return new AddNode(left, right);
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

        return new SubNode(left, right);
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

        return new MulNode(left, right);
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

        return new DivNode(left, right);
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

        return new ModNode(left, right);
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

        return new PowNode(left, right);
    }



    public PreProcessorSettings getSettings() {
        return settings;
    }
}
