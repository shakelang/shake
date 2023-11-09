package io.github.shakelang.shake.processor

import io.github.shakelang.shake.processor.program.types.ShakeConstructor
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.ShakeInvokable

object ShakeSelect {

    fun <I : ShakeInvokable, T : ShakeType> selectFunction(
        functions: List<I>,
        parameters: List<T>,
    ): I? = functions.filter {
        it.parameters.size == parameters.size && // TODO default parameters
                it.parameters.zip(parameters).all { (parameter, argument) ->
                    parameter.type.compatibleTo(argument)
                }
    }.minByOrNull {
        it.parameters.zip(parameters).sumOf { (parameter, argument) ->
            parameter.type.compatibilityDistance(argument)
        }
    }

    fun <C : ShakeConstructor, T : ShakeType> selectConstructor(
        constructors: List<C>,
        parameters: List<T>,
    ): C? = constructors.filter {
        it.parameters.size == parameters.size && // TODO default parameters
                it.parameters.zip(parameters).all { (parameter, argument) ->
                    parameter.type.compatibleTo(argument)
                }
    }.minByOrNull {
        it.parameters.zip(parameters).sumOf { (parameter, argument) ->
            parameter.type.compatibilityDistance(argument)
        }
    }

}