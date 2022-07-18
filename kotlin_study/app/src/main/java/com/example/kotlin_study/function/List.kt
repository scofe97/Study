package com.example.kotlin_study.function

fun main(){

    val systemUsers : MutableList<Int> = mutableListOf(1,2,3)
    val sudoers : List<Int> = systemUsers

    fun addSystemUser(newUser: Int) {
        systemUsers.add(newUser)
    }

    fun getSysSudoers(): List<Int> {
        return sudoers
    }

    addSystemUser(4)
    println("sudoers 크기: ${getSysSudoers().size}")
    getSysSudoers().forEach {
            i -> println("systemUsers 에 담긴 내용 $i")
    }
    // getSysSudoers().add(5) <- Error!
}
