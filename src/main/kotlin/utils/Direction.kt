package utils

import java.awt.Point


enum class Direction(val x: Int, val y: Int) {
    LEFT(-1, 0),
    TOP_LEFT(-1, -1),
    UP(0, -1),
    TOP_RIGHT(1, -1),
    RIGHT(1, 0),
    BOTTOM_RIGHT(1, 1),
    DOWN(0, 1),
    BOTTOM_LEFT(-1, 1);
}

enum class Movement(val x: Long, val y: Long)

operator fun Point.plus(direction: Direction) = Point(x + direction.x, y + direction.y)
operator fun Point.plus(movement: Movement) = Point((x + movement.x).toInt(), (y + movement.y).toInt())

fun <T> List<List<T>>.inGrid(point: Point): Boolean {
    return point.x in this[0].indices && point.y in this.indices
}

fun <T> List<List<T>>.getValue(point: Point): T {
    return this[point.y][point.x]
}
