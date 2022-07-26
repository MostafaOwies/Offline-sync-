package test.microsoft.com.synctest0.roomdb

import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.microsoft.com.synctest0.MainActivity
import java.lang.Exception

class FiresToreClass(private val ioDispatcher: CoroutineDispatcher=Dispatchers.IO) {
    private val mFirestore =Firebase.firestore

    suspend fun syncData(activity:MainActivity,offlineEntity:List<OfflineEntity>){
        try {
            val dataHashMap=HashMap<String,Any>()
            dataHashMap["datalist"]=offlineEntity
            withContext(ioDispatcher){
                mFirestore.collection("data")
                    .document("TSxvN9hpMn5V0AjwHDyH")
                    .update(dataHashMap)
                    .addOnSuccessListener {
                        Toast.makeText(activity,"Synced", Toast.LENGTH_SHORT).show()
                    }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}