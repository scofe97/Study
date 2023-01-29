package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

// 효율보다는 걍 코드줄여보려고 작성해봄
fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))


    val st = StringTokenizer(br.readLine())
    val columnSpace = st.nextToken().toInt()
    val rowSpace = st.nextToken().toInt()

    val list = MutableList<MutableList<Int>>(columnSpace) { MutableList(rowSpace) { 0 } }

    for (i in 0 until columnSpace) {
        val step = br.readLine().split(" ").map { it -> it.toInt() }.toMutableList()
        list[i] = step
    }


    // 1자 모형
    val dPair11  = arrayOf(Pair(0,0), Pair(1,0), Pair(2,0), Pair(3,0))
    val dPair12  = arrayOf(Pair(0,0), Pair(0,1), Pair(0,2), Pair(0,3))
    val figure1 = arrayOf(
        dPair11,
        dPair12
    )


    // 정사각형 모형
    val dPair21 = arrayOf(Pair(0,0), Pair(0,1), Pair(1,0), Pair(1,1))
    val figure2 = arrayOf(dPair21)


    // L자 모형
    val dPair31  = arrayOf(Pair(0,0), Pair(-1,0), Pair(-1,-1), Pair(-1,-2))
    val dPair32  = arrayOf(Pair(0,0), Pair(0,-1), Pair(1,-1), Pair(2,-1))
    val dPair33  = arrayOf(Pair(0,0), Pair(1,0), Pair(1,-1), Pair(1,-2))
    val dPair34  = arrayOf(Pair(0,0), Pair(0,1), Pair(1,1), Pair(2,1))
    val dPair35  = arrayOf(Pair(0,0), Pair(1,0), Pair(1,1), Pair(1,2))
    val dPair36  = arrayOf(Pair(0,0), Pair(-1,0), Pair(-1,1), Pair(-1,2))
    val dPair37  = arrayOf(Pair(0,0), Pair(0,1), Pair(-1,1), Pair(-2,1))
    val dPair38  = arrayOf(Pair(0,0), Pair(0,-1), Pair(-1,-1), Pair(-2,-1))

    val figure3 = arrayOf(
        dPair31,
        dPair32,
        dPair33,
        dPair34,
        dPair35,
        dPair36,
        dPair37,
        dPair38,
    )


    // 지그재그 모형
    val dPair41  = arrayOf(Pair(0,0), Pair(0,1), Pair(1,1), Pair(1,2))
    val dPair42  = arrayOf(Pair(0,0), Pair(1,0), Pair(1,-1), Pair(2,-1))
    val dPair43  = arrayOf(Pair(0,0), Pair(0,-1), Pair(1,-1), Pair(1,-2))
    val dPair44  = arrayOf(Pair(0,0), Pair(-1,0), Pair(-1,-1), Pair(-2,-1))

    val figure4 = arrayOf(
        dPair41,
        dPair42,
        dPair43,
        dPair44
    )


    // ㅗ 모형
    val dPair51  = arrayOf(Pair(0,0), Pair(-1,1), Pair(0,1), Pair(1,1))
    val dPair52  = arrayOf(Pair(0,0), Pair(-1,-1), Pair(-1,0), Pair(-1,1))
    val dPair53  = arrayOf(Pair(0,0), Pair(-1,-1), Pair(0,-1), Pair(1,-1))
    val dPair54  = arrayOf(Pair(0,0), Pair(1,-1), Pair(1,0), Pair(1,1))

    val figure5 = arrayOf(
        dPair51,
        dPair52,
        dPair53,
        dPair54
    )


    val figure = arrayOf(figure1, figure2, figure3, figure4, figure5)
    var result = 0

    for (column in 0 until columnSpace) {

        for (row in 0 until rowSpace) {

            for (shape in figure) {

                for (shapeStep in shape) {
                    var checkState = true
                    var sum = 0


                    repeat(4) { idx ->

                        val nRow = row + shapeStep[idx].component1()
                        val nColumn = column + shapeStep[idx].component2()

                        if (nColumn !in 0 until columnSpace || nRow !in 0 until rowSpace) {
                            checkState = false
                        }
                    }

                    if (checkState) {
                        repeat(4) { idx ->

                            val nRow = row + shapeStep[idx].component1()
                            val nColumn = column + shapeStep[idx].component2()

                            sum += list[nColumn][nRow]
                        }
                    }

                    result = max(result, sum)
                }
            }

        }
    }

    bw.write("$result")
    bw.flush()
    bw.close()
    br.close()

}

