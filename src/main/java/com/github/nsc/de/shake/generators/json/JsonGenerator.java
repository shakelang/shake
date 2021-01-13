package com.github.nsc.de.shake.generators.json;

import com.github.nsc.de.shake.generators.Generator;
import com.github.nsc.de.shake.parser.node.*;
import com.github.nsc.de.shake.parser.node.expression.*;
import com.github.nsc.de.shake.parser.node.factor.DoubleNode;
import com.github.nsc.de.shake.parser.node.factor.IntegerNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionArgumentNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.shake.parser.node.logical.LogicalAndNode;
import com.github.nsc.de.shake.parser.node.logical.LogicalBiggerEqualsNode;
import com.github.nsc.de.shake.parser.node.logical.LogicalBiggerNode;
import com.github.nsc.de.shake.parser.node.logical.LogicalEqEqualsNode;
import com.github.nsc.de.shake.parser.node.logical.LogicalFalseNode;
import com.github.nsc.de.shake.parser.node.logical.LogicalOrNode;
import com.github.nsc.de.shake.parser.node.logical.LogicalSmallerEqualsNode;
import com.github.nsc.de.shake.parser.node.logical.LogicalSmallerNode;
import com.github.nsc.de.shake.parser.node.logical.LogicalTrueNode;
import com.github.nsc.de.shake.parser.node.loops.DoWhileNode;
import com.github.nsc.de.shake.parser.node.loops.ForNode;
import com.github.nsc.de.shake.parser.node.loops.WhileNode;
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode;
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode;
import com.github.nsc.de.shake.parser.node.variables.*;
import org.json.JSONObject;
import org.json.JSONArray;

public class JsonGenerator extends Generator<Object> {

    public JSONArray visitTree(Tree t) {
        JSONArray array = new JSONArray();
        for (int i = 0; i < t.getChildren().length; i++) array.put(visit(t.getChildren()[i]));
        return array;
    }

    @Override
    public Double visitDoubleNode(DoubleNode n) {
        return n.getNumber();
    }

    @Override
    public Integer visitIntegerNode(IntegerNode n) {
        return n.getNumber();
    }

