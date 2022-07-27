package test.microsoft.com.synctest0

import android.app.Application
import test.microsoft.com.synctest0.roomdb.OfflineDatabase

/**
 * Instantiate the database by Lazy to be a singleton
 */
class SyncTestApp:Application() {
    val db by lazy {
        OfflineDatabase.getInstance(this)
    }
}