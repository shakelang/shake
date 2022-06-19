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

This package will now be available using the `pImport` function.

```javascript
const test = pImport(`io.github.shakelang.packagejs.test`);
```

You can also create your own package-js package host. This will be handled by the `package-js` library, but not contain
the packages of the global package-js host and not store the packages in the global package-js host.

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