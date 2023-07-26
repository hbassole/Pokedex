package com.bassolehermann.pokedex.core.error

data class WebError(
   override val statusCode:Int?,
   override val message:String,
): Failure(statusCode,message)
