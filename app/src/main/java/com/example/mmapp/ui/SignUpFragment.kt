package com.example.mmapp.ui

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mmapp.R
import com.example.mmapp.databinding.FragmentSignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_sign_up.*


/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    lateinit var binding:FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_sign_up, container, false)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignUp.setOnClickListener{
            signUpUser()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }

    private fun signUpUser(){
        if(input_email.text.toString().isEmpty()){
            input_email.error = "Please enter email"
            input_email.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(input_email.text.toString()).matches()){
            input_email.error = "Please enter valid email"
            input_email.requestFocus()
            return
        }

        if(input_password.text.toString().isEmpty()){
            input_password.error = "Please enter password"
            input_password.requestFocus()
            return
        }

//        auth.createUserWithEmailAndPassword(input_email.text.toString(), input_password.text.toString())
//            .addOnCompleteListener(this, object : OnCompleteListener<AuthResult?> {
//                override fun onComplete(task: Task<AuthResult?>) {
//                    if (task.isSuccessful()) { // Sign in success, update UI with the signed-in user's information
//                        //Log.d(TAG, "createUserWithEmail:success")
//                        val user: FirebaseUser = auth.currentUser!!
//                        Navigation.findNavController(view!!).navigate(R.id.action_signUpFragment_to_loginFragment)
//                        activity!!.finish()
//                    } else { // If sign in fails, display a message to the user.
//                        //Log.w(TAG, "createUserWithEmail:failure", task.getException())
//                        Toast.makeText(
//                            context, "Authentication failed.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    // ...
//                }
//            })
    }

}

//private fun <TResult> Task<TResult>.addOnCompleteListener(signUpFragment: SignUpFragment, onCompleteListener: OnCompleteListener<TResult?>) {
//
//}
