package test.microsoft.com.synctest0.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * the dataBase Entity which acts as the database model.
 * define how the data will be stored in the database.
 * primary key "id" as an int.
 * data column "name as a String.
 */
@Entity(tableName = "syncTest-table")
data class OfflineEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo(name = "name ID")
    val name:String
)
