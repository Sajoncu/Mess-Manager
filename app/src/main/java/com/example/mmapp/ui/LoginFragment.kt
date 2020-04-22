package com.example.mmapp.ui

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.mmapp.R
import com.example.mmapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        // Inflate the layout for this fragment
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    private fun userLogin() {
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

        auth.signInWithEmailAndPassword(input_email.text.toString(), input_email.text.toString())
            .addOnSuccessListener { authResult ->
                if(authResult != null){
                    Navigation.findNavController(view!!).navigate(R.id.action_loginFragment_to_dashboardFragment)
                    activity!!.finish()
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Login failed due to "+it, Toast.LENGTH_SHORT).show()
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnLogin.setOnClickListener {
            userLogin()
        }

        binding.linkSignup.setOnClickListener{
            Navigation.findNavController(view!!).navigate(R.id.action_loginFragment_to_signUpFragment)
            activity!!.finish()
        }
    }
}
