package com.bassolehermann.pokedex.core.error

import java.lang.Exception

open class Failure(
    open val statusCode:Int?,
    override val message:String?,
): Throwable()