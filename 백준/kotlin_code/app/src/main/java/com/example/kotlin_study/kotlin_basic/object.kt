package com.example.kotlin_study

fun main(){

    val car = Car.makeCar(150)
    val car2 = Car.Factory.makeCar(150)
    println(Car.Factory.cars.size)

    start(auto())

    start(object : Vehicle {
        override fun drive() = "Driving really fast"
    })

}


/*object CarFactory{
    val cars = mutableListOf<Car>()

    fun makeCar(horsePowers: Int) : Car{
        val car = Car(horsePowers)
        cars.add(car)
        return car
    }
}*/

class Car(val horsePowers: Int){
    companion object Factory{
        val cars = mutableListOf<Car>()

        fun makeCar(horsePowers: Int) : Car{
            val car = Car(horsePowers)
            cars.add(car)
            return car
        }
    }
}

interface Vehicle {
    fun drive(): String
}

class auto : Vehicle{
    override fun drive(): String {
        return "오토바이"
    }
}

fun start(vehicle: Vehicle) = println(vehicle.drive())