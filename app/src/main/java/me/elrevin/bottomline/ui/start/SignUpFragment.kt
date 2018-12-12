package me.elrevin.bottomline.ui.start

import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_sign_up.*

import me.elrevin.bottomline.R

class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnSignUpFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        view.findViewById<ImageView>(R.id.ivLogo).translationY = -420F

        val tvConfirmText = view.findViewById<TextView>(R.id.tvConfirmText)
        tvConfirmText.translationY = 700F
        tvConfirmText.alpha = 0F

        val btnContinue = view.findViewById<Button>(R.id.btnContinue)
        btnContinue.translationY = 700F
        btnContinue.alpha = 0F

        val btnSignIn = view.findViewById<Button>(R.id.btnSignIn)
        btnSignIn.translationY = 700F
        btnSignIn.alpha = 0F

        btnContinue.setOnClickListener {
            outAnimate {
                listener?.onGoToSignUp()
            }
        }

        btnSignIn.setOnClickListener {
            outAnimate {
                listener?.onBackToFirstFragment()
            }
        }

        return view
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

    private fun inAnimate(handler: (() -> Unit)?) {
        var delay:Long = 0
        val delayIncrement = 100

        tvConfirmText.animate().alpha(1F).setDuration(100).setStartDelay(delay).start()
        tvConfirmText.animate().translationY(0F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        btnContinue.animate().alpha(1F).setDuration(100).setStartDelay(delay).start()
        btnContinue.animate().translationY(0F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        btnSignIn.animate().alpha(1F).setDuration(100).setStartDelay(delay).start()
        btnSignIn.animate().translationY(0F).setDuration(300).setStartDelay(delay).start()

        if (handler != null) {
            Handler().postDelayed(handler, 1000)
        }
    }

    private fun outAnimate(handler: (() -> Unit)?) {
        var delay:Long = 0
        val delayIncrement = 100

        tvConfirmText.animate().alpha(0F).translationY(700F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        btnContinue.animate().alpha(0F).translationY(700F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        btnSignIn.animate().alpha(0F).translationY(700F).setDuration(300).setStartDelay(delay).start()

        if (handler != null) {
            Handler().postDelayed(handler, 700)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSignUpFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnSignUpFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnSignUpFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onBackToFirstFragment()
        fun onGoToSignUp()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                SignUpFragment().apply {
                    arguments = Bundle()
                }
    }
}
