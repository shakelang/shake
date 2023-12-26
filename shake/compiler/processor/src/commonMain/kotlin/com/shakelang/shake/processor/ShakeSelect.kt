package com.shakelang.shake.processor

import com.shakelang.shake.processor.program.types.ShakeConstructor
import com.shakelang.shake.processor.program.types.ShakeType
import com.shakelang.shake.processor.program.types.code.ShakeInvokable

/**
 * Utility to select the best function or constructor for a given set of parameters
 */
object ShakeSelect {

    /**
     * Select the best function for the given parameters
     *
     * @param functions The functions to select from
     * @param parameters The parameters to select the function for
     * @return The best function or null if no function was found
     */
    fun <I : ShakeInvokable, T : ShakeType> selectFunction(
        functions: List<I>,
        parameters: List<T>
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

    /**
     * Select the best constructor for the given parameters
     *
     * @param constructors The constructors to select from
     * @param parameters The parameters to select the constructor for
     * @return The best constructor or null if no constructor was found
     */
    fun <C : ShakeConstructor, T : ShakeType> selectConstructor(
        constructors: List<C>,
        parameters: List<T>
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
