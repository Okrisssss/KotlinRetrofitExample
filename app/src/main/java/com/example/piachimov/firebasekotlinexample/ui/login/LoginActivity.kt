package com.example.piachimov.firebasekotlinexample.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.R.string.email
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.ui.main.MainActivity
import com.example.piachimov.firebasekotlinexample.ui.registration.RegistrationActivity
import com.example.piachimov.firebasekotlinexample.utils.ScreenNavigation
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import javax.inject.Inject
import com.facebook.GraphResponse
import org.json.JSONObject
import com.facebook.GraphRequest
import org.json.JSONException
import com.facebook.Profile.getCurrentProfile
import com.facebook.AccessToken






class LoginActivity : AppCompatActivity(), LoginView {

    @Inject
    lateinit var loginPresenter: LoginPresenter
    lateinit var callbackManager: CallbackManager

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(this)
        setContentView(R.layout.activity_login)
        Injector.initLoginActivityComponent(this)
        Injector.loginActivityComponent?.inject(this)
        if (loginPresenter.mAuth?.currentUser != null) {
            ScreenNavigation.switchActivity(this, MainActivity::class.java)
        }
        callbackManager = CallbackManager.Factory.create()
        //facebook_login_button.setReadPermissions("public_profile", "email")
        facebook_login_button.setReadPermissions(Arrays.asList("public_profile", "email"))

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
    }

    fun onFacebookLogin(view: View) {
        facebook_login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                ScreenNavigation.switchActivity(applicationContext, MainActivity::class.java)
                val accessToken = result?.accessToken
                val profile = Profile.getCurrentProfile()

                // Facebook Email address
                val request = GraphRequest.newMeRequest(
                        result?.accessToken
                ) { `object`, response ->
                    Log.v("LoginActivity Response ", response.toString())

                    try {
                        val name: String = `object`.getString("name")

                        val fname: String = `object`.getString("email")
                        Log.v("Email = ", " $fname")
                        Toast.makeText(applicationContext, "Name $name", Toast.LENGTH_LONG).show()


                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email,gender, birthday")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun switchToMainActivity(view: View) {
        val email = edit_username.text.toString()
        val password = edit_password.text.toString()
        loginPresenter.onLogin(email, password, this)
    }

    fun switchToRegisterActivity(view: View) {
        ScreenNavigation.switchActivity(this, RegistrationActivity::class.java)
    }


    override fun onLoginSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        Injector.releaseLoginActivityComponent()
        Injector.loginActivityComponent = null
        super.onDestroy()
    }
}
