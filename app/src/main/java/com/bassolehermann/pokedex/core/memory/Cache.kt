package com.bassolehermann.pokedex.core.memory

interface Cache<T> {
     suspend fun put(key:String, value:T):T?
     suspend fun get(key:String):T?
     suspend fun remove(key:String):T?
     suspend fun size():Int
     suspend fun clear():Int
     suspend fun containsKey(entryName:String):Boolean
}