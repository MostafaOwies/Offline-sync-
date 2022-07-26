package test.microsoft.com.synctest0

import android.app.Application
import test.microsoft.com.synctest0.roomdb.OfflineDatabase

class SyncTestApp:Application() {
    val db by lazy {
        OfflineDatabase.getInstance(this)
    }
}