package com.example.looperandhandler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.looperandhandler.databinding.ActivityMainBinding

class SampleActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var worker: SimpleHandler
    private lateinit var tv: TextView
    private val handler = Handler(Looper.getMainLooper()) {
        tv.text = it.obj as String
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tv = binding.textview
        val worker = SimpleHandler()
        worker.post {
            try {
                Thread.sleep(3000)
            } catch (e:InterruptedException){
                e.printStackTrace()
            }
            val msg = Message.obtain()
            msg.obj = "Task 1 Completed"
            handler.sendMessage(msg)
        }.execute {
            try {
                Thread.sleep(500)
            } catch (e:InterruptedException){
                e.printStackTrace()
            }
            val msg = Message.obtain()
            msg.obj = "Task 2 Completed"
            handler.sendMessage(msg)
        }
        worker.quit()
        worker.post {
            try {
                Thread.sleep(500)
            } catch (e:InterruptedException){
                e.printStackTrace()
            }
            val msg = Message.obtain()
            msg.obj = "Task 1 Completed"
            handler.sendMessage(msg)
        }

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        worker.quit()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}