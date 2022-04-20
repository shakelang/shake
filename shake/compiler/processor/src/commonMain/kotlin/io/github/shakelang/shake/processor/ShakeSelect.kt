package io.github.shakelang.shake.processor

import io.github.shakelang.shake.processor.program.ShakeConstructor
import io.github.shakelang.shake.processor.program.ShakeType
import io.github.shakelang.shake.processor.program.code.ShakeInvokable

object ShakeSelect {

    fun selectFunction(
        functions: List<ShakeInvokable>,
        parameters: List<ShakeType>,
    ): ShakeInvokable? = functions.filter {
            it.parameters.size == parameters.size && // TODO default parameters
                    it.parameters.zip(parameters).all { (parameter, argument) ->
                        parameter.type.compatibleTo(argument)
                    }
        }.minByOrNull {
            it.parameters.zip(parameters).sumOf { (parameter, argument) ->
                parameter.type.compatibilityDistance(argument)
            }
        }

    fun selectConstructor(
        constructors: List<ShakeConstructor>,
        parameters: List<ShakeType>,
    ): ShakeConstructor? = constructors.filter {
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