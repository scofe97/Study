package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

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
    val dRow11 = arrayOf(0, 1, 2, 3)
    val dColumn11 = arrayOf(0, 0, 0, 0)

    val dRow12 = arrayOf(0, 0, 0, 0)
    val dColumn12 = arrayOf(0, 1, 2, 3)

    val figure1 = arrayOf(
        arrayOf(dColumn11, dRow11),
        arrayOf(dColumn12, dRow12)
    )

    // 정사각형 모형
    val dRow21 = arrayOf(0, 1, 0, 1)
    val dColumn21 = arrayOf(0, 0, 1, 1)

    val figure2 = arrayOf(arrayOf(dColumn21, dRow21))

    // L자 모형
    val dRow31 = arrayOf(0, -1, -1, -1)
    val dColumn31 = arrayOf(0, 0, -1, -2)

    val dRow32 = arrayOf(0, 0, 1, 2)
    val dColumn32 = arrayOf(0, -1, -1, -1)

    val dRow33 = arrayOf(0, 1, 1, 1)
    val dColumn33 = arrayOf(0, 0, -1, -2)

    val dRow34 = arrayOf(0, 0, 1, 2)
    val dColumn34 = arrayOf(0, 1, 1, 1)

    val dRow35 = arrayOf(0, 1, 1, 1)
    val dColumn35 = arrayOf(0, 0, 1, 2)

    val dRow36 = arrayOf(0, -1, -1, -1)
    val dColumn36 = arrayOf(0, 0, 1, 2)

    val dRow37 = arrayOf(0, 0, -1, -2)
    val dColumn37 = arrayOf(0, 1, 1, 1)

    val dRow38 = arrayOf(0, 0, -1, -2)
    val dColumn38 = arrayOf(0, -1, -1, -1)

    val figure3 = arrayOf(
        arrayOf(dColumn31, dRow31),
        arrayOf(dColumn32, dRow32),
        arrayOf(dColumn33, dRow33),
        arrayOf(dColumn34, dRow34),
        arrayOf(dColumn35, dRow35),
        arrayOf(dColumn36, dRow36),
        arrayOf(dColumn37, dRow37),
        arrayOf(dColumn38, dRow38)
    )


    // 지그재그 모형
    val dRow41 = arrayOf(0, 0, 1, 1)
    val dColumn41 = arrayOf(0, 1, 1, 2)

    val dRow42 = arrayOf(0, 1, 1, 2)
    val dColumn42 = arrayOf(0, 0, -1, -1)

    val dRow43 = arrayOf(0, 0, 1, 1)
    val dColumn43 = arrayOf(0, -1, -1, -2)

    val dRow44 = arrayOf(0, -1, -1, -2)
    val dColumn44 = arrayOf(0, 0, -1, -1)

    val figure4 = arrayOf(
        arrayOf(dColumn41, dRow41),
        arrayOf(dColumn42, dRow42),
        arrayOf(dColumn43, dRow43),
        arrayOf(dColumn44, dRow44)
    )


    // ㅗ 모형
    val dRow51 = arrayOf(0, -1, 0, 1)
    val dColumn51 = arrayOf(0, 1, 1, 1)

    val dRow52 = arrayOf(0, -1, -1, -1)
    val dColumn52 = arrayOf(0, -1, 0, 1)

    val dRow53 = arrayOf(0, -1, 0, 1)
    val dColumn53 = arrayOf(0, -1, -1, -1)

    val dRow54 = arrayOf(0, 1, 1, 1)
    val dColumn54 = arrayOf(0, -1, 0, 1)

    val figure5 = arrayOf(
        arrayOf(dColumn51, dRow51),
        arrayOf(dColumn52, dRow52),
        arrayOf(dColumn53, dRow53),
        arrayOf(dColumn54, dRow54)
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

                        val nColumn = column + shapeStep[0][idx]
                        val nRow = row + shapeStep[1][idx]

                        if (nColumn !in 0 until columnSpace || nRow !in 0 until rowSpace) {
                            checkState = false
                        }
                    }

                    if (checkState) {
                        repeat(4) { idx ->

                            val nColumn = column + shapeStep[0][idx]
                            val nRow = row + shapeStep[1][idx]

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

