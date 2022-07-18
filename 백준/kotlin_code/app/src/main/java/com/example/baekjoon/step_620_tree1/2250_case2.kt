package com.example.baekjoon.step_620_tree1

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count = br.readLine().toInt()

private var root: Tree2? = null
private var tree2: Array<Tree2> = Array(count+1){ Tree2(-1) }
private var level = IntArray(count+1)

private var levelArr = mutableListOf<MutableList<Int>>()
private var widthArr = MutableList<Int>(count+1){1}
private var resultArr = mutableListOf<Int>()

private var w = 0

data class Tree2(
    var data: Int = -1,
    var parent : Tree2? = null,
    var left: Tree2? = null,
    var right: Tree2? = null
)

private fun main() {
    repeat(count) {
        val (data, left, right) = br.readLine().split(" ").map { it.toInt() }
        // add(data, left, right)

        tree2[data].data = data

        if(left != -1) {
            tree2[data].left = tree2[left]
            tree2[left].parent = tree2[data]

        }
        if(right != -1) {
            tree2[data].right = tree2[right]
            tree2[right].parent = tree2[data]
        }
    }

    for(i in 1 .. tree2.size){
        if(tree2[i].parent == null) {
            root = tree2[i]
            break
        }
    }

    dfs(0, root!!)

    for(i in levelArr){
        var max = 0
        var min = 10001

        for(v in i){
            if(max < widthArr[v]) max = widthArr[v]
            if(min > widthArr[v]) min = widthArr[v]
        }

        resultArr.add(abs(max - min) +1)
    }

    val maxWidth = resultArr.maxOrNull()
    val rLevel = resultArr.indexOf(maxWidth)+1

    bw.write("$rLevel $maxWidth")
    bw.flush()
    bw.close()
    br.close()
}


private fun dfs(level: Int, root: Tree2) {
    if(level == levelArr.size) levelArr.add(mutableListOf())
    levelArr[level].add(root.data)

    if (root.left != null) dfs(level + 1, root.left!!)

    widthArr[root.data] = w
    w++

    if (root.right != null) dfs(level + 1, root.right!!)
}

/*
5
5 -1 -1
1 4 5
4 -1 -1
2 1 3
3 -1 -1
*/
