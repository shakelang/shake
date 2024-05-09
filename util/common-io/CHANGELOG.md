# com.shakelang.shake.util:common-io

## Description

Utility for working with colors in console applications (Kotlin Multiplatform)

## Versions

### 0.2.0

* Update to new package structure

### 0.2.1

* writeUTF8() should write an int as size (as readUTF8 also reads an int)

### 0.2.2

* writeUTF8() should write an ushort as size (readUTF8 as well)

### 0.3.0

* Introduce BufferedInputStream#peek() (As it can be helpfull and is no problem to do a peek operation on a buffered stream)

* Introduce DumpAble interface (for objects that have the ability to be dumped into binary)

### 0.3.1

* Update com.shakelang.shake.util:primitives to 0.4.0

### 0.4.0

* Move `com.shakelang.shake.util` to `com.shakelang.util`

* Update com.shakelang.util:primitives to 0.5.0

### 0.5.0

* Introduce generic Stream api

### 0.6.0

* Enhance builtin stream (datastructure) api

### 0.6.1

* Update com.shakelang.util:primitives to 0.6.0