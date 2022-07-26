package test.microsoft.com.synctest0.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [OfflineEntity::class], version = 1, exportSchema = false)
abstract class OfflineDatabase :RoomDatabase(){
    abstract fun offlineDao():OfflineDao

    companion object{
        @Volatile
        private var INSTANCE:OfflineDatabase?=null
        fun getInstance(context: Context):OfflineDatabase{
            synchronized(this){
                var instance= INSTANCE
                if (instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        OfflineDatabase::class.java,
                        "offline_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}