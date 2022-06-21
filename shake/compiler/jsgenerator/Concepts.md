# Generation concepts to generate javascript code from shake

## Packages

### japack
The library `japack` is lightweight tool to represent a package storage in javascript. Packages must be registered
in the package-system before they can be used. Then you can just import the package from the library. 
[More info about japack](https://github.com/shakelang/package-js)

### files
Every package is generated into it's javascript file. The file is named `[qualified-package-name].js`. Subpackages are 
not put into folders. The contents of each package will only be loaded if the package is imported, to avoid loading
unnecessary files.

## JavaScript code generation

The shake ast is converted into javascript code. We have to transform some stuff because some features are not supported
in javascript. We create a new JavaScript AST and then convert this JavaScript AST into a string.

### Variables
