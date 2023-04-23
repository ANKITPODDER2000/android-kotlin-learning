package com.cheezycode.daggerexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var userRegistrationServiceComponent: UserRegistrationService

    @Inject
    lateinit var notificationService: NotificationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = DaggerUserRegistrationServiceComponent.builder().build()
        component.inject(this@MainActivity)
        // component.getUserRegistrationService().registerUser("ankit", "ankit@demo.com")

        userRegistrationServiceComponent.registerUser("Ankit", "ankit@demo.com")
        notificationService.sendMail("ankit@demo.com")

        Log.d("MainActivity", "Done")
    }
}