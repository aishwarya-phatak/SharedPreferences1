package com.example.sharedpreferencesinandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var btnMain : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeToast("onCreate called")

        var prefs = getSharedPreferences("pref_settings", Activity.MODE_PRIVATE)

        var editor = prefs.edit()
        editor.putString("name","Bitcode")
        editor.putInt("code",1245)
        editor.putFloat("fValue",23.22f)
        editor.commit()

        var name = prefs.getString("name","--")
        var code = prefs.getInt("code",0)
        var fValue = prefs.getFloat("fValue",10.00f)

        makeToast("$name -  $code  - $fValue")

        btnMain = findViewById(R.id.btnMain)
        btnMain.setOnClickListener {
            var intent = Intent(MainActivity@this,MainActivity::class.java)
            intent.putExtra("code", Random.nextInt())
            startActivity(intent)
        }

        var prefs1 = getPreferences(Activity.MODE_PRIVATE)
        editor = prefs1.edit()
        editor.putString("city","Pune")
        editor.putInt("pin",411004)
        editor.commit()


        var city = prefs1.getString("city","Nagpur")
        var pinCode = prefs1.getInt("pin",411030)
        makeToast("$city -- $pinCode")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if(intent != null){
            makeToast("onNewIntent ${intent.getIntExtra("code",411030)}")
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun makeToast(text : String){
        Log.e("tag",text)
        Toast.makeText(this,text,Toast.LENGTH_LONG).show()
    }
}