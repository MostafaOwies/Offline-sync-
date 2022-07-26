package test.microsoft.com.synctest0.roomdb

import android.app.Application

class SyncTestApp:Application() {
    val db by lazy {
        OfflineDatabase.getInstance(this)
    }
}