    public JSONObject visitAddNode(AddNode n) {
        return new JSONObject().put("type", "add").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitSubNode(SubNode n) {
        return new JSONObject().put("type", "sub").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitMulNode(MulNode n) {
        return new JSONObject().put("type", "mul").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitDivNode(DivNode n) {
        return new JSONObject().put("type", "div").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitModNode(ModNode n) {
        return new JSONObject().put("type", "mod").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitPowNode(PowNode n) {
        return new JSONObject().put("type", "pow").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitVariableDeclarationNode(VariableDeclarationNode n) {
        JSONObject obj = new JSONObject()
                .put("type", "variable_declaration")
                .put("name", n.getName())
                .put("access", n.getAccess().toString())
                .put("in_class", n.isInClass())
                .put("static", n.isStatic())
                .put("final", n.isFinal())
                .put("type", n.getType().getType().toString());
        if(n.getAssignment() != null) obj.put("assignment", visit(n.getAssignment()));
        return obj;
    }

    public JSONObject visitVariableAssignmentNode(VariableAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    public JSONObject visitVariableAddAssignmentNode(VariableAddAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_add_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    public JSONObject visitVariableSubAssignmentNode(VariableSubAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_sub_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    public JSONObject visitVariableMulAssignmentNode(VariableMulAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_mul_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    public JSONObject visitVariableDivAssignmentNode(VariableDivAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_div_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    public JSONObject visitVariableModAssignmentNode(VariableModAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_mod_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    public JSONObject visitVariablePowAssignmentNode(VariablePowAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_pow_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    public JSONObject visitVariableIncreaseNode(VariableIncreaseNode n) {
        return new JSONObject().put("type", "variable_incr").put("variable", visit(n.getVariable()));
    }

    public JSONObject visitVariableDecreaseNode(VariableDecreaseNode n) {
        return new JSONObject().put("type", "variable_decr").put("variable", visit(n.getVariable()));
    }

    public JSONObject visitVariableUsageNode(VariableUsageNode n) {
        return new JSONObject().put("type", "variable_usage").put("variable", visit(n.getVariable()));
    }

    public JSONObject visitEqEqualsNode(LogicalEqEqualsNode n) {
        return new JSONObject().put("type", "eq_eq").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitBiggerEqualsNode(LogicalBiggerEqualsNode n) {
        return new JSONObject().put("type", "bigger_eq").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitSmallerEqualsNode(LogicalSmallerEqualsNode n) {
        return new JSONObject().put("type", "smaller_eq").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitBiggerNode(LogicalBiggerNode n) {
        return new JSONObject().put("type", "bigger").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitSmallerNode(LogicalSmallerNode n) {
        return new JSONObject().put("type", "smaller").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitLogicalAndNode(LogicalAndNode n) {
        return new JSONObject().put("type", "logical_and").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitLogicalOrNode(LogicalOrNode n) {
        return new JSONObject().put("type", "logical_or").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    public JSONObject visitWhileNode(WhileNode n) {
        return new JSONObject().put("type", "while").put("condition", visit(n.getCondition())).put("body", visit(n.getBody()));
    }

    public JSONObject visitDoWhileNode(DoWhileNode n) {
        return new JSONObject().put("type", "do_while").put("condition", visit(n.getCondition())).put("body", visit(n.getBody()));
    }

    public JSONObject visitForNode(ForNode n) {
        return new JSONObject()
                .put("type", "for")
                .put("declaration", visit(n.getDeclaration()))
                .put("condition", visit(n.getCondition()))
                .put("round", visit(n.getRound()))
                .put("body", visit(n.getBody()));
    }

    public JSONObject visitIfNode(IfNode n) {
        return new JSONObject()
                .put("type", "if")
                .put("condition", visit(n.getCondition()))
                .put("body", visit(n.getBody()))
                .put("else_body", visit(n.getElseBody()));
    }

    public JSONObject visitFunctionDeclarationNode(FunctionDeclarationNode n) {

        JSONArray args = new JSONArray();
        for(FunctionArgumentNode arg : n.getArgs()) {
            args.put(new JSONObject().put("name", arg.getName()));
        }

        return new JSONObject()
                .put("type", "function_declaration")
                .put("name", n.getName())
                .put("access", n.getAccess().toString())
                .put("in_class", n.isInClass())
                .put("static", n.isStatic())
                .put("final", n.isFinal())
                .put("type", n.getType().getType().toString())
                .put("args", args)
                .put("body", visit(n.getBody()));

    }

    public JSONObject visitClassDeclarationNode(ClassDeclarationNode n) {

        JSONArray methods = new JSONArray();
        JSONArray classes = new JSONArray();
        JSONArray fields = new JSONArray();

        for(int i = 0; i < n.getMethods().length; i++) methods.put(this.visit(n.getMethods()[i]));
        for(int i = 0; i < n.getClasses().length; i++) classes.put(this.visit(n.getFields()[i]));
        for(int i = 0; i < n.getFields().length; i++) fields.put(this.visit(n.getFields()[i]));


        return new JSONObject().put("type", "class_declaration")
                .put("name", n.getName())
                .put("access", n.getAccess().toString())
                .put("in_class", n.isInClass())
                .put("static", n.isStatic())
                .put("final", n.isFinal())
                .put("methods", methods)
                .put("classes", classes)
                .put("fields", fields);
    }

    public JSONObject visitClassConstruction(ClassConstructionNode n) {

        JSONArray args = new JSONArray();
        for(ValuedNode arg : n.getArgs()) {
            args.put(visit(arg));
        }

        return new JSONObject()
                .put("type", "class_construction")
                .put("class", visit(n.getType()))
                .put("args", args);

    }

    public JSONObject visitFunctionCallNode(FunctionCallNode n) {

        JSONArray args = new JSONArray();
        for(ValuedNode arg : n.getArgs()) {
            args.put(visit(arg));
        }

        return new JSONObject()
                .put("type", "function_call")
                .put("function", visit(n.getFunction()))
                .put("args", args);

    }

    public JSONObject visitIdentifierNode(IdentifierNode n) {

        JSONObject obj = new JSONObject().put("type", "identifier");
        if(n.getParent() != null) obj.put("parent", visit(n.getParent()));
        return obj.put("name", n.getName());

    }

    @Override
    public Boolean visitLogicalTrueNode(LogicalTrueNode n) {
        return true;
    }

    @Override
    public Boolean visitLogicalFalseNode(LogicalFalseNode n) {
        return false;
    }

    @Override
    public String getExtension() {
        return ".json";
    }

    @Override
    public String getName() {
        return "json";
    }
}
