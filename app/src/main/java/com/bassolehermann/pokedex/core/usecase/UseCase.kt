package com.bassolehermann.pokedex.core.usecase

import com.bassolehermann.pokedex.core.error.Failure
import com.bassolehermann.pokedex.core.functionals.Either

abstract class UseCase<Type,Params> {
  abstract suspend fun call(params: Params?): Either<Failure, Type>
}