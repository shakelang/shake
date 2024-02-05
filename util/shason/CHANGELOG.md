# com.shakelang.shake.util:shason

## Description

A json parser implemented in kotlin (mpp)

## Versions

### 0.2.0

* Update to new package structure

### 0.2.1

* Update dependencies

### 0.2.2

* Update dependencies

### 0.2.3

* Update dependencies

### 0.2.4

* Generation of JsonElement class fixed

### 0.3.0

* Introduce JsonElement#toJson[PrimitiveType]

* Introduce JsonElement#isJson[PrimitiveType]

* Make int compatible to double

### 0.3.1

* Update com.shakelang.shake.util:common-io to 0.3.0

* Update com.shakelang.shake.util:parseutils to 0.2.4

### 0.3.2

* Update com.shakelang.shake.util:primitives to 0.4.0

* Update com.shakelang.shake.util:common-io to 0.3.1

* Update com.shakelang.shake.util:parseutils to 0.2.5

### 0.4.0

* Move `com.shakelang.shake.util` to `com.shakelang.util`

* Update com.shakelang.util:colorlib to 0.3.0

* Update com.shakelang.util:common-io to 0.4.0

* Update com.shakelang.util:environment to 0.3.0

* Update com.shakelang.util:parseutils to 0.3.0

* Update com.shakelang.util:primitives to 0.5.0

### 0.4.1

* Fix: Don't escape `'` character in json strings

* Make com.shakelang.util.shason.json from typealias to val

* Update com.shakelang.util:parseutils to 0.4.0

### 0.4.2

* Fix NullPointerException in some cases on stringify of null

* Generate of float/double 10 should be generated to 10 instead of 10.0

* Bugfix: Parsed "null" as string. Now it is parsed into a JsonNullElement