package com.github.nsc.de.shake.generators.java;

import com.github.nsc.de.shake.generators.ShakeGenerator;
import com.github.nsc.de.shake.generators.java.nodes.*;
import com.github.nsc.de.shake.parser.node.*;
import com.github.nsc.de.shake.parser.node.expression.*;
import com.github.nsc.de.shake.parser.node.factor.CharacterNode;
import com.github.nsc.de.shake.parser.node.factor.DoubleNode;
import com.github.nsc.de.shake.parser.node.factor.IntegerNode;
import com.github.nsc.de.shake.parser.node.factor.StringNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.shake.parser.node.logical.*;
import com.github.nsc.de.shake.parser.node.loops.DoWhileNode;
import com.github.nsc.de.shake.parser.node.loops.ForNode;
import com.github.nsc.de.shake.parser.node.loops.WhileNode;
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode;
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode;
import com.github.nsc.de.shake.parser.node.variables.*;
import com.github.nsc.de.shake.util.ArrayUtil;

public class JavaGenerator implements ShakeGenerator {

    public JavaClass visitProgram(Tree t, String filename) {
        JavaClass cls = new JavaClass(filename, JavaAccessDescriptor.PUBLIC, false, false);
        cls.getFunctions().add(new JavaFunction("main", JavaVariableType.VOID,
                new JavaFunction.JavaFunctionArgument[] { new JavaFunction.JavaFunctionArgument("String[]", "args") },
                visitTree(t, new JavaGenerationContext(cls, true)), JavaAccessDescriptor.PUBLIC, true, false));
        return cls;
    }

    public JavaNode visit(Node n, JavaGenerationContext context) {

        if(n instanceof Tree) return visitTree((Tree) n, context);
        if(n instanceof DoubleNode) return visitDoubleNode((DoubleNode) n);
        if(n instanceof IntegerNode) return visitIntegerNode((IntegerNode) n);
        if(n instanceof AddNode) return visitAddNode((AddNode) n, context);
        if(n instanceof SubNode) return visitSubNode((SubNode) n, context);
        if(n instanceof MulNode) return visitMulNode((MulNode) n, context);
        if(n instanceof DivNode) return visitDivNode((DivNode) n, context);
        if(n instanceof ModNode) return visitModNode((ModNode) n, context);
        if(n instanceof PowNode) return visitPowNode((PowNode) n, context);
        if(n instanceof VariableDeclarationNode) return visitVariableDeclarationNode((VariableDeclarationNode) n, context);
        if(n instanceof VariableAddAssignmentNode) return visitVariableAddAssignmentNode((VariableAddAssignmentNode) n, context);
        if(n instanceof VariableSubAssignmentNode) return visitVariableSubAssignmentNode((VariableSubAssignmentNode) n, context);
        if(n instanceof VariableMulAssignmentNode) return visitVariableMulAssignmentNode((VariableMulAssignmentNode) n, context);
        if(n instanceof VariableDivAssignmentNode) return visitVariableDivAssignmentNode((VariableDivAssignmentNode) n, context);
        if(n instanceof VariableModAssignmentNode) return visitVariableModAssignmentNode((VariableModAssignmentNode) n, context);
        if(n instanceof VariablePowAssignmentNode) return visitVariablePowAssignmentNode((VariablePowAssignmentNode) n, context);
        if(n instanceof VariableIncreaseNode) return visitVariableIncreaseNode((VariableIncreaseNode) n, context);
        if(n instanceof VariableDecreaseNode) return visitVariableDecreaseNode((VariableDecreaseNode) n, context);
        if(n instanceof VariableAssignmentNode) return visitVariableAssignmentNode((VariableAssignmentNode) n, context);
        if(n instanceof VariableUsageNode) return visitVariableUsageNode((VariableUsageNode) n, context);
        if(n instanceof LogicalEqEqualsNode) return visitEqEqualsNode((LogicalEqEqualsNode) n, context);
        if(n instanceof LogicalBiggerEqualsNode) return visitBiggerEqualsNode((LogicalBiggerEqualsNode) n, context);
        if(n instanceof LogicalSmallerEqualsNode) return visitSmallerEqualsNode((LogicalSmallerEqualsNode) n, context);
        if(n instanceof LogicalBiggerNode) return visitBiggerNode((LogicalBiggerNode) n, context);
        if(n instanceof LogicalSmallerNode) return visitSmallerNode((LogicalSmallerNode) n, context);
        if(n instanceof LogicalAndNode) return visitLogicalAndNode((LogicalAndNode) n, context);
        if(n instanceof LogicalOrNode) return visitLogicalOrNode((LogicalOrNode) n, context);
        if(n instanceof LogicalXOrNode) return visitLogicalXOrNode((LogicalXOrNode) n, context);
        if(n instanceof WhileNode) return visitWhileNode((WhileNode) n, context);
        if(n instanceof DoWhileNode) return visitDoWhileNode((DoWhileNode) n, context);
        if(n instanceof ForNode) return visitForNode((ForNode) n, context);
        if(n instanceof IfNode) return visitIfNode((IfNode) n, context);
        if(n instanceof FunctionDeclarationNode) return visitFunctionDeclarationNode((FunctionDeclarationNode) n, context);
        if(n instanceof ClassConstructionNode) return visitClassConstruction((ClassConstructionNode) n, context);
        if(n instanceof FunctionCallNode) return visitFunctionCallNode((FunctionCallNode) n, context);
        if(n instanceof IdentifierNode) return visitIdentifierNode((IdentifierNode) n, context);
        if(n instanceof ClassDeclarationNode) return visitClassDeclarationNode((ClassDeclarationNode) n, context);
        if(n instanceof LogicalTrueNode) return new JavaValued.JavaValue("true");
        if(n instanceof LogicalFalseNode) return new JavaValued.JavaValue("false");
        if(n instanceof StringNode) return visitStringNode((StringNode) n);
        if(n instanceof CharacterNode) return visitCharacterNode((CharacterNode) n);
        throw new Error(String.format("It looks like that node is not implemented in the Interpreter: %s", n.getClass().toString()));

    }

