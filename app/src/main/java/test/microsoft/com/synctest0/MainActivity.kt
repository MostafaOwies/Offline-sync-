package test.microsoft.com.synctest0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import test.microsoft.com.synctest0.roomdb.OfflineDao
import test.microsoft.com.synctest0.roomdb.OfflineEntity
import test.microsoft.com.synctest0.roomdb.RoomDbImp
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var enterDataEt :EditText
    private lateinit var addDataBtn :Button
    private lateinit var syncDataBtn :Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enterDataEt=findViewById(R.id.input_ET)
        addDataBtn=findViewById(R.id.add_data_btn)
        syncDataBtn=findViewById(R.id.sync_data_btn)

        /**
         * Instantiate a singleton of the Dao.
         */
        val mOfflineDao=(application as SyncTestApp).db.offlineDao()

        addDataBtn.setOnClickListener {
            inputData(mOfflineDao)
        }
        syncDataBtn.setOnClickListener {
            syncData(mOfflineDao)
        }
    }

    /**
     * Insert the data from the EditTextView into the Offline Entity of our dataBase.
     * Uses our defined Dao to access its method "fun insert()" to insert the data.
     * Check if the editTextView is empty, if not:-
     * Launch a coroutine scope and call on "fun insert()" from the RoomDbImp class
     * and insert the text into the Offline Entity
     *
     * this method will be clicked when the Add Data button is clicked
     */
    private fun inputData(mOfflineDao: OfflineDao){
        val mData=enterDataEt.text.toString()
        try {
            if (mData.isEmpty()){
                Toast.makeText(this,"Cannot be empty",Toast.LENGTH_SHORT).show()
            }else{
                lifecycleScope.launch {
                    RoomDbImp(mOfflineDao).insert(OfflineEntity(name = mData))
                    Toast.makeText(this@MainActivity,"Inserted",Toast.LENGTH_SHORT).show()
                    enterDataEt.text.clear()
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
            Toast.makeText(this@MainActivity,"Failed",Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Inside a coroutine Scope, collects the data list from the data flow inside our Offline Entity.
     * Collected data flow list will be passed to the syncData function inside the FireStoreClass
     * to be synced with the online database at the fireStore
     *
     * this method will be called when the Sync button is clicked
     */
    private fun syncData(mOfflineDao: OfflineDao){
        try {
            lifecycleScope.launch {
                RoomDbImp(mOfflineDao).getAll().collect{
                    FireStoreClass().syncData(this@MainActivity,it)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
            Toast.makeText(this@MainActivity,"Failed to sync",Toast.LENGTH_SHORT).show()
        }
    }
}