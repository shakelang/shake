package com.github.nsc.de.shake.interpreter.values;

import com.github.nsc.de.shake.interpreter.Scope;
import com.github.nsc.de.shake.interpreter.Variable;
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode;

import java.beans.Statement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Java implements InterpreterValue {

    /**
     * This function will be executed when getting a child (variable.child)
     *
     * @param c the child to get
     * @return the child variable
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public Variable getChild(String c) {
        return Variable.finalOf(c, new JavaUnknown(c));
    }

    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     *
     * @return the name of the {@link InterpreterValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String getName() {
        return "java";
    }

    public static class JavaUnknown implements InterpreterValue {

        private final String unknownName;

        private JavaUnknown(String unknownName) {
            this.unknownName = unknownName;
        }

        public String getUnknownName() {
            return unknownName;
        }

        /**
         * This function will be executed when getting a child (variable.child)
         *
         * @param c the child to get
         * @return the child variable
         *
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        @Override
        public Variable getChild(String c) {
            String name = this.unknownName + "." + c;
            try {
                return Variable.finalOf(c, new JavaClass(Class.forName(name)));
            } catch (ClassNotFoundException e) {
                return Variable.finalOf(c, new JavaUnknown(name));
            }
        }

        /**
         * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
         *
         * @return the name of the {@link InterpreterValue}
         *
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        @Override
        public String getName() {
            return "java-unknown";
        }

        @Override
        public String toString() {
            return "JavaUnknown{" +
                    "unknownName='" + unknownName + '\'' +
                    '}';
        }
    }

    private static class JavaClass implements InterpreterValue {

        private final Class javaClass;

        private JavaClass(Class<?> javaClass) {
            this.javaClass = javaClass;
        }

        public Class getJavaClass() {
            return javaClass;
        }

        /**
         * This function will be executed when getting a child (variable.child)
         *
         * @param c the child to get
         * @return the child variable
         *
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        @Override
        public Variable getChild(String c) {

            Field[] fields = this.javaClass.getFields();
            for(int i = 0; i < fields.length; i++)
                if(fields[i].getName().equals(c) && Modifier.isStatic(fields[i].getModifiers())) {
                    try {
                        return Variable.finalOf(c, new JavaValue(fields[i].getType(), fields[i].get(null)));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new Error(e);
                    }
                }

            Method[] methods = this.javaClass.getMethods();
            for(int i = 0; i < methods.length; i++)
                if(methods[i].getName().equals(c) && Modifier.isStatic(methods[i].getModifiers()))
                    return Variable.finalOf(c, new JavaMethod(javaClass, c));

            Class[] classes = this.javaClass.getClasses();
            for(int i = 0; i < classes.length; i++)
                if(classes[i].getSimpleName().equals(c) && Modifier.isStatic(classes[i].getModifiers()))
                    return Variable.finalOf(c, new JavaClass(classes[i]));


            return Variable.finalOf(c, NullValue.NULL);

        }

        /**
         * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
         *
         * @return the name of the {@link InterpreterValue}
         *
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        @Override
        public String getName() {
            return "java-class";
        }

        @Override
        public String toString() {
            return "JavaClass{" +
                    "javaClass=" + javaClass +
                    '}';
        }
    }

    public static class JavaMethod implements InterpreterValue {

        private final Class type;
        private final String name;
        private final Object object;

        public JavaMethod(Class type, String name, Object object) {
            super();
            this.type = type;
            this.name = name;
            this.object = object;
        }

        public JavaMethod(Class type, String name) {
            this.type = type;
            this.name = name;
            this.object = null;
        }

        @Override
        public InterpreterValue invoke(FunctionCallNode node, Scope scope) {

            Object[] args = new Object[node.getArgs().length];

            for(int i = 0; i < node.getArgs().length; i++) {
                args[i] = scope.getInterpreter().visit(node.getArgs()[i], scope).toJava();
            }

            try {
                Statement s = new Statement(this.object, this.name, args);
                s.execute();
                return NullValue.NULL;
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        /**
         * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
         *
         * @return the name of the {@link InterpreterValue}
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        @Override
        public String getName() {
            return "java-method";
        }

        @Override
        public String toString() {
            return "JavaMethod{" +
                    "type=" + type +
                    ", name='" + name + '\'' +
                    ", object=" + object +
                    '}';
        }
    }

    public static class JavaValue<T> implements InterpreterValue {

        private final Class<T> type;
        private final T object;

        public JavaValue(Class<T> type, T object) {
            this.object = object;
            this.type = type;
        }

        public T getObject() {
            return object;
        }

        public Class<T> getType() {
            return type;
        }

        /**
         * This function will be executed when getting a child (variable.child)
         *
         * @param c the child to get
         * @return the child variable
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        @Override
        public Variable getChild(String c) {

            try {
                Field[] fields = this.type.getFields();
                for(int i = 0; i < fields.length; i++)
                    if(fields[i].getName().equals(c) && !Modifier.isStatic(fields[i].getModifiers()))
                        return Variable.finalOf(c, new JavaValue(fields[i].getType(), fields[i].get(this.object)));


                Method[] methods = this.type.getMethods();
                for(int i = 0; i < methods.length; i++) {
                    if (methods[i].getName().equals(c) && !Modifier.isStatic(methods[i].getModifiers()))
                        return Variable.finalOf(c, new JavaMethod(type, c, object));
                }

                Class[] classes = this.type.getClasses();
                for(int i = 0; i < classes.length; i++)
                    if(classes[i].getSimpleName().equals(c) && !Modifier.isStatic(classes[i].getModifiers()))
                        throw new Error("Can't get non-static inner classes");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return Variable.finalOf(c, NullValue.NULL);
        }

        /**
         * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
         *
         * @return the name of the {@link InterpreterValue}
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        @Override
        public String getName() {
            return "java-value";
        }

        @Override
        public String toString() {
            return "JavaValue{" +
                    "value=" + object +
                    '}';
        }
    }
}
