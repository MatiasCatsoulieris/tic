import java.util.*
import kotlin.math.abs

val scanner = Scanner(System.`in`)
var grid = arrayOf(
    charArrayOf('_', '_', '_'),
    charArrayOf('_', '_', '_'),
    charArrayOf('_', '_', '_')
)
var result = ""
var oCount = 0
var xCount = 0

fun main() {
    displayGrid()
    makeMove(oCount, xCount)

}

fun calculateResult() {
    oCount = grid.map { it.count { it == 'O' } }.sum()
    xCount = grid.map { it -> it.count { it == 'X' } }.sum()
    val drawCount = grid.map { it.count { it -> it == '_' } }.sum()
    val xWins = checkIfUserWin('X')
    val oWins = checkIfUserWin('O')

    if (abs(oCount - xCount) > 1 || xWins && oWins) {
        result = "Impossible"
        displayResult()
    } else if (xWins) {
        result = "X wins"
        displayResult()
    } else if (oWins) {
        result = "O wins"
        displayResult()
    } else if (drawCount == 0) {
        result = "Draw"
        displayResult()

    } else {
        makeMove(oCount, xCount)
    }
}



fun checkIfUserWin(symbol: Char): Boolean {
    return when {
        grid[0][0] == grid[0][1] && grid[0][0] == grid[0][2] && grid[0][0] == symbol -> true
        grid[1][0] == grid[1][1] && grid[1][0] == grid[1][2] && grid[1][0] == symbol -> true
        grid[2][0] == grid[2][1] && grid[2][0] == grid[2][2] && grid[2][0] == symbol -> true
        grid[0][0] == grid[1][0] && grid[0][0] == grid[2][0] && grid[0][0] == symbol -> true
        grid[0][1] == grid[1][1] && grid[0][1] == grid[2][1] && grid[0][1] == symbol -> true
        grid[0][2] == grid[1][2] && grid[0][2] == grid[2][2] && grid[0][2] == symbol -> true
        grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[0][0] == symbol -> true
        grid[2][0] == grid[1][1] && grid[2][0] == grid[0][2] && grid[2][0] == symbol -> true
        else -> false
    }
}

fun displayGrid() {
    println("---------")
    println("| ${grid[0][0]} ${grid[0][1]} ${grid[0][2]} |")
    println("| ${grid[1][0]} ${grid[1][1]} ${grid[1][2]} |")
    println("| ${grid[2][0]} ${grid[2][1]} ${grid[2][2]} |")
    println("---------")
    calculateResult()
}

fun displayResult() {
    println(result)
}

fun makeMove(oCount: Int, xCount: Int) {
    while (result == "") {
        print("Enter the coordinates: ")
        val x = scanner.next()
        val y = scanner.next()
        val isValidCoordinates = isValidateCoordinates(x, y)
        if (!isValidCoordinates) {
            continue
        }
        if (abs(oCount - xCount) == 0) {
            grid[x.toInt() - 1][y.toInt() - 1] = 'X'
        } else {
            grid[x.toInt() - 1][y.toInt() - 1] = 'O'
        }
        displayGrid()
    }
}

fun isValidateCoordinates(x: String, y: String): Boolean {
    return when {
        x.length != 1 || y.length != 1 || !x[0].isDigit() || !y[0].isDigit() -> {
            println("You should enter numbers!")
            false
        }
        x.toInt() !in 1..3 || y.toInt() !in 1..3 -> {
            println("Coordinates should be from 1 to 3!")
            false
        }
        grid[x.toInt() - 1][y.toInt() - 1] != '_' -> {
            println("This cell is occupied! Choose another one!")
            false
        }
        else -> true
    }
}
