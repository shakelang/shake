const it = require('./index.js');

const pkg = it.packageSystem({
    packages: {
        "io.github.shakelang.packagejs.test" : it.require(`${__dirname}/test.js`)
    }
});

console.log("Example package loaded");

const testImport = pkg.import("io.github.shakelang.packagejs.test");

