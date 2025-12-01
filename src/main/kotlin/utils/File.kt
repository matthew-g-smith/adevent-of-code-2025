package utils

import java.io.File

fun <T> readInput(fileName: String, map: (List<String>) -> T): T {
    val classLoader = object {}::class.java.classLoader
    val resource = classLoader.getResource(fileName)
        ?: throw IllegalArgumentException("File $fileName not found in resources")
    return map(File(resource.toURI()).readLines())
}
