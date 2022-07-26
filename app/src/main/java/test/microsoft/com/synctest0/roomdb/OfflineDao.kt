package test.microsoft.com.synctest0.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OfflineDao {
    @Insert
     fun insert(mOfflineEntity: OfflineEntity)
    @Query("SELECT*FROM `synctest-table`" )
     fun getAll():Flow<List<OfflineEntity>>
}