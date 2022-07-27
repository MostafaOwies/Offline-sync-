package test.microsoft.com.synctest0.roomdb

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import test.microsoft.com.synctest0.roomdb.OfflineDao
import test.microsoft.com.synctest0.roomdb.OfflineEntity

/**
 * implements the method inside the Dao to do some functions.
 */
class RoomDbImp(private val mOfflineDao: OfflineDao) {
    /**
     * take Offline Entity as a parameter and insert data received from the user into the database.
     */
    suspend fun insert(mOfflineEntity: OfflineEntity){
        withContext(Dispatchers.Default){
            mOfflineDao.insert(mOfflineEntity)
        }
    }

    /**
     * retrieve data from the Offline entity as a list of flows .
     */
    fun getAll():Flow<List<OfflineEntity>> = mOfflineDao.getAll()
}