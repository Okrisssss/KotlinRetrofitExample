package com.example.piachimov.firebasekotlinexample.ui.registration

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.di.Injector
import kotlinx.android.synthetic.main.activity_registration.*
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity(), RegistrationView {

    @Inject
    lateinit var registrationPresenter: RegistrationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        Injector.initRegistrationActivityComponent(this)
        Injector.registrationActivityComponent?.inject(this)
    }

    fun onRegister(view: View) {
        val email = edit_user_mail.text.toString()
        val username = edit_user_name.text.toString()
        val password = edit_user_password.text.toString()
        registrationPresenter.onRegister(email, username, password, this)
        registrationPresenter.saveUserData(username)
    }

    override fun onRegistrationSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onRegistrationFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        Injector.releaseRegistrationActivityComponent()
        Injector.registrationActivityComponent = null
        super.onDestroy()
    }
}
