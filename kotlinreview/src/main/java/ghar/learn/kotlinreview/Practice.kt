package ghar.learn.kotlinreview


fun main(){

    val zan = Person()
    zan.name = "Zain"

    println("${zan.name} has eaten? ${zan.hasEaten}")
    zan.feedMe("Nashta")
    println("${zan.name} has eaten? ${zan.hasEaten}")
    println("${zan.name} has consumed ${zan.calories} calories")
}
class Person {
    /**
     * Using setter() to validate the parameter or value passed in while
     * it does not have 'it's own' backing-field, but using another property's one
      */
    var name : String = ""
        set(value) {
            if(value.isEmpty()) throw Exception("please enter a name, it can't be empty")
            field = value
        }

    var calories = 0

    // Using backing field
    val hasEaten : Boolean
        get() {
            return calories != 0            // return false if Person has not eaten anything, i.e: calories would be '0' then
        }

    fun feedMe(food : String){
        updateCalories(20)
    }

    fun updateCalories(calories : Int) {
        this.calories += calories     // 'this' is added to make explicit call to 'property of the class (that has same name)
    }

}