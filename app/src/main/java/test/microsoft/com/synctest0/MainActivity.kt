package test.microsoft.com.synctest0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import test.microsoft.com.synctest0.roomdb.FiresToreClass
import test.microsoft.com.synctest0.roomdb.OfflineDao
import test.microsoft.com.synctest0.roomdb.OfflineEntity
import test.microsoft.com.synctest0.roomdb.SyncTestApp
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

        val mOfflineDao=(application as SyncTestApp).db.offlineDao()

        addDataBtn.setOnClickListener {
            inputData(mOfflineDao)
        }
        syncDataBtn.setOnClickListener {
            syncData(mOfflineDao)
        }
    }

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

    private fun syncData(mOfflineDao: OfflineDao){
        try {
            lifecycleScope.launch {
                RoomDbImp(mOfflineDao).getAll().collect{
                    FiresToreClass().syncData(this@MainActivity,it)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
            Toast.makeText(this@MainActivity,"Failed to sync",Toast.LENGTH_SHORT).show()
        }
    }
}