    public JavaTree visitTree(Tree t, JavaGenerationContext context) {

        JavaNode.JavaOperation[] children = new JavaNode.JavaOperation[t.getChildren().length];

        for(int i = 0; i < t.getChildren().length; i++) {
            JavaNode n = visit(t.getChildren()[i], context);
            if(n instanceof JavaNode.JavaOperation) children[i] = (JavaNode.JavaOperation) n;
            else if(n instanceof JavaValued) {
                children[i] = new JavaValued.JavaFunctionCall(
                        new JavaIdentifier("println", new JavaIdentifier("out", new JavaIdentifier("System"))),
                        new JavaValued[] { (JavaValued) n });
            }
        }

        return new JavaTree(children);
    }


    public JavaValued.JavaDoublePart visitDoubleNode(DoubleNode n) {
        return new JavaValued.JavaDoublePart(n.getNumber());
    }


    public JavaValued.JavaIntegerPart visitIntegerNode(IntegerNode n) {
        return new JavaValued.JavaIntegerPart(n.getNumber());
    }


    public JavaValued.JavaValue visitStringNode(StringNode n) {
        return new JavaValued.JavaValue('"' + n.getValue() + '"');
    }


    public JavaValued.JavaValue visitCharacterNode(CharacterNode n) {
        return new JavaValued.JavaValue("'" + n.getValue() + "'");
    }


    public JavaValued.JavaExpression visitAddNode(AddNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression((JavaValued) visit(n.getLeft(), context), (JavaValued) visit(n.getRight(), context), "+");
    }


    public JavaValued.JavaExpression visitSubNode(SubNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression((JavaValued) visit(n.getLeft(), context), (JavaValued) visit(n.getRight(), context), "-");
    }


    public JavaValued.JavaExpression visitMulNode(MulNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression((JavaValued) visit(n.getLeft(), context), (JavaValued) visit(n.getRight(), context), "*");
    }


    public JavaValued.JavaExpression visitDivNode(DivNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression((JavaValued) visit(n.getLeft(), context), (JavaValued) visit(n.getRight(), context), "/");
    }


    public JavaValued.JavaExpression visitModNode(ModNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression((JavaValued) visit(n.getLeft(), context), (JavaValued) visit(n.getRight(), context), "%");
    }


    public JavaValued.JavaFunctionCall visitPowNode(PowNode n, JavaGenerationContext context) {
        return new JavaValued.JavaFunctionCall(
                new JavaIdentifier("pow", "Math"),
                new JavaValued[]{ (JavaValued) visit(n.getLeft(), context), (JavaValued) visit(n.getRight(), context) });
    }

    public JavaNode visitVariableDeclarationNode(VariableDeclarationNode n, JavaGenerationContext context) {
        JavaVariableType type = JavaVariableType.from(n.getType(), this, context);
        if(context.isInRoot()) {
            context.getActualClass().getFields().add(new JavaVariableDeclaration(type, n.getName(), true, false, JavaAccessDescriptor.PUBLIC));
            if(n.getAssignment() != null) return visit(n.getAssignment(), context);
            else return null;
        }
        else {
            if(n.getAssignment() != null)
                return new JavaVariableDeclaration(type, n.getName(),
                        (JavaValued) visit(n.getAssignment().getValue(), context), false, n.isFinal(), JavaAccessDescriptor.PACKAGE);
            return new JavaVariableDeclaration(type, n.getName(), n.isStatic(), n.isFinal(), JavaAccessDescriptor.PACKAGE);
        }
    }


