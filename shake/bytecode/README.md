# Shake Bytecode

This directory contains the bytecode interpreter for Shake.

**Important**: Sometimes we have to balance between performance and
readability. Normally readability wins. As some code in these packages
(especially in the packages `conventions` and `interpreter`) is executed 
during runtime of the interpreter, this directory is an exception to that 
rule. Especially functions that are executed very frequently should be as 
fast as possible. We will therefore prefer fast code over readable code
over readable code in here.