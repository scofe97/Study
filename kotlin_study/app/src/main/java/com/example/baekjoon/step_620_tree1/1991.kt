package com.example.baekjoon.step_620_tree1

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count = br.readLine().toInt()
private var root : TreeNode<String>? = null

data class TreeNode<T>(
    var data : T,
    var left : TreeNode<T>? = null,
    var right : TreeNode<T>? = null
)

private fun main(){

    repeat(count){
        val (data,left,right) = br.readLine().split(" ")
        add(data,left,right)
    }

    preOrder(root!!)
    bw.write("\n")
    inorder(root!!)
    bw.write("\n")
    postorder(root!!)
    bw.write("\n")

    bw.flush()
    bw.close()
    br.close()

}


private fun add(data: String, left: String, right: String){
    // 루트가 NULL 일 때 (트리 내에 존재하지 않을 때)
    if(root == null){
        // 새로 생성
        if(data != ".") root = TreeNode(data)
        if(left != ".") root!!.left = TreeNode(left)
        if(right != ".") root!!.right = TreeNode(right)
    }
    // 트리 내에 존재할 때
    else search(root!!,data,left,right)
}

private fun search(root: TreeNode<String>, data: String, left: String, right: String){
    // 찾았을 때, left right 연결
    if(root.data == data){
        if(left != ".") root!!.left = TreeNode(left)
        if(right != ".") root!!.right = TreeNode(right)
    }

    // 못 찾았을 때, 좌우 탐색
    else {
        if(root.left != null) search(root.left!!,data,left,right)
        if(root.right != null) search(root.right!!,data,left,right)
    }
}

private fun preOrder(root : TreeNode<String>){
    bw.write(root.data)
    if(root.left != null) preOrder(root.left!!)
    if(root.right != null) preOrder(root.right!!)
}

private fun inorder(root : TreeNode<String>){
    if(root.left != null) inorder(root.left!!)
    bw.write(root.data)
    if(root.right != null) inorder(root.right!!)
}

private fun postorder(root : TreeNode<String>){
    if(root.left != null) postorder(root.left!!)
    if(root.right != null) postorder(root.right!!)
    bw.write(root.data)
}