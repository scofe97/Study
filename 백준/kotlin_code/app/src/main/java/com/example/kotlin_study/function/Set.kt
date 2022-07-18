package com.example.kotlin_study.function


fun main(){
    // 변경가능한 set
    val openIssues: MutableSet<String> = mutableSetOf("uniqueDescr1", "uniqueDescr2", "uniqueDescr3")


    fun addIssue(uniqueDesc: String): Boolean {
        // 내용추가
        return openIssues.add(uniqueDesc)
    }

    fun getStatusLog(isAdded: Boolean): String {
        // 중복된 값이 있는지 확인
        return if (isAdded) "registered correctly." else "marked as duplicate and rejected."
    }

    val aNewIssue: String = "uniqueDescr4"
    val anIssueAlreadyIn: String = "uniqueDescr2"

    println("Issue $aNewIssue ${getStatusLog(addIssue(aNewIssue))}")
    println("Issue $anIssueAlreadyIn ${getStatusLog(addIssue(anIssueAlreadyIn))}")
}