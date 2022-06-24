# Shake Class Map Definition

## File format
| Key                    | Description                                         | Byte Size    |
|------------------------|-----------------------------------------------------|--------------|
| magic                  | Magic number (Value: `0x3082d75f`)                  | 4 bytes      |
| version                | Version                                             | 4 bytes      |
| cp_size                | Class path size                                     | 4 bytes      |
| constants[]            | Constant pool (Contains Constant objects)           | ? bytes      |
| pkg_amount             | Amount of packages                                  | 4 bytes      |
| package[]              | Packages (Contains Package Objects)                 | ? bytes      |
| class_amount           | Amount of classes                                   | 4 bytes      |
| class[]                | Classes (Contains Class Objects)                    | ? bytes      |
| method_amount          | Amount of methods                                   | 4 bytes      |
| method[]               | Methods (Contains Method Objects)                   | ? bytes      |
| constructor_amount     | Amount of constructors                              | 4 bytes      |
| constructor[]          | Constructors (Contains Constructor Objects)         | ? bytes      |
| field_amount           | Amount of fields                                    | 4 bytes      |
| field[]                | Fields (Contains Field Objects)                     | ? bytes      |
| project_package_amount | Amount of project packages                          | 4 bytes      |
| project_packages[]     | Project packages (Contains indexes of root packages | 4 bytes each |
| project_class_amount   | Amount of project classes                           | 4 bytes      |
| project_classes[]      | Project classes (Contains indexes of root classes   | 4 bytes each |
| project_method_amount  | Amount of project methods                           | 4 bytes      |
| project_methods[]      | Project methods (Contains indexes of root methods   | 4 bytes each |
| project_field_amount   | Amount of project fields                            | 4 bytes      |
| project_fields[]       | Project fields (Contains indexes of root fields     | 4 bytes each |



## Constants (in the constant pool)

### UTF-8 string
| Key    | Description                    | Byte Size |
|--------|--------------------------------|-----------|
| type   | Constant type (Value: `0x01`)  | 1 byte    |
| length | Length of the string           | 4 bytes   |
| value  | UTF-8 string                   | ? bytes   |

## Packages
| Key                  | Description                                  | Byte Size    |
|----------------------|----------------------------------------------|--------------|
| name                 | Package name (index to constant pool)        | 4 bytes      |
| package_amount       | Amount of packages in this package           | 4 bytes      |
| package_references[] | References to other packages in this package | 4 bytes each |
| class_amount         | Amount of classes in the package             | 4 bytes      |
| class_references[]   | References to classes in the package         | 4 bytes each |
| function_amount      | Amount of functions in the package           | 4 bytes      |
| function_references  | References to functions in the package       | 4 bytes each |
| field_amount         | Amount of fields in the package              | 4 bytes      |
| field_references[]   | References to fields in the package          | 4 bytes each |

## Classes
| Key                    | Description                               | Byte Size    |
|------------------------|-------------------------------------------|--------------|
| name                   | Class name (index to constant pool)       | 4 bytes      |
| attributes             | Attributes of the class                   | 2 bytes      |
| super_class            | Super class name (index to constant pool) | 4 bytes      |
| interfaces_amount      | Amount of interfaces in the class         | 4 bytes      |
| interfaces_references  | References to interfaces in the class     | 4 bytes each |
| subclass_amount        | Amount of subclasses of this class        | 4 bytes      |
| subclass_references    | References to subclasses of this class    | 4 bytes each |
| method_amount          | Amount of methods in this class           | 4 bytes      |
| method_references      | References to methods in this class       | 4 bytes each |
| constructor_amount     | Amount of constructors in this class      | 4 bytes      |
| constructor_references | References to constructors in this class  | 4 bytes each |
| field_amount           | Amount of fields in this class            | 4 bytes      |
| field_references       | References to fields in this class        | 4 bytes each |

## Methods
| Key               | Description                                   | Byte Size    |
|-------------------|-----------------------------------------------|--------------|
| name              | Method name (index to constant pool)          | 4 bytes      |
| attributes        | Attributes of the method                      | 2 bytes      |
| return_type       | Return type (index to constant pool)          | 4 bytes      |
| parameter_amount  | Amount of parameters in this method           | 4 bytes      |
| parameter_names[] | Parameter names (index to constant pool)      | 4 bytes each |
| parameter_types[] | Parameter types (index to constant pool)      | 4 bytes each |

## Constructors
| Key               | Description                               | Byte Size    |
|-------------------|-------------------------------------------|--------------|
| name              | Constructor name (index to constant pool) | 4 bytes      |
| attributes        | Attributes of the constructor             | 2 bytes      |
| parameter_amount  | Amount of parameters in this constructor  | 4 bytes      |
| parameter_names[] | Parameter names (index to constant pool)  | 4 bytes each |
| parameter_types[] | Parameter types (index to constant pool)  | 4 bytes each |

## Fields
| Key                  | Description                         | Byte Size |
|----------------------|-------------------------------------|-----------|
| name                 | Field name (index to constant pool) | 4 bytes   |
| attributes           | Attributes of the field             | 2 bytes   |
| type                 | Field type (index to constant pool) | 4 bytes   |
