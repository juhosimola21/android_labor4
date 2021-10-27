package com.example.quiz_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts

const val EXTRA_MESSAGE = "username"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toast = Toast.makeText(applicationContext, "Loads data...", Toast.LENGTH_SHORT)
        toast.show()
        Log.i("Info", "onCreate() called")

        val buttonChoosed  = findViewById<Button>(R.id.button2)
        val username = findViewById<EditText>(R.id.editTextTextPersonName)
        val Contact = registerForActivityResult(ActivityResultContracts.PickContact(),
            ActivityResultCallback {
                val list = listOf(ContactsContract.Contacts.DISPLAY_NAME).toTypedArray()
                val cursor = contentResolver.query(it, list, null, null, null)
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        username.setText(cursor.getString(0))
                    }
                    cursor.close()
                }
            })
        buttonChoosed.setOnClickListener {
            Contact.launch(null)
        }
    }

    override fun onStart() {
        super.onStart()
        val toast = Toast.makeText(applicationContext, "onStart()", Toast.LENGTH_SHORT)
        toast.show()
        Log.i("Info", "onStart() called")
    }

    override fun onResume(){
        super.onResume()
        val toast = Toast.makeText(applicationContext, "onResume()", Toast.LENGTH_SHORT)
        toast.show()
        Log.i("Info", "onResume() called")
    }

    override fun onPause(){
        super.onPause()
        val toast = Toast.makeText(applicationContext, "onPause()", Toast.LENGTH_SHORT)
        toast.show()
        Log.i("Info", "onPause() called")
    }

    override fun onStop(){
        super.onStop()
        val toast = Toast.makeText(applicationContext, "onStop()", Toast.LENGTH_SHORT)
        toast.show()
        Log.i("Info", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        val toast = Toast.makeText(applicationContext, "onDestroy()", Toast.LENGTH_SHORT)
        toast.show()
        Log.i("Info","onDestroy() called")
    }

    fun sendMessage(view: View) {
        val edit = findViewById<EditText>(R.id.editTextTextPersonName)
        val message = edit.text.toString()
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

}