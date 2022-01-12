package com.mohit.sharedprefernce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var inputUsername: EditText? = null
    private var inputPassword: EditText? = null
    private lateinit var usernameTextView: TextView
    private lateinit var passwordTextView: TextView
    private lateinit var loginButton: Button
    private lateinit var shareprefernce:SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shareprefernce = SharedPreference(this);

        inputUsername = findViewById(R.id.edit_username)
        inputPassword = findViewById(R.id.edit_password)
        loginButton = findViewById(R.id.button_login)
        usernameTextView = findViewById(R.id.username)
        passwordTextView = findViewById(R.id.password)


        if (shareprefernce.getValueString("username") != null || shareprefernce.getValueString("password") != null || shareprefernce.getValueString("button") !=null){

            usernameTextView.setText(shareprefernce.getValueString("username"))
            passwordTextView.setText(shareprefernce.getValueString("password"))
            loginButton.setText(shareprefernce.getValueString("button"))

        }else{
            usernameTextView.setText("No Username Saved")
            passwordTextView.setText("No password Saved")
            loginButton.setText("Save Data")
        }

        loginButton!!.setOnClickListener(View.OnClickListener {
            val username = inputUsername!!.text.toString().trim()
            val password = inputPassword!!.text.toString().trim()

            saveDataSharePrefernce(username, password)

        })

    }

    private fun saveDataSharePrefernce(username: String, password: String) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(
                applicationContext,
                "PLease Enter all Details",
                Toast.LENGTH_SHORT
            )
                .show()
        }

        shareprefernce.save("username", username)
        shareprefernce.save("password", password)
        shareprefernce.save("button", "Update Values")

        usernameTextView.setText(shareprefernce.getValueString("username"))
        passwordTextView.setText(shareprefernce.getValueString("password"))
        loginButton.setText(shareprefernce.getValueString("button"))

    }
}