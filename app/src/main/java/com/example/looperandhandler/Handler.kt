
package com.example.looperandhandler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Handler {

    fun post(task: Runnable) {
        /*
        This method posts the task into the thread
         */
    }

    fun quit() {
        /*
        Stops the handler
         */
    }

}

class MainActivity : AppCompatActivity() {
    //...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Call example of Handler
    }
    //...
}