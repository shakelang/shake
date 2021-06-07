package com.github.shakelang.shake.generators.json;

import com.github.shakelang.shake.generation.ShakeGenerator;
import com.github.shakelang.shake.parser.node.*;
import com.github.shakelang.shake.parser.node.expression.*;
import com.github.shakelang.shake.parser.node.factor.DoubleNode;
import com.github.shakelang.shake.parser.node.factor.IntegerNode;
import com.github.shakelang.shake.parser.node.functions.FunctionArgumentNode;
import com.github.shakelang.shake.parser.node.functions.FunctionCallNode;
import com.github.shakelang.shake.parser.node.functions.FunctionDeclarationNode;
import com.github.shakelang.shake.parser.node.logical.*;
import com.github.shakelang.shake.parser.node.loops.DoWhileNode;
import com.github.shakelang.shake.parser.node.loops.ForNode;
import com.github.shakelang.shake.parser.node.loops.WhileNode;
import com.github.shakelang.shake.parser.node.objects.ClassConstructionNode;
import com.github.shakelang.shake.parser.node.objects.ClassDeclarationNode;
import com.github.shakelang.shake.parser.node.variables.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonGenerator extends ShakeGenerator<JSONObject> {

    @Override
    public JSONObject visitTree(Tree t) {
        JSONArray array = new JSONArray();
        for (int i = 0; i < t.getChildren().length; i++) array.put(visit(t.getChildren()[i]));
        return new JSONObject().put("type", "tree").put("children", array);
    }

    @Override
    public JSONObject visitDoubleNode(DoubleNode n) {
        JSONObject obj =  new JSONObject().put("type", "double_value");
        if(n.getNumber() == ((int) n.getNumber())) obj.put("value", (int) n.getNumber());
        else obj.put("type", n.getNumber());
        return obj;
    }

    @Override
    public JSONObject visitIntegerNode(IntegerNode n) {
        return new JSONObject()
                .put("type", "integer_value")
                .put("value", n.getNumber());
    }

    @Override
    public JSONObject visitAddNode(AddNode n) {
        return new JSONObject().put("type", "add").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitSubNode(SubNode n) {
        return new JSONObject().put("type", "sub").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitMulNode(MulNode n) {
        return new JSONObject().put("type", "mul").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitDivNode(DivNode n) {
        return new JSONObject().put("type", "div").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitModNode(ModNode n) {
        return new JSONObject().put("type", "mod").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitPowNode(PowNode n) {
        return new JSONObject().put("type", "pow").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
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

    @Override
    public JSONObject visitVariableAssignmentNode(VariableAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    @Override
    public JSONObject visitVariableAddAssignmentNode(VariableAddAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_add_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    @Override
    public JSONObject visitVariableSubAssignmentNode(VariableSubAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_sub_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    @Override
    public JSONObject visitVariableMulAssignmentNode(VariableMulAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_mul_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    @Override
    public JSONObject visitVariableDivAssignmentNode(VariableDivAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_div_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    @Override
    public JSONObject visitVariableModAssignmentNode(VariableModAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_mod_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    @Override
    public JSONObject visitVariablePowAssignmentNode(VariablePowAssignmentNode n) {
        return new JSONObject()
                .put("type", "variable_pow_assignment")
                .put("variable", visit(n.getVariable()))
                .put("value", visit(n.getValue()));
    }

    @Override
    public JSONObject visitVariableIncreaseNode(VariableIncreaseNode n) {
        return new JSONObject().put("type", "variable_incr").put("variable", visit(n.getVariable()));
    }

    @Override
    public JSONObject visitVariableDecreaseNode(VariableDecreaseNode n) {
        return new JSONObject().put("type", "variable_decr").put("variable", visit(n.getVariable()));
    }

    @Override
    public JSONObject visitVariableUsageNode(VariableUsageNode n) {
        return new JSONObject().put("type", "variable_usage").put("variable", visit(n.getVariable()));
    }

    @Override
    public JSONObject visitEqEqualsNode(LogicalEqEqualsNode n) {
        return new JSONObject().put("type", "eq_eq").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitBiggerEqualsNode(LogicalBiggerEqualsNode n) {
        return new JSONObject().put("type", "bigger_eq").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitSmallerEqualsNode(LogicalSmallerEqualsNode n) {
        return new JSONObject().put("type", "smaller_eq").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitBiggerNode(LogicalBiggerNode n) {
        return new JSONObject().put("type", "bigger").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitSmallerNode(LogicalSmallerNode n) {
        return new JSONObject().put("type", "smaller").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitLogicalAndNode(LogicalAndNode n) {
        return new JSONObject().put("type", "logical_and").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitLogicalOrNode(LogicalOrNode n) {
        return new JSONObject().put("type", "logical_or").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitLogicalXOrNode(LogicalXOrNode n) {
        return new JSONObject().put("type", "logical_xor").put("left", visit(n.getLeft())).put("right", visit(n.getRight()));
    }

    @Override
    public JSONObject visitWhileNode(WhileNode n) {
        return new JSONObject().put("type", "while").put("condition", visit(n.getCondition())).put("body", visit(n.getBody()));
    }

    @Override
    public JSONObject visitDoWhileNode(DoWhileNode n) {
        return new JSONObject().put("type", "do_while").put("condition", visit(n.getCondition())).put("body", visit(n.getBody()));
    }

    @Override
    public JSONObject visitForNode(ForNode n) {
        return new JSONObject()
                .put("type", "for")
                .put("declaration", visit(n.getDeclaration()))
                .put("condition", visit(n.getCondition()))
                .put("round", visit(n.getRound()))
                .put("body", visit(n.getBody()));
    }

    @Override
    public JSONObject visitIfNode(IfNode n) {
        return new JSONObject()
                .put("type", "if")
                .put("condition", visit(n.getCondition()))
                .put("body", visit(n.getBody()))
                .put("else_body", visit(n.getElseBody()));
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public JSONObject visitIdentifierNode(IdentifierNode n) {

        JSONObject obj = new JSONObject().put("type", "identifier");
        if(n.getParent() != null) obj.put("parent", visit(n.getParent()));
        return obj.put("name", n.getName());

    }

    @Override
    public JSONObject visitLogicalTrueNode(LogicalTrueNode n) {
        return new JSONObject().put("type", "logical_true");
    }

    @Override
    public JSONObject visitLogicalFalseNode(LogicalFalseNode n) {
        return new JSONObject().put("type", "logical_false");
    }

    @Override
    public JSONObject visitImportNode(ImportNode n) {

        return new JSONObject().put("type", "import").put("import", n.getImport());

    }

    @Override
    public JSONObject visitCastNode(CastNode n) {
        return new JSONObject().put("type", "cast")
                .put("target", visitCastTarget(n.getCastTarget()))
                .put("value", n.getValue());
    }

    public JSONObject visitCastTarget(CastNode.CastTarget target) {
        JSONObject result = new JSONObject().put("type", target.getType().toString());
        if(target.getType() == CastNode.CastTarget.CastTargetType.OBJECT)
            result.put("subtype", visit(target.getSubtype()));
        return result;
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
