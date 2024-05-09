package com.shakelang.util.primitives.atomic

typealias UnaryOperation<I, O> = (I) -> O
typealias BinaryOperation<I1, I2, O> = (I1, I2) -> O