    public JavaValued.JavaVariableAssignment visitVariableAssignmentNode(VariableAssignmentNode n, JavaGenerationContext context) {
        return new JavaValued.JavaVariableAssignment((JavaIdentifier) visit(n.getVariable(), context),
                (JavaValued) visit(n.getValue(), context));
    }


    public JavaNode visitVariableAddAssignmentNode(VariableAddAssignmentNode n, JavaGenerationContext context) {
        return new JavaValued.JavaVariableExpressionAssignment((JavaIdentifier) visit(n.getVariable(), context),
                (JavaValued) visit(n.getValue(), context), '+');
    }


    public JavaNode visitVariableSubAssignmentNode(VariableSubAssignmentNode n, JavaGenerationContext context) {
        return new JavaValued.JavaVariableExpressionAssignment((JavaIdentifier) visit(n.getVariable(), context),
                (JavaValued) visit(n.getValue(), context), '-');
    }


    public JavaNode visitVariableMulAssignmentNode(VariableMulAssignmentNode n, JavaGenerationContext context) {
        return new JavaValued.JavaVariableExpressionAssignment((JavaIdentifier) visit(n.getVariable(), context),
                (JavaValued) visit(n.getValue(), context), '*');
    }


    public JavaNode visitVariableDivAssignmentNode(VariableDivAssignmentNode n, JavaGenerationContext context) {
        return new JavaValued.JavaVariableExpressionAssignment((JavaIdentifier) visit(n.getVariable(), context),
                (JavaValued) visit(n.getValue(), context), '/');
    }


    public JavaNode visitVariableModAssignmentNode(VariableModAssignmentNode n, JavaGenerationContext context) {
        return new JavaValued.JavaVariableExpressionAssignment((JavaIdentifier) visit(n.getVariable(), context),
                (JavaValued) visit(n.getValue(), context), '%');
    }


    public JavaValued.JavaVariableAssignment visitVariablePowAssignmentNode(VariablePowAssignmentNode n, JavaGenerationContext context) {
        JavaIdentifier left = (JavaIdentifier) visit(n.getVariable(), context);
        return new JavaValued.JavaVariableAssignment(left,
                new JavaValued.JavaFunctionCall(
                        new JavaIdentifier("pow", "Math"),
                        new JavaValued[]{
                                left,
                                (JavaValued) visit(n.getValue(), context)
                        }));
    }


    public JavaValued.JavaVariableIncr visitVariableIncreaseNode(VariableIncreaseNode n, JavaGenerationContext context) {
        return new JavaValued.JavaVariableIncr((JavaIdentifier) visit(n.getVariable(), context));
    }


    public JavaValued.JavaVariableDecr visitVariableDecreaseNode(VariableDecreaseNode n, JavaGenerationContext context) {
        return new JavaValued.JavaVariableDecr((JavaIdentifier) visit(n.getVariable(), context));
    }


    public JavaValued.JavaVariable visitVariableUsageNode(VariableUsageNode n, JavaGenerationContext context) {
        return new JavaValued.JavaVariable(visitIdentifierNode(n.getVariable(), context));
    }


    public JavaValued.JavaExpression visitEqEqualsNode(LogicalEqEqualsNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression(
                (JavaValued) visit(n.getLeft(), context),
                (JavaValued) visit(n.getRight(), context),
                "==");
    }


    public JavaValued.JavaExpression visitBiggerEqualsNode(LogicalBiggerEqualsNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression(
                (JavaValued) visit(n.getLeft(), context),
                (JavaValued) visit(n.getRight(), context),
                ">=");
    }


    public JavaValued.JavaExpression visitSmallerEqualsNode(LogicalSmallerEqualsNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression(
                (JavaValued) visit(n.getLeft(), context),
                (JavaValued) visit(n.getRight(), context),
                "<=");
    }


    public JavaValued.JavaExpression visitBiggerNode(LogicalBiggerNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression(
                (JavaValued) visit(n.getLeft(), context),
                (JavaValued) visit(n.getRight(), context),
                ">");
    }


    public JavaValued.JavaExpression visitSmallerNode(LogicalSmallerNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression(
                (JavaValued) visit(n.getLeft(), context),
                (JavaValued) visit(n.getRight(), context),
                "<");
    }


