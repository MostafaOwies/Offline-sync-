package test.microsoft.com.synctest0

import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.microsoft.com.synctest0.roomdb.OfflineEntity
import java.lang.Exception

/**
 * the FireStore class where we initialize the fireStore and its functionalities.
 */
class FireStoreClass(private val ioDispatcher: CoroutineDispatcher=Dispatchers.IO) {
    private val mFirestore =Firebase.firestore

    /**
     * store the data from the Offline Entity into a HashMap of "String , Any".
     * inside a coroutine dispatcher we will call on a fireStore document
     * and update the document with the defined HashMap of Offline Entity.
     * on success will make a Toast to inform the user of the Sync.
     */
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