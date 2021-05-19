package com.github.nsc.de.shake.interpreter.values;

import com.github.nsc.de.shake.interpreter.Scope;
import com.github.nsc.de.shake.interpreter.UnformattedInterpreterError;
import com.github.nsc.de.shake.interpreter.Variable;
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode;
import org.reflections.Reflections;

import java.beans.Expression;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
         * This function will be executed when getting all child keys
         *
         * @return the keys of all children
         *
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        @Override
        public String[] getChildren() {

            // Get names of all classes in this package using reflections

            Reflections reflections = new Reflections(this.unknownName);
            Class<? extends Object>[] allClasses =
                    reflections.getSubTypesOf(Object.class).toArray(new Class[0]);
            String[] children = new String[allClasses.length];

            for(int i = 0; i < allClasses.length; i++)
                children[i] = allClasses[i].getSimpleName();

            return children;
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

    public static class JavaClass implements InterpreterValue {

        private final Class javaClass;

        public JavaClass(Class<?> javaClass) {
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
                        throw new UnformattedInterpreterError(e);
                    }
                }

            Method[] methods = this.javaClass.getMethods();
            for(int i = 0; i < methods.length; i++)
                if(methods[i].getName().equals(c) && Modifier.isStatic(methods[i].getModifiers()))
                    return Variable.finalOf(c, new JavaMethod(c, javaClass));

            Class[] classes = this.javaClass.getClasses();
            for(int i = 0; i < classes.length; i++)
                if(classes[i].getSimpleName().equals(c) && Modifier.isStatic(classes[i].getModifiers()))
                    return Variable.finalOf(c, new JavaClass(classes[i]));


            return Variable.finalOf(c, NullValue.NULL);

        }

        @Override
        public String[] getChildren() {
            List<String> children = new ArrayList<>();
            Field[] fields = this.javaClass.getFields();

            for(int i = 0; i < fields.length; i++)
                if(!containsString(children, fields[i].getName()))
                    children.add(fields[i].getName());

            for(int i = 0; i < javaClass.getMethods().length; i++)
                if(!containsString(children, fields[i].getName()))
                    children.add(fields[i].getName());

            for(int i = 0; i < javaClass.getClasses().length; i++)
                if(!containsString(children, fields[i].getName()))
                    children.add(fields[i].getName());

            return children.toArray(new String[0]);

        }

        private static final boolean containsString(List<String> l, String s) {
            return l.stream().filter(str -> str.equals(s)).findFirst().isPresent();
        }

        @Override
        public InterpreterValue newInstance(ClassConstructionNode node, Scope scope) {

            Object[] args = new Object[node.getArgs().length];

            for(int i = 0; i < node.getArgs().length; i++) {
                args[i] = scope.getInterpreter().visit(node.getArgs()[i], scope).toJava();
            }

            try {

                Constructor[] allConstructors = this.javaClass.getDeclaredConstructors();
                for (Constructor constructor : allConstructors) {

                    Class<?>[] pType  = constructor.getParameterTypes();

                    boolean matches = true;
                    int i;
                    for (i = 0; i < pType.length; i++) {

                        if(i >= args.length || !pType[i].isInstance(args[i])) {
                            matches = false;
                            break;
                        }
                    }

                    if(matches && i == args.length) {

                        return InterpreterValue.of(constructor.newInstance(args));

                    }

                }

                throw new UnformattedInterpreterError(String.format("No constructor of class %s for arguments %s found",
                        this.getJavaClass().getName(), Arrays.toString(Arrays.stream(args)
                                .map(arg -> arg.getClass()).toArray())));

            } catch (Exception e) {
                throw new UnformattedInterpreterError(e);
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
            return "java-class";
        }

        @Override
        public String toString() {
            return "JavaClass{" +
                    "javaClass=" + javaClass +
                    '}';
        }

        @Override
        public Object toJava() {
            return this.javaClass;
        }
    }

    public static class JavaMethod implements InterpreterValue {

        private final String name;
        private final Object object;

        public JavaMethod(String name, Object object) {
            super();
            this.name = name;
            this.object = object;
        }

        public JavaMethod(String name) {
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
                Expression s = new Expression(this.object, this.name, args);
                s.execute();
                return InterpreterValue.of(s.getValue());
            } catch (Exception e) {
                throw new UnformattedInterpreterError(e);
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
                    "name='" + name + '\'' +
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
                        return Variable.finalOf(c, new JavaMethod(c, object));
                }

                Class[] classes = this.type.getClasses();
                for(int i = 0; i < classes.length; i++)
                    if(classes[i].getSimpleName().equals(c) && !Modifier.isStatic(classes[i].getModifiers()))
                        throw new UnformattedInterpreterError("Can't get non-static inner classes");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return Variable.finalOf(c, NullValue.NULL);
        }

        @Override
        public String[] getChildren() {
            return new JavaClass(this.getClass()).getChildren();
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

        @Override
        public <T extends InterpreterValue> T to(Class<T> type) {
            return InterpreterValue.of(this.getObject()).to(type);
        }

        @Override
        public Object toJava() {
            return this.object;
        }
    }
}
