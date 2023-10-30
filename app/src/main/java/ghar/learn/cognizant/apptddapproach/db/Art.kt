package ghar.learn.cognizant.apptddapproach.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class Art(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    var name : String,
    var artistName : String,
    var year : Int,
    var imageUrl : String
) {

}