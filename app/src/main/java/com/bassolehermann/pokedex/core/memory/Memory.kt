package com.bassolehermann.pokedex.core.memory

import android.os.Build
import java.io.File
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.deleteRecursively
import kotlin.io.path.exists
import kotlin.io.path.notExists
import kotlin.io.path.pathString

class FileCache<T>(
    private var read: ((file: File) -> T?)? = null,
    private var rootDirectory: Path,
    var write: (data:T, targetFile: File) -> Unit
) : Cache<T> {
    private var useMemory:Boolean = false
    private var memoryCache: Memory<T> = Memory()


    override suspend fun put(key: String, value: T): T? {
        var old:T? = get(key);
        if (write != null) {
            var file:File = targetFile(key)
            if (useMemory){
                memoryCache.put(file.path, value)
            }
            write(value, file)
        }
        return old
    }

    override suspend fun get(key: String): T? {
        if (read != null){
            var file:File = targetFile(key)
            if (useMemory) {
                var inMemoryValue:T? = memoryCache.get(file.path)
                if (inMemoryValue != null) {
                    return inMemoryValue
                }
            }
            return read?.let { it(file) }
        }
        return null
    }

    override suspend fun remove(key: String): T? {
        var old:T? = get(key)
        var file:File = targetFile(key)
        file.deleteRecursively()
        return old
    }

    override suspend fun size(): Int {
        var keyList:List<String> = keys()
        return keyList.size
    }

    @OptIn(ExperimentalPathApi::class)
    override suspend fun clear(): Int {
        var keyList:List<String> = keys()
        rootDirectory.deleteRecursively()
        memoryCache.clear()
        return keyList.size
    }

    override suspend fun containsKey(entryName: String): Boolean {
        var targetFile:File = targetFile(entryName)
        if (memoryCache.containsKey(targetFile.path)) {
            return true
        }

        return targetFile.exists()
    }

     private fun keys():List<String> {
        var directory = Path(rootDirectory.pathString)
        var content = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            directory.toMutableList()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        var keys:MutableList<String> = mutableListOf()
        for (file in content) {
            keys.add(file.pathString)
        }
        return keys
    }

    private fun targetFile(key:String): File {
        if (rootDirectory.notExists()){
            rootDirectory.createDirectory()
        }
        var entryName:String =  key.hashCode().toString()
        var path:String = if( rootDirectory.pathString.endsWith("/") ) rootDirectory.pathString else  rootDirectory.pathString + "/"
        var out = File("$path$entryName")
        if(!out.exists()){
            out.createNewFile()
        }
        return out
    }

}

class Memory<T> : Cache<T> {

    private var map:MutableMap<String?,T> = mutableMapOf()

    override suspend fun put(key: String, value: T): T? {
        var oldValue = map[key]
        map[key] = value
        return oldValue
    }

    override suspend fun get(key: String): T? {
        return map[key]
    }

    override suspend fun remove(key: String): T? {
        return map.remove(key)
    }

    override suspend fun size(): Int {
        return map.size
    }

    override suspend fun clear(): Int {
        var size:Int = map.size
        map.clear()
        return size
    }

    override suspend fun containsKey(entryName: String):Boolean {
        return map.containsKey(entryName)
    }
}