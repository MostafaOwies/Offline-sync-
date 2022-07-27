package test.microsoft.com.synctest0.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 *The Dao to interact with the data in the data base has two methods :-
 * fun insert() : insert data into the dataBase.
 * fun getAll() : retrieve all data from the dataBase.
 */
@Dao
interface OfflineDao {
    @Insert
     fun insert(mOfflineEntity: OfflineEntity)
    @Query("SELECT*FROM `synctest-table`" )
     fun getAll():Flow<List<OfflineEntity>>
}