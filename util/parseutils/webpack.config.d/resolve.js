const path = require("path");

config.resolve.alias = {
    "crypto": false,
    "path": require.resolve("path-browserify"),
    "fs": false,
}
//
// config.resolve.modules = [
//     path.resolve("src/commonMain/resources"),
//     path.resolve("src/commonTest/resources"),
//     path.resolve("src/jsMain/resources"),
//     path.resolve("src/jsTest/resources"),
//     path.resolve("src/jsMain/kotlin"),
//     path.resolve("src/jsTest/kotlin")
// ]