    public JavaValued.JavaExpression visitLogicalAndNode(LogicalAndNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression(
                (JavaValued) visit(n.getLeft(), context),
                (JavaValued) visit(n.getRight(), context),
                "&&");
    }


    public JavaValued.JavaExpression visitLogicalOrNode(LogicalOrNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression(
                (JavaValued) visit(n.getLeft(), context),
                (JavaValued) visit(n.getRight(), context),
                "||");
    }


    public JavaValued.JavaExpression visitLogicalXOrNode(LogicalXOrNode n, JavaGenerationContext context) {
        return new JavaValued.JavaExpression(
                (JavaValued) visit(n.getLeft(), context),
                (JavaValued) visit(n.getRight(), context),
                "^");
    }


    public JavaWhileLoop visitWhileNode(WhileNode n, JavaGenerationContext context) {
        return new JavaWhileLoop((JavaValued) visit(n.getCondition(), context), visitTree(n.getBody(), context));
    }


    public JavaDoWhileLoop visitDoWhileNode(DoWhileNode n, JavaGenerationContext context) {
        return new JavaDoWhileLoop((JavaValued) visit(n.getCondition(), context), visitTree(n.getBody(), context));
    }


    public JavaForLoop visitForNode(ForNode n, JavaGenerationContext context) {
        return new JavaForLoop(
                (JavaNode.JavaOperation) visit(n.getDeclaration(), context),
                (JavaValued) visit(n.getCondition(), context),
                (JavaNode.JavaOperation) visit(n.getRound(), context),
                visitTree(n.getBody(), context));
    }


    public JavaIfCondition visitIfNode(IfNode n, JavaGenerationContext context) {
        return n.getElseBody() != null ?
                new JavaIfCondition((JavaValued) visit(n.getCondition(), context), visitTree(n.getBody(), context)) :
                new JavaIfCondition((JavaValued) visit(n.getCondition(), context), visitTree(n.getBody(), context),
                        visitTree(n.getElseBody(), context));
    }


    public JavaNode visitFunctionDeclarationNode(FunctionDeclarationNode n, JavaGenerationContext context) {

        context.getActualClass().getFunctions().add(new JavaFunction(
                n.getName(),
                JavaVariableType.VOID,
                ArrayUtil.map(n.getArgs(), new JavaFunction.JavaFunctionArgument[]{}, (arg) ->
                    new JavaFunction.JavaFunctionArgument("Object", arg.getName())
                ),
                visitTree(n.getBody(), context),
                JavaAccessDescriptor.from(n.getAccess()),
                n.isStatic(),
                n.isFinal()));
        return null;
    }


    public JavaNode visitClassDeclarationNode(ClassDeclarationNode n, JavaGenerationContext context) {

        JavaClass javaClass = new JavaClass(n.getName(), JavaAccessDescriptor.from(n.getAccess()), n.isStatic(), n.isFinal());
        context.getActualClass().getSubClasses().add(javaClass);
        JavaGenerationContext ctx = new JavaGenerationContext(javaClass, false);
        for(FunctionDeclarationNode node : n.getMethods()) visitFunctionDeclarationNode(node, ctx);
        for(ClassDeclarationNode node : n.getClasses()) visitClassDeclarationNode(node, ctx);
        // for(FunctionDeclarationNode node : n.getMethods()) visitFunctionDeclarationNode(node, ctx);

        return null;

    }


    public JavaValued.JavaConstruction visitClassConstruction(ClassConstructionNode n, JavaGenerationContext context) {
        JavaValued[] values = new JavaValued[n.getArgs().length];
        for(int i = 0; i < values.length; i++) values[i] = (JavaValued) visit(n.getArgs()[i], context);
        return new JavaValued.JavaConstruction((JavaIdentifier) visit(n.getType(), context), values);
    }


    public JavaValued.JavaFunctionCall visitFunctionCallNode(FunctionCallNode n, JavaGenerationContext context) {
        JavaValued[] values = new JavaValued[n.getArgs().length];
        for(int i = 0; i < values.length; i++) values[i] = (JavaValued) visit(n.getArgs()[i], context);
        return new JavaValued.JavaFunctionCall((JavaIdentifier) visit(n.getFunction(), context), values);
    }


    public JavaIdentifier visitIdentifierNode(IdentifierNode n, JavaGenerationContext context) {
        return n.getParent() == null ? new JavaIdentifier(n.getName())
                : new JavaIdentifier(n.getName(), (JavaValued) visit(n.getParent(), context));
    }

    @Override
    public String getExtension() {
        return ".java";
    }

    @Override
    public String getName() {
        return "java";
    }
}
