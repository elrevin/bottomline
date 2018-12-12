package me.elrevin.bottomline.ui.start

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_sign_up2.*

import me.elrevin.bottomline.R

class SignUp2Fragment : Fragment() {
    private var listener: OnSignUp2FragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up2, container, false)
        view.findViewById<ImageView>(R.id.ivLogo).translationY = -420F

        val tvPincodeHelpText = view.findViewById<TextView>(R.id.tvPincodeHelpText)
        tvPincodeHelpText.translationY = 700F
        tvPincodeHelpText.alpha = 0F

        val etPinCode = view.findViewById<EditText>(R.id.etPinCode)
        etPinCode.translationY = 700F
        etPinCode.alpha = 0F

        val btnContinue = view.findViewById<Button>(R.id.btnContinue)
        btnContinue.translationY = 700F
        btnContinue.alpha = 0F

        val btnSignIn = view.findViewById<Button>(R.id.btnSignIn)
        btnSignIn.translationY = 700F
        btnSignIn.alpha = 0F

        btnSignIn.setOnClickListener {
            outAnimate {
                listener?.onBackToFirstFragment()
            }
        }

        btnContinue.setOnClickListener {
            val pin: String = etPinCode.text.toString()
            outAnimate {
                listener?.onSignUp(pin)
            }
        }

        return view
    }

    private fun inAnimate(handler: (() -> Unit)?) {
        var delay:Long = 0
        val delayIncrement = 100

        tvPincodeHelpText.animate().alpha(1F).setDuration(100).setStartDelay(delay).start()
        tvPincodeHelpText.animate().translationY(0F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        etPinCode.animate().alpha(1F).setDuration(100).setStartDelay(delay).start()
        etPinCode.animate().translationY(0F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        btnContinue.animate().alpha(1F).setDuration(100).setStartDelay(delay).start()
        btnContinue.animate().translationY(0F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        btnSignIn.animate().alpha(1F).setDuration(100).setStartDelay(delay).start()
        btnSignIn.animate().translationY(0F).setDuration(300).setStartDelay(delay).start()

        if (handler != null) {
            Handler().postDelayed(handler, 1200)
        }
    }

    private fun outAnimate(handler: (() -> Unit)?) {
        var delay:Long = 0
        val delayIncrement = 100

        tvPincodeHelpText.animate().alpha(0F).translationY(700F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        etPinCode.animate().alpha(0F).translationY(700F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        btnContinue.animate().alpha(0F).translationY(700F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        btnSignIn.animate().alpha(0F).translationY(700F).setDuration(300).setStartDelay(delay).start()

        if (handler != null) {
            Handler().postDelayed(handler, 900)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) {
            inAnimate(null)
        }
        super.onHiddenChanged(hidden)
    }

    override fun onResume() {
        inAnimate(null)
        super.onResume()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSignUp2FragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnSignUp2FragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnSignUp2FragmentInteractionListener {
        fun onBackToFirstFragment()
        fun onSignUp(pin: String)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SignUp2.
         */
        @JvmStatic
        fun newInstance() = SignUp2Fragment()
    }
}
