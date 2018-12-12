package me.elrevin.bottomline.ui.start

import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*

import me.elrevin.bottomline.R
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FirstFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var listener: OnFirstFragmentInteractionListener? = null

    var firstShowing = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        val ivBg = view.findViewById<ImageView>(R.id.ivBg)
        ivBg.scaleX = 0F
        ivBg.scaleY = 0F
        ivBg.alpha = 0F

        val ivLogo = view.findViewById<ImageView>(R.id.ivLogo)
        ivLogo.scaleX = 0F
        ivLogo.scaleY = 0F
        ivLogo.alpha = 0F

        val enPinCode = view.findViewById<EditText>(R.id.etPinCode)
        enPinCode.translationY = 700F
        enPinCode.alpha = 0F

        val btnSignIn = view.findViewById<Button>(R.id.btnSignIn)
        btnSignIn.translationY = 700F
        btnSignIn.alpha = 0F

        val ivFingerPrint = view.findViewById<ImageView>(R.id.ivFingerPrint)
        ivFingerPrint.translationY = 700F
        ivFingerPrint.alpha = 0F

        val tvFingerPrintHelp = view.findViewById<TextView>(R.id.tvFingerPrintHelp)
        tvFingerPrintHelp.translationY = 700F
        tvFingerPrintHelp.alpha = 0F

        val btnSignUp = view.findViewById<Button>(R.id.btnSignUp)
        btnSignUp.translationY = 700F
        btnSignUp.alpha = 0F

        btnSignUp.setOnClickListener {
            outAnimate {
                listener?.onSignUpRequest()
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        Log.i("start fragment", "onAttach")

        super.onAttach(context)
        if (context is OnFirstFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFirstFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) {
            inAnimate(null)
        }
        super.onHiddenChanged(hidden)
    }

    private fun inAnimate(handler: (() -> Unit)?) {
        var delay:Long = -600
        if (firstShowing) {
            firstShowing = false
            ivBg.animate().alpha(1F).setDuration(100).start()
            ivBg.animate().scaleX(1F).scaleY(1F).setDuration(200).start()

            delay = 200


            ivLogo.animate().alpha(1F).setDuration(200).setStartDelay(delay).start()
            ivLogo.animate().scaleX(1F).scaleY(1F).setDuration(500).setStartDelay(delay).start()

            delay += 700

            ivLogo.animate().translationY(-420F).setDuration(500).setStartDelay(delay).start()
        }

        delay += 600
        val delayIncrement = 100

        etPinCode.animate().alpha(1F).setDuration(100).setStartDelay(delay).start()
        etPinCode.animate().translationY(0F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        btnSignIn.animate().alpha(1F).setDuration(100).setStartDelay(delay).start()
        btnSignIn.animate().translationY(0F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        ivFingerPrint.animate().alpha(1F).setDuration(100).setStartDelay(delay).start()
        ivFingerPrint.animate().translationY(0F).setDuration(300).setStartDelay(delay).start()
        tvFingerPrintHelp.animate().alpha(1F).setDuration(100).setStartDelay(delay).start()
        tvFingerPrintHelp.animate().translationY(0F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        btnSignUp.animate().alpha(1F).setDuration(100).setStartDelay(delay).start()
        btnSignUp.animate().translationY(0F).setDuration(300).setStartDelay(delay).start()

        if (handler != null) {
            var postDelay: Long = if (firstShowing)  2500 else 1000
            Handler().postDelayed(handler, postDelay)
        }
    }

    private fun outAnimate(handler: (() -> Unit)?) {
        var delay:Long = 0
        val delayIncrement = 100

        etPinCode.animate().alpha(0F).translationY(700F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        btnSignIn.animate().alpha(0F).translationY(700F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        ivFingerPrint.animate().alpha(0F).translationY(700F).setDuration(300).setStartDelay(delay).start()
        tvFingerPrintHelp.animate().alpha(0F).translationY(700F).setDuration(300).setStartDelay(delay).start()

        delay += delayIncrement

        btnSignUp.animate().alpha(0F).translationY(700F).setDuration(300).setStartDelay(delay).start()

        if (handler != null) {
            Handler().postDelayed(handler, 1000)
        }
    }

    override fun onResume() {
        inAnimate(null)
        super.onResume()
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
    interface OnFirstFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onSignUpRequest()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                FirstFragment().apply {
                    arguments = Bundle()
                }
    }
}
