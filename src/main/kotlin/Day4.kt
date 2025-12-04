import utils.Direction
import utils.getValue
import utils.inGrid
import utils.plus
import utils.readInput
import java.awt.Point


fun main() {
    val basicInput = readInput("day4-basic.txt") {lines -> mapDay4(lines)}
    val input = readInput("day4.txt") {lines -> mapDay4(lines)}
    println(handleDay4(basicInput))
    println(handleDay4(input))
    println(handleDay4Part2(basicInput))
    println(handleDay4Part2(input))
}

fun handleDay4(input: List<List<String>>): Long {
    return extractNeighbours(input).first.filter { it.value.size < 4 }.count().toLong()
}

fun handleDay4Part2(input: List<List<String>>): Long {
    val (nextToMe, iAmNextTo) = extractNeighbours(input)
    var hasRemoved = true
    var count = 0L
    while (hasRemoved) {
        val itemsToRemove = nextToMe.filter { it.value.size < 4 }
        itemsToRemove.forEach {
            nextToMe.remove(it.key)
            val toBeRemovedFrom = iAmNextTo.remove(it.key)
            toBeRemovedFrom?.forEach { removeMe ->
                if (nextToMe.contains(removeMe)) nextToMe.getValue(removeMe).remove(it.key)
            }
        }
        hasRemoved = itemsToRemove.isNotEmpty()
        count += itemsToRemove.size
    }
    return count
}

fun extractNeighbours(input: List<List<String>>): Pair<MutableMap<Point, MutableSet<Point>>, MutableMap<Point, MutableSet<Point>>> {
    val nextToMe = mutableMapOf<Point, MutableSet<Point>>()
    val iAmNextTo = mutableMapOf<Point, MutableSet<Point>>()
    input.forEachIndexed { rowIndex, line ->
        line.forEachIndexed { colIndex, value ->
            if (value == "@") {
                val point = Point(colIndex, rowIndex)
                val neighbours = Direction.entries.filter { direction ->
                    val check = point + direction
                    input.inGrid(check) && input.getValue(check) == "@"
                }.map { direction ->  point + direction}
                nextToMe.put(point, neighbours.toMutableSet())
                neighbours.forEach { neighbour -> iAmNextTo.getOrPut(neighbour) { mutableSetOf() }.add(point) }
            }
        }
    }
    return Pair(nextToMe, iAmNextTo)
}


private fun mapDay4(lines: List<String>): List<List<String>> = {
    lines.mapIndexed { rowIndex, line ->
        line.split("").filter { it.isNotBlank() }.mapIndexed { colIndex, value ->
            value
        }
    }
}()
