package com.shakelang.shake.shakespeare.builder

import com.shakelang.shake.shakespeare.spec.code.*

class VariableAssignmentBuilder
internal constructor(init: VariableAssignmentBuilder.() -> Unit) :
    VariableAssignmentSpec.VariableAssignmentSpecBuilder(),
    Builder {
    init {
        init()
    }
}

class VariableAdditionSpecBuilder
internal constructor(init: VariableAdditionSpecBuilder.() -> Unit) :
    VariableAdditionAssignmentSpec.VariableAdditionAssignmentSpecBuilder(),
    Builder {
    init {
        init()
    }
}

class VariableSubtractionSpecBuilder
internal constructor(init: VariableSubtractionSpecBuilder.() -> Unit) :
    VariableSubtractionAssignmentSpec.VariableSubtractionAssignmentSpecBuilder(),
    Builder {
    init {
        init()
    }
}

class VariableMultiplicationSpecBuilder
internal constructor(init: VariableMultiplicationSpecBuilder.() -> Unit) :
    VariableMultiplicationAssignmentSpec.VariableMultiplicationAssignmentSpecBuilder(),
    Builder {
    init {
        init()
    }
}

class VariableDivisionSpecBuilder
internal constructor(init: VariableDivisionSpecBuilder.() -> Unit) :
    VariableDivisionAssignmentSpec.VariableDivisionAssignmentSpecBuilder(),
    Builder {
    init {
        init()
    }
}

class VariableModuloSpecBuilder
internal constructor(init: VariableModuloSpecBuilder.() -> Unit) :
    VariableModuloAssignmentSpec.VariableModuloAssignmentSpecBuilder(),
    Builder {
    init {
        init()
    }
}

class VariableIncrementBeforeSpecBuilder
internal constructor(init: VariableIncrementBeforeSpecBuilder.() -> Unit) :
    VariableIncrementBeforeSpec.VariableIncrementBeforeSpecBuilder(),
    Builder {
    init {
        init()
    }
}

class VariableIncrementAfterSpecBuilder
internal constructor(init: VariableIncrementAfterSpecBuilder.() -> Unit) :
    VariableIncrementAfterSpec.VariableIncrementAfterSpecBuilder(),
    Builder {
    init {
        init()
    }
}

class VariableDecrementBeforeSpecBuilder
internal constructor(init: VariableDecrementBeforeSpecBuilder.() -> Unit) :
    VariableDecrementBeforeSpec.VariableDecrementBeforeSpecBuilder(),
    Builder {
    init {
        init()
    }
}

class VariableDecrementAfterSpecBuilder
internal constructor(init: VariableDecrementAfterSpecBuilder.() -> Unit) :
    VariableDecrementAfterSpec.VariableDecrementAfterSpecBuilder(),
    Builder {
    init {
        init()
    }
}

class FunctionCallBuilder
internal constructor(init: FunctionCallBuilder.() -> Unit) : FunctionCallSpec.FunctionCallSpecBuilder(), Builder {
    init {
        init()
    }
}
