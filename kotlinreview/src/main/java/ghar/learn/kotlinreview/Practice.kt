package ghar.learn.kotlinreview


fun main(){

    val zan = Person()
    zan.name = "Zain"

    zan.feedMe("Nashta")
    println("${zan.name} has consumed ${zan.calories} calories")
}
class Person {
    var name = ""
    var calories = 0

    fun feedMe(food : String){
        println("$name has been feed $food")
        updateCalories(20)
    }

    fun updateCalories(calories : Int){
//        calories += 10
        this.calories += calories     // 'this' is added to make explicit call to 'property of the class (that has same name)
    }

}