package me.elrevin.bottomline.ui.start

import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import me.elrevin.bottomline.R

class StartActivity : AppCompatActivity()
        , FirstFragment.OnFirstFragmentInteractionListener
        , SignUpFragment.OnSignUpFragmentInteractionListener
        , SignUp2Fragment.OnSignUp2FragmentInteractionListener
{
    override fun onGoToSignUp() {
        showSignUp2Fragment()
    }

    override fun onSignUp() {
        Toast.makeText(this, "Sign up", Toast.LENGTH_LONG).show()
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
