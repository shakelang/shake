package com.shakelang.shake.processor.program.creation.code

import com.shakelang.shake.processor.program.creation.code.statements.CreationShakeStatement
import com.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import com.shakelang.shake.processor.program.types.code.ShakeValuedStatement

interface CreationShakeValuedStatement :
    ShakeValuedStatement,
    CreationShakeStatement,
    CreationShakeValue
