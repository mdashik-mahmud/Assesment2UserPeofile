import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")//creating the table
data class Note(
//unique Identifier
    @PrimaryKey(autoGenerate = true)
    val id :Int =0,
    //field
    val name : String,
    val address: String,
    val phone: String,
    val gmail: String,

    )