package me.elrevin.bottomline.ui.start

import android.app.FragmentTransaction
import android.arch.lifecycle.Observer
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.elrevin.bottomline.R
import me.elrevin.bottomline.db.BlDataBase
import me.elrevin.bottomline.db.User
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class StartActivity : AppCompatActivity()
        , FirstFragment.OnFirstFragmentInteractionListener
        , SignUpFragment.OnSignUpFragmentInteractionListener
        , SignUp2Fragment.OnSignUp2FragmentInteractionListener
{
    override fun onGoneFromFirstFragment() {
        Toast.makeText(this, "Enter", Toast.LENGTH_LONG).show()
    }

    override fun onSignIn(pin: String, handler: ((soHow: Boolean) -> Unit)?) {
        db.userDao().getUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (handler != null) {
                        handler(it != null)
                    }
                }
    }

    val db: BlDataBase = Injekt.get()

    override fun onGoToSignUp() {
        showSignUp2Fragment()
    }

    override fun onSignUp(pin: String) {
        Completable.fromAction {
            db.userDao().insertUser(User(1, pin))
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Toast.makeText(this, "Sign up with pin" + pin, Toast.LENGTH_LONG).show()
                }
    }

    var firstFragment: FirstFragment ?= null
    var signUpFragment: SignUpFragment ?= null
    var signUp2Fragment: SignUp2Fragment ?= null

    override fun onBackToFirstFragment() {
        showFirstFragment()
    }

    override fun onSignUpRequest() {
        showSignUpFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        firstFragment = FirstFragment.newInstance()
        signUpFragment = SignUpFragment.newInstance()
        signUp2Fragment = SignUp2Fragment.newInstance()
        showFirstFragment()
    }

    private fun showFirstFragment() {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        if (signUpFragment!!.isAdded) {
            fragmentTransaction.hide(signUpFragment)
        }
        if (signUp2Fragment!!.isAdded) {
            fragmentTransaction.hide(signUp2Fragment)
        }
        if (firstFragment!!.isAdded) {
            fragmentTransaction.show(firstFragment)
        } else {
            fragmentTransaction.add(R.id.fragmentContainer, firstFragment)
        }
        fragmentTransaction.commit()
    }

    private fun showSignUpFragment() {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        if (firstFragment!!.isAdded) {
            fragmentTransaction.hide(firstFragment)
        }
        if (signUpFragment!!.isAdded) {
            fragmentTransaction.show(signUpFragment)
        } else {
            fragmentTransaction.add(R.id.fragmentContainer, signUpFragment)
        }
        fragmentTransaction.commit()
    }

    private fun showSignUp2Fragment() {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        if (signUpFragment!!.isAdded) {
            fragmentTransaction.hide(signUpFragment)
        }
        if (signUp2Fragment!!.isAdded) {
            fragmentTransaction.show(signUp2Fragment)
        } else {
            fragmentTransaction.add(R.id.fragmentContainer, signUp2Fragment)
        }
        fragmentTransaction.commit()
    }


}
