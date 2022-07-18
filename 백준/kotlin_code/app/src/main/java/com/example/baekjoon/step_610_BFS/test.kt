import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private const val MAX_POSITION = 100000
private const val TELEPORT = 0
private const val MOVE_LEFT = 1
private const val MOVE_RIGHT = 2

private var n = 0
private var k = 0

fun main() {
    init()
    println(bfs())
}

private fun init() = with(BufferedReader(InputStreamReader(System.`in`))) {
    with(StringTokenizer(readLine())) {
        n = nextToken().toInt()
        k = nextToken().toInt()
    }
    close()
}

private fun bfs(): Int {
    val visited = IntArray(MAX_POSITION + 1).apply {
        this[n] = 1
    }
    val queue = LinkedList<Int>().apply { addLast(n) }

    while (queue.isNotEmpty()) {
        val current = queue.pollFirst()
        if (current == k) {
            return visited[current] - 1
        }

        for (i in 0 until 3) {
            var next = current
            when (i) {
                TELEPORT -> next *= 2
                MOVE_LEFT -> next -= 1
                MOVE_RIGHT -> next += 1
            }
            if (next in 0..MAX_POSITION && visited[next] == 0) {
                visited[next] = if (i == TELEPORT) visited[current] else visited[current] + 1
                queue.addLast(next)
            }
        }
    }

    return -1
}