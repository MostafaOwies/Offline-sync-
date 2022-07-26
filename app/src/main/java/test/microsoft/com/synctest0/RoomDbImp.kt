package test.microsoft.com.synctest0

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import test.microsoft.com.synctest0.roomdb.OfflineDao
import test.microsoft.com.synctest0.roomdb.OfflineEntity

class RoomDbImp(private val mOfflineDao: OfflineDao) {

    suspend fun insert(mOfflineEntity: OfflineEntity){
        withContext(Dispatchers.Default){
            mOfflineDao.insert(mOfflineEntity)
        }
    }

    fun getAll():Flow<List<OfflineEntity>> = mOfflineDao.getAll()
}