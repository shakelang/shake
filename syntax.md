# Shake Syntax Definition

This is just a simple markdown definition for the Shake Programming Language Syntax.
It is not a full language tutorial and not meant to be a full language reference, but
rather for experienced users to get a feel and overview of the language.

## Info

Shake is an object-oriented, modern programming language. It is not a scripting language.
Shake has a java-like package system and a simple, yet powerful, c-style, syntax.

```shake
package com.shakelang.shake
```

## Function declarations

Function declarations are written in a c-style syntax.

```shake
void main() {
  ...
}
```

Functions can have the following attributes:

- `public`: The function is accessible from outside the package.
- `private`: The function is only accessible from inside the file
- `protected`: The function is accessible from inside the package, inside the file and inside of subclasses.
- `static`: Only available for functions in classes, enums and interfaces. Static functions do not need an instance to
  be called.
- `final`: The function can not be overridden.
- `abstract`: The function provides no implementation and must be implemented in a subclass.
- `override`: The function overrides a function in a superclass.
- `native`: The function is implemented in the compiler.
- `synchronized`: The function is synchronized.

Functions can also have parameters.

```shake
void main(int arg0) {
  ...
}
```

There are also default parameters.

```shake
void main(int arg0 = 0) {
  ...
}
```

## Variables / Fields

Variables are written in a c-style syntax. They are declared with the type of value and the name of the variable.
Declarations can also directly get a value assigned.

```shake
int a
int b = 0
```

Fields can have the following attributes:

- `public`: The field is accessible from outside the package.
- `private`: The field is only accessible from inside the file.
- `protected`: The field is accessible from inside the package, inside the file and inside of subclasses.
- `static`: Only available for fields in classes, enums and interfaces. Static fields do not need an instance to be
  accessed.
- `final`: The field can not be overridden.
- `const`: The field can not be changed.
- `abstract`: The field provides no implementation and must be implemented in a subclass.
- `override`: The field overrides a field in a superclass.
- `native`: The field is implemented in the compiler.

## Classes

Classes are created using the `class` keyword followed by the name of the class.

```shake
class MyClass {
  ...
}
```

They are able to extend other classes and implement interfaces.

```shake
class MyClass3 extends MySuperClass implements MyInterface {
  ...
}
```

A constructor is created using the `constructor` keyword.

```shake
class MyClass {
  constructor() {
    ...
  }
}
```

Constructors can have parameters.

```shake
class MyClass {
  constructor(int arg0) {
    ...
  }
}
```

Constructors can also have a name.

```shake
class MyClass {
  constructor MyConstructor(int arg0) {
    ...
  }
}
```

Classes can have fields and functions.

```shake
class MyClass {
  int a
  int b = 0
  void main() {
    ...
  }
}
```

## Interfaces

Interfaces are created using the `interface` keyword followed by the name of the interface.

```shake
interface MyInterface {
  ...
}
```

Interfaces can have fields and functions.

```shake
interface MyInterface {
  int a
  int b = 0
  void main() {
    ...
  }
}
```

Interfaces can also have methods and fields.
Interface fields must not have values and must not be final. Methods must not be final too.

```shake
interface MyInterface {
  int a
  int b = 0
  void main() {
    ...
  }
  void main2() {
    ...
  }
}
```

## Enums

Enums are created using the `enum` keyword followed by the name of the enum.

```shake
enum MyEnum {
  ...
}
```

Enum fields are created at the start of the enum. Then after the last field the regular class declaration starts
seperated by a semicolon. (If you do not need it the semicolon is optional.)

```shake
enum MyEnum {
  A,
  B,
  C
}

enum MyEnum2 {
  A,
  B,
  C;
  void main() {
    ...
  }
}

```

Enums can't have a superclass. They can only implement interfaces.
They can declare constructors, methods and fields.

```shake
enum MyEnum {
  A("a"),
  B("b"),
  C("c");
  String name;
  constructor(String name) {
    this.name = name;
  }
  void main() {
    ...
  }
}
```

## Objects

Objects are created using the `object` keyword followed by the name of the object.

```shake
object MyObject {
  ...
}
```

They are basically the same as classes, but they are directly created.

## Code

### Comments

#### Line Comments

Line comments are created using the `//` symbol.

```shake
// This is a comment
```

#### Block Comments

Block comments are created using the `/*` and `*/` symbols.

```shake
/*
  This is a comment
*/
```

### Loops

#### For Loops

`for` loops are created using the `for` keyword. They contain three parts:

- declaration of the counter variable
- condition for the loop to run
- increment of the counter variable

```shake
for(int i = 0; i < 10; i++) {
  ...
}
```

#### While Loops

`while` loops are created using the `while` keyword. They contain a condition for the loop to run.

```shake
while(true) {
  ...
}
```

#### Do-While Loops

`do-while` loops are created using the `do` keyword. They contain a condition for the loop to run.

```shake
do {
  ...
} while(true)
```

### If Statements

`if` statements are created using the `if` keyword. They contain a condition for the statement to run.

```shake
if(true) {
  ...
}
```

They can also contain an else statement.

```shake
if(true) {
  ...
} else {
  ...
}
```

### Local Variables

Local variables are declared with the type of value and the name of the variable. They are only available inside the
block they are declared in.

```shake
{
  int a
}
```

Local variables can also have a value assigned.

```shake
{
  int a = 0
}
```

### Assignments

Assignments are created using the `=` symbol.

```shake
a = 0
```

There are also `+=`, `-=`, `*=`, `/=`, `%=` and `**=` assignments.

### Expressions

### Arithmetic Expressions

#### Addition

`+` is used to add two values.

```shake
a + b
```

#### Subtraction

`-` is used to subtract two values.

```shake
a - b
```

#### Multiplication

`*` is used to multiply two values.

```shake
a * b
```

#### Division

`/` is used to divide two values.

```shake
a / b
```

#### Modulo

`%` is used to get the remainder of two values.

```shake
a % b
```

#### Exponentiation

`**` is used to get the power of two values.

```shake
a ** b
```

### Comparison Expressions

#### Equality

`==` is used to compare two values.

```shake
a == b
```

#### Inequality

`!=` is used to compare two values.

```shake
a != b
```

#### Greater Than

`>` is used to compare two values.

```shake
a > b
```

#### Less Than

`<` is used to compare two values.

```shake
a < b
```

#### Greater Than or Equal To

`>=` is used to compare two values.

```shake
a >= b
```

#### Less Than or Equal To

`<=` is used to compare two values.

```shake
a <= b
```

### Logical Expressions

#### Logical And

`&&` is used to combine two values.

```shake
a && b
```

#### Logical Or

`||` is used to combine two values.

```shake
a || b
```

#### Logical Not

`!` is used to invert a value.

```shake
!a
```

#### Logical Xor

`^` is used to combine two values.

```shake
a ^ b
```

### Bitwise Expressions

#### Bitwise And

`&` is used to combine two values.

```shake
a & b
```

#### Bitwise Or

`|` is used to combine two values.

```shake
a | b
```

#### Bitwise Xor

`^` is used to combine two values.

```shake
a ^ b
```

### Unary Expressions

#### Unary Plus

`+` is used to get the value of a value.

```shake
+a
```

#### Unary Minus

`-` is used to get the value of a value.

```shake
-a
```

### Other Expressions

#### Parenthesis

`(` and `)` are used to group expressions.

```shake
(a + b)
```

#### Function Call

`()` is used to call a function.

```shake
a()
```