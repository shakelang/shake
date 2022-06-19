# package-js library

`package-js` is a lightweight tool develeped by the `shake` programming language team as a library to represent a 
java-style package storage in javascript.

## Usage

```javascript
import { pImport, add as addPackage } from 'package-js';
```

### Registering a package

You can register a package by calling the `add` function.

```javascript
addPackage({
    "io.github.shakelang.packagejs.test" : it => it.require(`${__dirname}/test.js`)
});
```


### Importing a package

This package will now be available using the `pImport` function.

```javascript
const test = pImport(`io.github.shakelang.packagejs.test`);
```

You can also create your own package-js package host. This will be handled by the `package-js` library, but not contain
the packages of the global package-js host and not store the packages in the global package-js host.

### Own package-js host

```javascript
import { createPackageSystem } from 'package-js';

const packages = createPackageSystem({
    "io.github.shakelang.packagejs.test" : it => it.require(`${__dirname}/test.js`)
});
```

You can of course also use the `pImport`  and `add` functions from the global package-js host.

```javascript
const { pImport, add: addPackage } = packages;
```

### Inline package-js installation

There is also the possibility to add a inline package-js instance to your package. This is useful if you want to use 
the `package-js` library in your package without the need to install the `package-js` dependency. One advantage of this
is that package-js will use the installed `package-js` version instead of the inline version if it can find it. Also 
with the inline version you can import packages from other inline `package-js` instances. The compressed version of
the inline package-js instance can be easily added to your javascript file and is only about 1000 characters long.

**Keep in mind that the inline version is also licensed under the BSD-2 Clause license. YOU HAVE TO INCLUDE A NOTE (AS 
A COMMENT OR IN THE LICENSE FILE) THAT YOU ARE USING PACKAGE-JS INLINE LIBRARY**