package com.example.piachimov.firebasekotlinexample.ui.registration

import android.content.Context
import android.widget.Toast
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.Hero
import com.example.piachimov.firebasekotlinexample.model.User
import com.example.piachimov.firebasekotlinexample.ui.main.MainActivity
import com.example.piachimov.firebasekotlinexample.utils.ScreenNavigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistrationPresenter(var registrationView: RegistrationView, var context: Context) {

    private val mAuth: FirebaseAuth? = Injector.appComponent?.firebaseAuth()

    private val firebaseDatabas: FirebaseDatabase? = Injector.appComponent?.firebaseDatabase()
    private val refUsers = firebaseDatabas?.getReference("users")

    fun onRegister(email: String, name: String, password: String, registrationActivity: RegistrationActivity) {
        if (!email.isEmpty() && !name.isEmpty() && !password.isEmpty()) {
            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(registrationActivity) { task ->
                if (task.isSuccessful) {
                    ScreenNavigation.switchActivity(context, MainActivity::class.java)
                    registrationView.onRegistrationSuccess("Registration is success!")
                } else {
                    registrationView.onRegistrationFailed("Registration failed!")
                }
            }
        } else {
            Toast.makeText(context, "Please fill up all required fields", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveUserData(name: String) {
        val userId = refUsers?.push()?.key
        val user = User(userId!!, name)
        refUsers?.child(userId)?.setValue(user)
    }
}